package com.employee.app.employee_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;

public class UpdateEmployeePanel extends JPanel {

    private JTextField txtId, txtValue;
    private JComboBox<String> updateChoice;
    private EmployeeManagement em;

    public UpdateEmployeePanel() {
        em = new EmployeeManagement();
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("Update Employee Details");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblTitle, gbc);

        gbc.gridwidth = 1;

        // ID Field
        JLabel lblId = new JLabel("Enter Employee ID:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblId, gbc);

        txtId = new JTextField(15);
        gbc.gridx = 1;
        add(txtId, gbc);

        // Update Choice
        JLabel lblChoice = new JLabel("Update Field:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblChoice, gbc);

        updateChoice = new JComboBox<>(new String[]{"Name", "Phone", "Email"});
        gbc.gridx = 1;
        add(updateChoice, gbc);

        // New Value
        JLabel lblValue = new JLabel("New Value:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblValue, gbc);

        txtValue = new JTextField(15);
        gbc.gridx = 1;
        add(txtValue, gbc);

        // Update Button
        JButton btnUpdate = new JButton("Update");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(btnUpdate, gbc);

        btnUpdate.addActionListener(e -> updateEmployee());
    }

    private void updateEmployee() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            String field = updateChoice.getSelectedItem().toString();
            String newValue = txtValue.getText().trim();

            if (newValue.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a value!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String sql = "";
            switch (field) {
                case "Name":
                    sql = "update employee_table set Name = ? where Id = ?";
                    break;
                case "Phone":
                    sql = "update employee_table set Phone = ? where Id = ?";
                    break;
                case "Email":
                    sql = "update employee_table set Email = ? where Id = ?";
                    break;
            }

            PreparedStatement ps = em.conn.prepareStatement(sql);
            ps.setString(1, newValue);
            ps.setInt(2, id);

            int updated = ps.executeUpdate();

            if (updated > 0) {
                JOptionPane.showMessageDialog(this, "Employee updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Employee ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }

            ps.close();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Update failed!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
