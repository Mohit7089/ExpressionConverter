#ExpressionConveter
Project Overview
This project is a ExpressionConveter developed in Java. It consists of two main components:

1. Login System (By Mohit Soni)
Built using Java Swing and AWT for the graphical user interface.
Uses JDBC for database connectivity to manage user authentication.
Features:
User registration and login functionality.
Secure password storage and retrieval.
Error handling for incorrect credentials.
2. Expression Converter (By Piyush Agnihotri)
Developed using Java Swing for GUI.
Implements Multithreading for efficient processing.
Converts mathematical expressions between different notations (Infix, Prefix, and Postfix).
Features:
Real-time conversion of expressions.
Supports step-by-step evaluation for better understanding.


#How to run this project
1.Database Setup:


CREATE DATABASE userdb;

 USE userdb;

CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL
);


2.Update DatabaseHelper.java: Edit the following lines with your credentials:

private static final String URL = "jdbc:mysql://localhost:3306/userdb";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";


3.Add MySQL JDBC Driver to Classpath

Download from MySQL Connector/J

Add mysql-connector-java-x.x.x.jar to your project libraries

4.Run the Project
javac DatabaseHelper.java LoginUI.java
java LoginUI



