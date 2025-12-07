package com.employee.app.employee_management_system;

import javax.swing.*;
import java.awt.*;

public class AddEmployeePanel extends JPanel {

    private JTextField txtId, txtName, txtSalary, txtEmail, txtPhone;
    private EmployeeManagement em;

    public AddEmployeePanel() {

        em = new EmployeeManagement();  // connect to DB

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("Add New Employee", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblTitle, gbc);

        gbc.gridwidth = 1;

        // Row 1 - ID
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Employee ID:"), gbc);
        gbc.gridx = 1;
        txtId = new JTextField(15);
        add(txtId, gbc);

        // Row 2 - Name
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        txtName = new JTextField(15);
        add(txtName, gbc);

        // Row 3 - Salary
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Salary:"), gbc);
        gbc.gridx = 1;
        txtSalary = new JTextField(15);
        add(txtSalary, gbc);

        // Row 4 - Email
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        txtEmail = new JTextField(15);
        add(txtEmail, gbc);

        // Row 5 - Phone
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1;
        txtPhone = new JTextField(15);
        add(txtPhone, gbc);

        // Submit Button
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        JButton btnAdd = new JButton("Add Employee");
        add(btnAdd, gbc);

        // Response Label
        gbc.gridy++;
        JLabel lblResult = new JLabel("", SwingConstants.CENTER);
        lblResult.setForeground(Color.BLUE);
        add(lblResult, gbc);

        // Action
        btnAdd.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                String name = txtName.getText();
                int salary = Integer.parseInt(txtSalary.getText());
                String email = txtEmail.getText();
                String phone = txtPhone.getText();

                em.addEmployee(id, name, salary, email, phone);
                lblResult.setText("Employee Added Successfully!");

            } catch (Exception ex) {
                lblResult.setForeground(Color.RED);
                lblResult.setText("Error: Check Inputs");
            }
        });
    }
}
