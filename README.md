# Group1 – Productivity Buddy

A full-stack web application built by **Software Engineer Solution Group 1** for managing productivity, sessions, and user accounts.

---

## Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
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
- **Java 17** with **Spring Boot 3.2.0**
- **Spring Security** – Authentication & authorization
- **Spring Data JPA** – ORM & database access
- **PostgreSQL** – Relational database
- **Docker & Docker Compose** – Containerized database
- **Gradle** – Build tool

### Frontend
- **HTML / CSS / JavaScript**
- Served via GitHub Pages

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

## Getting Started

### Prerequisites

- Java 17+
- Gradle
- Docker & Docker Compose
- Node.js (if frontend build tools are used)
- PostgreSQL (or use Docker)

### Backend Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/dgoumdi1/Group1.git
   cd Group1/backend
   ```

2. **Set up environment variables**
   ```bash
   cp .env.example .env
   # Edit .env with your database credentials
   ```

3. **Start the database**
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

1. Open `index.html` in your browser, or navigate to the deployed GitHub Pages URL.
2. For local development with the backend, make sure the API base URL is configured correctly in the frontend source files.

---

## Environment Variables

Create a `.env` file in the `backend/` directory based on `.env.example`:

| Variable            | Default           | Description                  |
|---------------------|-------------------|------------------------------|
| `POSTGRES_DB`       | `productivitybuddy` | Database name              |
| `POSTGRES_USER`     | `postgres`        | Database user                |
| `POSTGRES_PASSWORD` | `postgres`        | Database password            |

---

## Running with Docker

The `docker-compose.yml` in the `backend/` directory spins up a PostgreSQL 16 instance:

```bash
cd backend
docker-compose up -d
```

This starts a PostgreSQL container at `localhost:5433` (mapped from container port `5432`).

---

## Contributors

| GitHub | Role |
|--------|------|
| [@dgoumdi1](https://github.com/dgoumdi1) | Developer |
| [@IT1800](https://github.com/IT1800) | Developer |
| [@colinhart2004](https://github.com/colinhart2004) | Developer |
| [@hadiya-tan](https://github.com/hadiya-tan) | Developer |
| [@amyosaurus](https://github.com/amyosaurus) | Developer |
| [@MarcusWord27](https://github.com/MarcusWord27) | Developer |

---

> Eastern Michigan University – Software Engineering · Group 1
