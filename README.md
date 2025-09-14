📚 Library Management System (Spring Boot + JWT + PostgreSQL)

A simple Library Management System built with Spring Boot, Spring Security (JWT), and PostgreSQL.
This project supports user authentication, role-based access (ADMIN/USER), book management, borrowing/returning books, and borrow history tracking.

🚀 Features

User Registration & Login (JWT-based authentication)
Role-based access:
Admin → can add, update, and delete books
User → can borrow and return books
Borrow & Return books with availability tracking
View borrow history (per user & admin full history)
Search books by keyword (general search)

🛠️ Tech Stack

Backend: Spring Boot, Spring Security (JWT)
Database: PostgreSQL + JPA/Hibernate
Build Tool: Maven
Language: Java 17+

📌 API Endpoints
🔐 Auth APIs
Register User
1- POST /api/auth/register

{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "12345",
  "role": "USER"
}

2- Login (Generates JWT Token)
POST /api/auth/login

{
  "email": "john@example.com",
  "password": "12345"
}

3- Book APIs (Admin Only)
Add Book
POST /api/books

{
  "title": "DSA in JAVA",
  "author": "Navin Reddy",
  "category": "Programming",
  "totalCopies": 10,
  "availableCopies": 10
}

4- Delete Book
DELETE /api/books/{id}

5- Update Book
PUT /api/books/{id}

{
  "title": "DSA in JAVA",
  "author": "Navin Reddy",
  "category": "Programming",
  "totalCopies": 10,
  "availableCopies": 10
}

6- Get All Books
GET /api/books

7- Get Book by ID
GET /api/books/{id}

8- Search Books by Keyword
GET /api/books/general-search?keyword=java

9- Borrow & Return APIs
Borrow Book
POST /api/borrow/{userId}/{bookId}

10- Return Book
POST /api/return/{userId}/{bookId}

11- Borrow History
All Borrow History (Admin)
GET /api/borrow/history/all

12- Borrow History by User Email
GET /api/borrow/myhistory?email=john@example.com

✅ Example Flow

Register a user (role = USER or ADMIN)
Login → copy JWT token
Use token for Authorization in Postman
Admin → Add books
User → Borrow book
User → Return book
View borrow history

Images


![image alt]()

