
# 📚 Flipped Classroom Platform

A full-stack web application that redefines modern education using the **flipped classroom** model. Built with **Spring Boot (Java)** and **React (JS)**, this platform promotes interactive learning by shifting lecture content outside the classroom and reserving class time for collaboration, problem-solving, and discussions.

---

## 🚀 Features

- 📁 **Centralized Resource Upload:** Teachers can upload video lectures, PDFs, assignments.
- 💬 **Real-Time Chatrooms:** Secure messaging between students and instructors.
- 👥 **Role-Based Access:** Separate views and permissions for Admin, Teacher, and Student.
- 📊 **Engagement Analytics:** Track resource usage, quiz performance, and participation.
- 🧠 **Gamified Modules (Planned):** Badges, leaderboards, and interactive learning tools.
- 📈 **Scalable Architecture:** Easily extendable to large institutions.

---

## 🛠️ Tech Stack

### Backend
- Java 17+
- Spring Boot (Security, Data JPA)
- MySQL
- JWT (Authentication)

### Frontend
- React.js
- HTML, CSS, JS
- Axios (API Calls)

---

## 🧩 System Architecture

- **Controllers:** Handle RESTful API endpoints.
- **Services:** Contain business logic.
- **Repositories:** Interface with MySQL via Spring Data JPA.
- **Entities/DTOs:** Define database schema and transfer models.
- **Security:** JWT-based login with RBAC (Role-Based Access Control).

---

## 📐 Database Schema Overview

- `User`: `id`, `name`, `email`, `role`, `password_hash`
- `Course`: `id`, `title`, `description`, `teacher_id`
- `Material`: `id`, `title`, `type`, `url`, `course_id`, `uploaded_at`
- `Enrollment`: `student_id`, `course_id`

---

## 📲 API Endpoints (Sample)

| Endpoint                        | Method | Description                          |
|---------------------------------|--------|--------------------------------------|
| `/api/auth/register`           | POST   | Register new user                    |
| `/api/auth/login`              | POST   | Authenticate and return JWT         |
| `/api/courses`                 | GET    | Fetch all available courses          |
| `/api/materials`               | POST   | Upload course material               |
| `/api/quizzes/submit`          | POST   | Submit quiz answers                  |

---

## 🔐 Authentication & Authorization

- **JWT** used for session handling.
- **RBAC** separates student, teacher, and admin access.
- Secure password storage using hashing algorithms (e.g., Bcrypt).

---

## 📆 Development Timeline

- 📌 **Phase 1**: Planning, Schema Design
- 🧱 **Phase 2**: Spring Boot Backend, Auth
- 🎨 **Phase 3**: Frontend UI with React
- 🧪 **Phase 4**: Testing with Postman & Deployment (Render/Heroku)

---

## 🌱 Future Enhancements

- 📹 Live Video Integration (Zoom/Jitsi)
- 🧠 AI-based Quiz and Summary Generation
- 📱 Mobile App (React Native/Flutter)
- 🌐 Multilingual & Accessibility Support
- 🎮 Gamified Learning Dashboard

---

## 👨‍💻 Authors

- Agnibha Chakraborty  
- Soumyadeep Samanta  
- Dyutiprovo Sarkar  
- Souvik Bose  
👨‍🏫 Guided by: **Prof. Debashis Chakraborty**

---

## 📖 License

This project is intended for academic and demonstration purposes. Licensing terms can be added as required.
