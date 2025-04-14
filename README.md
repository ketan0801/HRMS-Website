# 👨‍💼 Human Resource Management System (HRMS) – Qubit

A **dynamic web application** developed using **Java (JDBC, Servlet, JSP)** along with **HTML5**, **CSS**, **JavaScript**, and **Bootstrap** that provides a complete **Human Resource Management System** for organizations to efficiently manage **employee data**, **project assignments**, **leave requests**, and **salary management**.

---

![Java](https://img.shields.io/badge/Backend-Java-blue?style=for-the-badge)
![JDBC](https://img.shields.io/badge/JDBC-MySQL-informational?style=for-the-badge)
![Servlet](https://img.shields.io/badge/Servlet-JSP-success?style=for-the-badge)
![Bootstrap](https://img.shields.io/badge/Frontend-Bootstrap-purple?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

---

## 🧠 Project Name
**Qubit - Human Resource Management Website**

---

## 📌 Features Overview

This project includes **two main user modules**:

### ✅ 1. Admin Module
- Secure login (assigned from database manually)
- Admin Dashboard
- Assign Projects to Employees
- Assign Salary (including PF, tax, allowances)
- Approve / Deny Leave Requests
- View:
  - List of all Employees
  - Project Details of employees

### ✅ 2. Employee Module
- Employee Registration and Login
- View Assigned Projects
- View Salary Details
- Apply for Leave
- Personal profile view

---

## 🛠️ Technology Stack

| Layer         | Technology Used                     |
|---------------|-------------------------------------|
| Programming   | Java (JDBC, Servlet, JSP)           |
| Database      | MySQL                               |
| Frontend      | HTML5, CSS3, JavaScript, Bootstrap  |
| Server        | Apache Tomcat                       |
| Tools         | Eclipse IDE, MySQL Workbench        |

---

## ⚙️ How to Run

### Prerequisites:
- Java JDK 17+
- Apache Tomcat 10+
- Eclipse IDE or IntelliJ
- MySQL Server

---

### 🧩 Setup Instructions

```bash
# Step 1: Clone the repository
git clone https://github.com/ketan0801/Qubit-HRMS.git
cd Qubit-HRMS

# Step 2: Import the project into Eclipse as a Dynamic Web Project

# Step 3: Setup your MySQL database
- Create a database named `Qubit`
- Run the SQL scripts to create `Admin`, `Employee`, `Projects`, `LeaveRequests`, and `Salary` tables

# Step 4: Configure DB credentials in your servlet/database connection file

# Step 5: Run on Apache Tomcat Server
- Right click on project → Run As → Run on Server

# Step 6: Access the app at:
http://localhost:8080/Qubit/
