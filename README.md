 ExpressionConverter – Java Project

──────────────────────────────────────────────

 Developed by:  
- Mohit Soni – Login System  
- Piyush Agnihotri – Expression Converter

──────────────────────────────────────────────

 Overview

A full-fledged Java Swing project combining:  
1. User Authentication System – with JDBC + MySQL  
2. Expression Converter – supporting Infix, Prefix, Postfix conversions using Multithreading

──────────────────────────────────────────────

 Login System (By Mohit Soni)

 Technologies:
- Java Swing & AWT (GUI)
- JDBC (Database Connectivity)

 Features:
- User Registration & Login
- Password security & validation
- Handles incorrect credentials gracefully

──────────────────────────────────────────────

 Expression Converter (By Piyush Agnihotri)

 Technologies:
- Java Swing (UI)
- Multithreading (Efficient Evaluation)

 Features:
- Real-time conversion between Infix, Prefix & Postfix
- Step-by-step evaluation display for deeper understanding

──────────────────────────────────────────────

🚀 How to Run This Project

🔸 1. Database Setup

CREATE DATABASE userdb;

USE userdb;

CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL
);

🔸 2. Configure Database in `DatabaseHelper.java`

private static final String URL = "jdbc:mysql://localhost:3306/userdb";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";

🔸 3. Add MySQL JDBC Driver

- Download from: https://dev.mysql.com/downloads/connector/j/
- Add `mysql-connector-java-x.x.x.jar` to your project libraries or classpath

🔸 4. Compile & Run

javac DatabaseHelper.java LoginUI.java  
java LoginUI

──────────────────────────────────────────────



