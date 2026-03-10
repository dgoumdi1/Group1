// API Configuration
const API_BASE_URL = 'http://localhost:8090/api';

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

        const data = await response.json();

        if (response.ok) {
            // Store login state
            localStorage.setItem('isLoggedIn', 'true');
            localStorage.setItem('username', usernameOrEmail);
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

        const data = await response.json();

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
    window.location.href = 'index.html';
}

function isLoggedIn() {
    return localStorage.getItem('isLoggedIn') === 'true';
}

function getUsername() {
    return localStorage.getItem('username');
}

// Initialize auth state on page load
document.addEventListener('DOMContentLoaded', function() {
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
                window.location.href = 'homepage.html';
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
