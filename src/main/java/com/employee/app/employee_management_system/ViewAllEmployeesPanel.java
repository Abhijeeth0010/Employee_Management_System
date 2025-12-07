package com.employee.app.employee_management_system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewAllEmployeesPanel extends JPanel {

    private EmployeeManagement em;
    private JTable table;
    private DefaultTableModel model;

    public ViewAllEmployeesPanel() {
        em = new EmployeeManagement();

        setLayout(new BorderLayout(20, 20));

        JLabel title = new JLabel("All Employees", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        add(title, BorderLayout.NORTH);

        // Table Setup
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "Name", "Salary", "Email", "Phone"});

        table = new JTable(model);
        table.setRowHeight(25);

        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        // Load Button
        JButton btnLoad = new JButton("Load Employees");
        btnLoad.setFont(new Font("Arial", Font.PLAIN, 16));
        add(btnLoad, BorderLayout.SOUTH);

        btnLoad.addActionListener(e -> loadData());
    }

    private void loadData() {
        try {
            model.setRowCount(0); // Clear existing rows

            String sql = "select * from employee_table";
            PreparedStatement ps = em.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] row = new Object[]{
                    rs.getInt("ID"),
                    rs.getString("Name"),
                    rs.getInt("Salary"),
                    rs.getString("Email"),
                    rs.getString("Phone")
                };
                model.addRow(row);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
