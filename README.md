# Simple Form Management System with Spring Boot

## Description
This project is a Spring Boot-based web application designed to manage forms and their fields. It provides a RESTful API for creating, retrieving, updating, and deleting forms, as well as managing the fields associated with each form. The project incorporates robust error handling to ensure clear and consistent responses for invalid or failed operations.

## Features
### 1. Form Management
- Create new forms with multiple fields.
- Retrieve forms by ID or list all forms.
- Update form details, including name, publication status, and fields.
- Delete forms by ID with proper error handling for non-existent IDs.

### 2. Field Management
- Add fields to forms, including properties like name, label, type, and default value.
- Support for multiple field types (e.g., text, email, etc.).
- Ensure bidirectional mapping between forms and fields.

### 3. Error Handling
- Custom exceptions (`FormNotFoundException`) for better debugging and user feedback.
- Global exception handler to manage errors consistently across the API.
- Clear error messages with HTTP status codes (e.g., 404 for "not found").

### 4. Technologies Used
- **Spring Boot:** Core framework for the application.
- **JPA/Hibernate:** For database interactions and entity management.
- **H2 Database:** Lightweight, in-memory database for development and testing.
- **Maven:** For project build and dependency management.

### 5. API Features
- RESTful API endpoints with JSON responses.
- Endpoints for form and field management.
- Proper validation and exception handling.

