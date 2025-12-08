# 🚀 Employee Management System (Java + Swing + Maven + MySQL)

A **desktop-based Employee Management System** built using:

* **Java**
* **Swing (JFrame GUI)**
* **Maven**
* **MySQL Database**

This project allows users to **Add, View, Search, Update, and Delete** employee records using a clean and simple GUI.

---

## 📌 Features

### ✅ **1. Add Employee**

Enter Name, Salary, Email, and Phone → Save to the MySQL database.

### ✅ **2. View All Employees**

Displays all employees from the database in a table format.

### ✅ **3. Search Employee by ID**

Enter employee ID → Fetch and show employee details.

### ✅ **4. Update Employee Details**

Modify existing employee information using ID.

### ✅ **5. Delete Employee**

Enter employee ID → Removes the record from the database.

---

## 📂 Project Structure

```
src/
 └── main/
     └── java/
         └── com.employee.app.employee_management_system/
             ├── MainFrame.java                 // Main GUI Window
             ├── Employee.java                  // Employee Model Class
             ├── EmployeeManagement.java        // DB Operations (CRUD)
             ├── AddEmployeePanel.java          // Add Employee GUI
             ├── ViewEmployeePanel.java         // View All Records GUI
             ├── GetByIdPanel.java              // Search by ID GUI
             ├── UpdateEmployeePanel.java       // Update GUI
             └── DeleteEmployeePanel.java       // Delete GUI
```

---

## 🛠️ Technologies Used

| Technology     | Purpose                           |
| -------------- | --------------------------------- |
| Java           | Core application logic            |
| Swing (JFrame) | Graphical User Interface          |
| Maven          | Project management & dependencies |
| MySQL          | Database                          |
| JDBC           | Database Connectivity             |

---

## 🗄️ Database Setup

Before running the application, create the database:

### **1️⃣ Create Database**

```sql
CREATE DATABASE employee;
USE employee;
```

### **2️⃣ Create Table**

```sql
CREATE TABLE employee_table (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    salary INT,
    email VARCHAR(255),
    phone VARCHAR(20)
);
```

---

## 🔌 Database Connection

Inside **EmployeeManagement.java**, update your connection details:

```java
conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/employee",
    "root",
    "your_password"
);
```

Make sure:
✔ MySQL server is running
✔ Username/password are correct

---

## ▶️ How to Run the Project

### **Method 1: Run from IDE (Eclipse, IntelliJ, VS Code)**

1. Open the project as a **Maven project**
2. Navigate to:

```
src/main/java/.../MainFrame.java
```

3. Right-click → **Run As → Java Application**

The GUI window will open.

---

### **Method 2: Run Using Maven Command**

```sh
mvn clean install
mvn exec:java -Dexec.mainClass="com.employee.app.employee_management_system.MainFrame"
```

(Requires `exec-maven-plugin`)

---

## 🧩 How the GUI Works

The application uses a **MainFrame** with a top navigation menu.

Each button loads a new JPanel:

* `AddEmployeePanel()`
* `ViewEmployeePanel()`
* `GetByIdPanel()`
* `UpdateEmployeePanel()`
* `DeleteEmployeePanel()`

Panels communicate with the backend using:

```java
EmployeeManagement em = new EmployeeManagement();
```

---

## 📷 Screenshots (Optional)

```
MainFrame Window
<img width="984" height="742" alt="image" src="https://github.com/user-attachments/assets/590763c8-0bfa-44f3-885b-fcf9319b8481" />

Add New Employee Window 
<img width="984" height="742" alt="Screenshot 2025-12-08 095351" src="https://github.com/user-attachments/assets/32ceec44-a2a0-4ffb-98c8-d82c3d37a6ff" />

Get Employee Details By Id Window
<img width="984" height="742" alt="Screenshot 2025-12-08 095402" src="https://github.com/user-attachments/assets/4228efb0-5678-4720-aec0-9fd99acfab75" />

Update Employee Details Window
<img width="984" height="742" alt="Screenshot 2025-12-08 095406" src="https://github.com/user-attachments/assets/36b618aa-51e7-4f4a-a261-1d036a3068a7" />

Delete Employee Window
<img width="984" height="742" alt="Screenshot 2025-12-08 095410" src="https://github.com/user-attachments/assets/c7d9aaed-c8d0-4bd7-a8e5-505395927eea" />


```

---

## ✨ Future Enhancements

* User authentication (Login/logout)
* Export employee data to Excel/PDF
* Search by name/mobile/email
* Pagination for large datasets

---

## 🤝 Contributing

Pull requests are welcome.
For major changes, open an issue to discuss what you’d like to modify.

---

## 📜 License

This project is open-source — feel free to use and modify.

---
