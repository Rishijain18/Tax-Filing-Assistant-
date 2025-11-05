# Frontend — Tax Filing Assistant

This is a minimal React (Vite) frontend for the Tax Filing Assistant.

Quick start:

1. Install dependencies

   npm install

2. Start dev server

   npm run dev

The app expects the backend API to run at `http://localhost:8080/api`. Adjust `src/api.js` if needed.

Pages:
- Income — collect salary/business/other income
- Deductions — 80C, 80D, others
- Result — compute tax, export PDF (client or server)

Notes:
- Uses Formik + Yup for form handling and validation (basic schema included)
- Use `jsPDF` for client-side PDF export; server-side export is available via `/generatePDF` endpoint
