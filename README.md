# Group1 – Plan-iT

A full-stack web application built by **Software Engineer Solution Group 1** for managing productivity, sessions, and user accounts.

🌐 **Live Site:** [https://dgoumdi1.github.io/Group1/](https://dgoumdi1.github.io/Group1/)
🚀 **Backend API:** [https://backend-production-7dee1.up.railway.app](https://backend-production-7dee1.up.railway.app)

---

## Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Deployment](#deployment)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Backend Setup](#backend-setup)
  - [Frontend Setup](#frontend-setup)
- [Environment Variables](#environment-variables)
- [Running with Docker](#running-with-docker)
- [Contributors](#contributors)

---

## Overview

Productivity Buddy is a web application that allows users to register, log in, and manage productivity sessions. The backend provides a RESTful API with authentication and session management, while the frontend delivers an interactive user interface.

---

## Tech Stack

### Backend
- **Java 21** with **Spring Boot 3.2.0**
- **Spring Security** – Authentication & authorization
- **Spring Data JPA** – ORM & database access
- **PostgreSQL** – Relational database
- **Docker & Docker Compose** – Containerized local database
- **Gradle** – Build tool

### Frontend
- **HTML / CSS / JavaScript**
- Hosted on **GitHub Pages**

### Cloud Infrastructure (Railway)
- **Backend service** – Java/Spring Boot app deployed on Railway (us-west2)
- **PostgreSQL service** – Managed PostgreSQL database on Railway

---

## Project Structure

```
Group1/
├── backend/
│   ├── app/
│   │   ├── src/          # Java source code
│   │   └── build.gradle  # App-level Gradle config
│   ├── docker-compose.yml
│   ├── .env.example
│   └── build.gradle
├── front-end/            # Frontend source files
├── index.html
├── .gitignore
└── README.md
```

---

## Deployment

### Frontend – GitHub Pages

| | |
|---|---|
| **URL** | https://dgoumdi1.github.io/Group1/ |
| **Source** | `main` branch, root `/` |
| **Platform** | GitHub Pages |

### Backend – Railway

| | |
|---|---|
| **API URL** | https://backend-production-7dee1.up.railway.app |
| **Port** | 8080 |
| **Runtime** | Java 21.0.2 |
| **Region** | us-west2 |
| **Platform** | Railway (`spirited-exploration` project) |
| **Root Directory** | `backend/` |
| **Branch** | `main` |

### Database – Railway PostgreSQL

| | |
|---|---|
| **Platform** | Railway Managed PostgreSQL |
| **Internal Host** | `backend.railway.internal` |
| **Project** | `spirited-exploration` |

The backend connects to the Railway PostgreSQL service using the following environment variables (injected automatically by Railway):

| Variable | Description |
|---|---|
| `DATABASE_URL` | Full PostgreSQL connection URL |
| `PGDATABASE` | Database name |
| `PGHOST` | Database host |
| `PGPASSWORD` | Database password |
| `PGPORT` | Database port |
| `PGUSER` | Database user |

---

## Getting Started

### Prerequisites

- Java 17+
- Gradle
- Docker & Docker Compose
- PostgreSQL (or use Docker for local dev)

### Backend Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/dgoumdi1/Group1.git
   cd Group1/backend
   ```

2. **Set up environment variables**
   ```bash
   cp .env.example .env
   # Edit .env with your local database credentials
   ```

3. **Start the local database**
   ```bash
   docker-compose up -d
   ```

4. **Run the backend**
   ```bash
   cd app
   ../gradlew bootRun
   ```

   The API will be available at `http://localhost:8080`.

### Frontend Setup

1. Open `index.html` in your browser, or visit the live site at [https://dgoumdi1.github.io/Group1/](https://dgoumdi1.github.io/Group1/).
2. For local development, make sure the API base URL in the frontend source points to `http://localhost:8080`.

---

## Environment Variables

For **local development**, create a `.env` file in `backend/` based on `.env.example`:

| Variable | Default | Description |
|---|---|---|
| `POSTGRES_DB` | `productivitybuddy` | Database name |
| `POSTGRES_USER` | `postgres` | Database user |
| `POSTGRES_PASSWORD` | `postgres` | Database password |

For **production on Railway**, variables are injected automatically from the linked PostgreSQL service (`DATABASE_URL`, `PGHOST`, `PGPORT`, `PGDATABASE`, `PGUSER`, `PGPASSWORD`).

---

## Running with Docker

The `docker-compose.yml` in `backend/` spins up a local PostgreSQL 16 instance for development:

```bash
cd backend
docker-compose up -d
```

This starts a PostgreSQL container at `localhost:5433` (mapped from container port `5432`).

---

## Contributors

| GitHub | Role |
|---|---|
| [@dgoumdi1](https://github.com/dgoumdi1) | Developer |
| [@IT1800](https://github.com/IT1800) | Developer |
| [@colinhart2004](https://github.com/colinhart2004) | Developer |
| [@hadiya-tan](https://github.com/hadiya-tan) | Developer |
| [@amyosaurus](https://github.com/amyosaurus) | Developer |
| [@MarcusWord27](https://github.com/MarcusWord27) | Developer |

---

> Eastern Michigan University – Software Engineering · Group 1
