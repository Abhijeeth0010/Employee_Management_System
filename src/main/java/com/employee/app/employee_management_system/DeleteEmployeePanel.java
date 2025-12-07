package com.employee.app.employee_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;

public class DeleteEmployeePanel extends JPanel {

    private JTextField txtId;
    private EmployeeManagement em;

    public DeleteEmployeePanel() {
        em = new EmployeeManagement();
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Panel Title
        JLabel lblTitle = new JLabel("Delete Employee");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblTitle, gbc);

        gbc.gridwidth = 1;

        // ID Label
        JLabel lblId = new JLabel("Enter Employee ID:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblId, gbc);

        // ID Input
        txtId = new JTextField(15);
        gbc.gridx = 1;
        add(txtId, gbc);

        // Delete Button
        JButton btnDelete = new JButton("Delete");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(btnDelete, gbc);

        btnDelete.addActionListener(e -> deleteEmployee());
    }

    private void deleteEmployee() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());

            String sql = "DELETE FROM employee_table WHERE id = ?";
            PreparedStatement ps = em.conn.prepareStatement(sql);
            ps.setInt(1, id);

            int deleted = ps.executeUpdate();

            if (deleted > 0) {
                JOptionPane.showMessageDialog(this, "Employee deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Employee ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }

            ps.close();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID! Enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Delete operation failed!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
