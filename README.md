# Tax Filing Assistant — Fullstack Scaffold

This workspace contains a minimal scaffold for a Tax Filing Assistant web application.

Tech stack:
- Frontend: React (Node.js) with Formik + Yup, Axios, jsPDF
- Backend: Spring Boot (Java, Maven) exposing REST APIs
- Database: PostgreSQL (JPA/Hibernate)

Structure:
- `backend/` — Spring Boot app
- `frontend/` — React app

What I implemented in this scaffold:
- Backend endpoints: `/calculateTax`, `/deductions`, `/generatePDF`, `/reminder`
- Basic tax calculation logic (simple progressive slabs)
- PDF generation endpoint using Apache PDFBox (returns application/pdf)
- Frontend skeleton with pages for income, deductions, and results using Formik + Yup and Axios calls to backend
- CORS enabled in backend (simple configuration)

How to run (local development):

1) Backend (Java / Maven)
- Edit `backend/src/main/resources/application.properties` and set your PostgreSQL URL, username, and password or use H2 for quick tests.
- From `backend/` run:

  mvn spring-boot:run

2) Frontend (Node.js)
- From `frontend/` install deps and start:

  npm install
  npm run dev

Notes and next steps:
- Add JWT auth and secure endpoints
- Add migrations and seed data
- Add tests and CI

License: prototype code — adapt and secure before production use.

