
Library Management System (Full)
- Features: register (role), login (JWT), admin book CRUD, borrow/return, borrow history, role-based method security.
- Update src/main/resources/application.properties for DB and jwt.secret.
- Build: mvn clean package
- Run: java -jar target/library-0.0.1-SNAPSHOT.jar
Endpoints summary:
- POST /api/auth/register
- POST /api/auth/login
- GET /api/users/me
- ADMIN: POST/PUT/DELETE /api/books
- USER: POST /api/borrow/{userId}/{bookId}, POST /api/borrow/return/{userId}/{bookId}, GET /api/borrow/history/{userId}
