package com.employee.app.employee_management_system;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GetByIdPanel extends JPanel {

    private JTextField txtId;
    private JTextField txtName, txtSalary, txtEmail, txtPhone;
    private EmployeeManagement em;

    public GetByIdPanel() {
        em = new EmployeeManagement();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Get Employee By ID", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;

        // ID row
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(new JLabel("Employee ID:"), gbc);

        txtId = new JTextField(15);
        gbc.gridx = 1;
        add(txtId, gbc);

        // Fetch button
        JButton btnFetch = new JButton("Fetch");
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(btnFetch, gbc);

        // Result fields
        gbc.gridwidth = 1;
        gbc.gridy = 3;
        gbc.gridx = 0;
        add(new JLabel("Name:"), gbc);
        txtName = new JTextField(20);
        txtName.setEditable(false);
        gbc.gridx = 1;
        add(txtName, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        add(new JLabel("Salary:"), gbc);
        txtSalary = new JTextField(20);
        txtSalary.setEditable(false);
        gbc.gridx = 1;
        add(txtSalary, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        add(new JLabel("Email:"), gbc);
        txtEmail = new JTextField(20);
        txtEmail.setEditable(false);
        gbc.gridx = 1;
        add(txtEmail, gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        add(new JLabel("Phone:"), gbc);
        txtPhone = new JTextField(20);
        txtPhone.setEditable(false);
        gbc.gridx = 1;
        add(txtPhone, gbc);

        // Clear button (optional)
        JButton btnClear = new JButton("Clear");
        gbc.gridy = 7;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(btnClear, gbc);

        // Actions
        btnFetch.addActionListener(e -> fetchEmployee());
        btnClear.addActionListener(e -> clearFields());
    }

    private void fetchEmployee() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String idText = txtId.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter an ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = Integer.parseInt(idText);

            String sql = "SELECT * FROM employee_table WHERE id = ?";
            ps = em.conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                txtName.setText(rs.getString("Name"));
                txtSalary.setText(String.valueOf(rs.getInt("Salary")));
                txtEmail.setText(rs.getString("Email"));
                txtPhone.setText(rs.getString("Phone"));
            } else {
                JOptionPane.showMessageDialog(this, "Employee not found.", "Info", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID. Enter numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error while fetching employee.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (ps != null) ps.close(); } catch (Exception ignored) {}
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtSalary.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
    }
}
