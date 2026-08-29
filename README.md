# Job Portal REST API

## 🔗 Live Demo

- **API Base URL:** https://job-portal-9skh.onrender.com
- **Swagger Docs:** https://job-portal-9skh.onrender.com/swagger-ui/index.html

> Note: hosted on Render's free tier — the server may take ~50 seconds to wake up after inactivity.

A backend REST API for a job portal platform, supporting recruiters posting jobs and applicants applying to them, with JWT-based authentication and role-based access control.

## Features

- **Authentication & Security**
  - User registration and login with BCrypt password hashing
  - JWT-based stateless authentication
  - Role-based authorization (RECRUITER / APPLICANT)
  - Ownership checks — recruiters can only manage their own job postings and applications

- **Job Management**
  - Recruiters can post, view jobs
  - Public job browsing with pagination and filtering (by location, skills, minimum salary)

- **Application Management**
  - Applicants can apply to jobs and track their application status
  - Recruiters can view applications for their jobs and update status (APPLIED → SHORTLISTED → REJECTED/HIRED)

- **API Quality**
  - Global exception handling with clean, consistent JSON error responses
  - Input validation with field-level error messages
  - Interactive API documentation via Swagger/OpenAPI, with JWT support built in

## Tech Stack

- **Language:** Java 17
- **Framework:** Spring Boot 3 (Spring Web, Spring Data JPA, Spring Security)
- **Database:** MySQL
- **Auth:** JWT (jjwt library), BCrypt
- **Docs:** Springdoc OpenAPI (Swagger UI)
- **Build Tool:** Maven

## Getting Started

### Prerequisites
- Java 17+
- MySQL 8+
- Maven (or use the included `mvnw` wrapper)

### Setup

1. Clone the repository:

   git clone https://github.com/IshaB16/job-portal.git
   cd job-portal

2. Create the MySQL database:
```sql
   CREATE DATABASE job_portal;
```

3. Set the `DB_PASSWORD` environment variable to your MySQL password (see `application.properties` for the connection config).

4. Run the application:
   ./mvnw spring-boot:run

5. Access the API at `http://localhost:8080`, or explore it interactively via Swagger at `http://localhost:8080/swagger-ui/index.html`.

## API Overview

| Endpoint | Method | Access | Description |
|---|---|---|---|
| `/auth/register` | POST | Public | Register a new user |
| `/auth/login` | POST | Public | Login and receive a JWT token |
| `/jobs` | GET | Public | List jobs (paginated, filterable) |
| `/jobs` | POST | RECRUITER | Post a new job |
| `/applications` | POST | APPLICANT | Apply to a job |
| `/applications/my` | GET | APPLICANT | View your own applications |
| `/applications/job/{jobId}` | GET | RECRUITER | View applications for your job |
| `/applications/{id}/status` | PUT | RECRUITER | Update an application's status |

## Key Design Decisions

- **Stateless JWT auth** instead of session-based auth, for scalability and simplicity in a REST API context.
- **DTOs instead of exposing entities directly**, to avoid leaking sensitive fields (like password hashes) in API responses.
- **Ownership checks layered on top of role checks** — role-based authorization alone isn't enough when data belongs to specific users (e.g., one recruiter shouldn't manage another recruiter's job applications).

## Author

Isha — built as part of backend development practice ahead of campus placements.
