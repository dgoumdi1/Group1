// API Configuration
const LOCAL_API_BASE_URL = 'http://localhost:8090/api';
const PROD_API_BASE_URL = 'https://backend-production-7dee1.up.railway.app/api';
const API_BASE_URL = (window.location.hostname === 'localhost' || window.location.hostname === '127.0.0.1')
    ? LOCAL_API_BASE_URL
    : PROD_API_BASE_URL;

function getPagePath(fileName) {
    const inFrontEndFolder = window.location.pathname.includes('/front-end/');
    const isLocalhost = window.location.hostname === 'localhost' || window.location.hostname === '127.0.0.1';
    return (inFrontEndFolder || isLocalhost) ? fileName : `front-end/${fileName}`;
}

async function parseApiResponse(response) {
    const contentType = response.headers.get('content-type') || '';

    if (contentType.includes('application/json')) {
        return response.json();
    }

    const text = await response.text();
    return {
        message: text ? text.substring(0, 180) : `Request failed (${response.status})`
    };
}

// Auth API functions
async function login(usernameOrEmail, password) {
    try {
        const response = await fetch(`${API_BASE_URL}/auth/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ usernameOrEmail, password })
        });

        const data = await parseApiResponse(response);

        if (response.ok) {
            // Store login state
            localStorage.setItem('isLoggedIn', 'true');
            localStorage.setItem('username', usernameOrEmail);
            localStorage.setItem('sessionPassword', password);
            return { success: true, message: data.message };
        } else {
            return { success: false, message: data.message || 'Login failed' };
        }
    } catch (error) {
        console.error('Login error:', error);
        return { success: false, message: 'Network error. Please try again.' };
    }
}

async function register(username, email, password) {
    try {
        const response = await fetch(`${API_BASE_URL}/auth/register`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ username, email, password })
        });

        const data = await parseApiResponse(response);

        if (response.ok) {
            return { success: true, message: data.message };
        } else {
            return { success: false, message: data.message || 'Registration failed' };
        }
    } catch (error) {
        console.error('Registration error:', error);
        return { success: false, message: 'Network error. Please try again.' };
    }
}

function logout() {
    localStorage.removeItem('isLoggedIn');
    localStorage.removeItem('username');
    localStorage.removeItem('sessionPassword');
    window.location.href = getPagePath('index.html');
}

// Session API functions
const SESSION_API_BASE_URL = (window.location.hostname === 'localhost' || window.location.hostname === '127.0.0.1')
    ? 'http://localhost:8090/api/v1/sessions'
    : 'https://backend-production-7dee1.up.railway.app/api/v1/sessions';

function getSessionHeaders() {
    const username = getUsername();
    const password = localStorage.getItem('sessionPassword');
    if (!username || !password) return { 'Content-Type': 'application/json' };
    return {
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + btoa(username + ':' + password)
    };
}

async function startSession() {
    const response = await fetch(`${SESSION_API_BASE_URL}/start`, {
        method: 'POST',
        headers: getSessionHeaders()
    });
    return parseApiResponse(response);
}

async function stopSession() {
    const response = await fetch(`${SESSION_API_BASE_URL}/stop`, {
        method: 'POST',
        headers: getSessionHeaders()
    });
    return parseApiResponse(response);
}

async function getTodayMinutes() {
    const response = await fetch(`${SESSION_API_BASE_URL}/today`, {
        headers: getSessionHeaders()
    });
    if (response.ok) return response.json();
    return null;
}

async function getWeekMinutes() {
    const response = await fetch(`${SESSION_API_BASE_URL}/week`, {
        headers: getSessionHeaders()
    });
    if (response.ok) return response.json();
    return null;
}

function isLoggedIn() {
    return localStorage.getItem('isLoggedIn') === 'true';
}

function getUsername() {
    return localStorage.getItem('username');
}

// Initialize auth state on page load
document.addEventListener('DOMContentLoaded', function() {
    const currentPage = window.location.pathname.split('/').pop();

    // If already logged in, keep users on the app home instead of sign-in page.
    if (isLoggedIn() && (currentPage === '' || currentPage === 'index.html')) {
        window.location.href = getPagePath('homepage.html');
        return;
    }

    // Handle login form if it exists
    const loginForm = document.querySelector('#sign_in form');
    if (loginForm) {
        loginForm.addEventListener('submit', async function(e) {
            e.preventDefault();
            
            const username = document.getElementById('username').value;
            const password = document.getElementById('password').value;
            
            const result = await login(username, password);
            
            if (result.success) {
                alert('Login successful!');
                window.location.href = getPagePath('homepage.html');
            } else {
                alert(result.message);
            }
        });
    }

    // Check if user is logged in and update UI
    if (isLoggedIn()) {
        const username = getUsername();
        // You can update the UI to show logged-in state
        console.log('User logged in:', username);
    }
});
