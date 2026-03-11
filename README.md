# Group1
Software Engineer Solution Group 1

## Deployment Notes

### 1) Deploy backend to public HTTPS

Use any host that supports Java/Spring Boot and PostgreSQL (Render, Railway, Azure App Service, etc.).

For Railway in this repo:

1. Create a new service in your Railway project from this same GitHub repo.
2. Set **Root Directory** to `backend` for that new service.
3. Keep the default builder (Nixpacks). The service will use `backend/railway.toml`.
4. Add service variables: `DB_URL`, `DB_USER`, `DB_PASSWORD`.
5. Deploy and copy the backend service URL (it must be different from your frontend/static URL).

Quick API check after deploy:

```bash
curl -i -X POST https://<your-backend-service>.up.railway.app/api/auth/login \
	-H 'Content-Type: application/json' \
	-d '{"usernameOrEmail":"x","password":"x"}'
```

Expected result: not `405` and not HTML.

Required backend environment variables:

- `DB_URL`
- `DB_USER`
- `DB_PASSWORD`

Backend API should be reachable at:

- `https://<your-backend-domain>/api/auth/login`
- `https://<your-backend-domain>/api/auth/register`

### 2) Frontend API base URL

The frontend auth code uses:

- Local: `http://localhost:8090/api`
- Production default placeholder: `https://your-backend-domain.com/api`

Set this in production pages before loading auth.js:

```html
<script>
	window.APP_API_BASE_URL = 'https://<your-backend-domain>/api';
</script>
<script src="auth.js"></script>
```

For root `index.html`, use:

```html
<script>
	window.APP_API_BASE_URL = 'https://<your-backend-domain>/api';
</script>
<script src="front-end/auth.js"></script>
```
