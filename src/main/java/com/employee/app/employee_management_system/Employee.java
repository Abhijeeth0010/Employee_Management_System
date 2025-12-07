package com.employee.app.employee_management_system;

public class Employee {
    private int id;
    private String name;
    private int salary;
    private String email;
    private String phone;

    // Constructor
    public Employee(int id, String name, int salary, String email, String phone) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.email = email;
        this.phone = phone;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getSalary() { return salary; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    public void setName(String name) { this.name = name; }
    public void setSalary(int salary) { this.salary = salary; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
}
