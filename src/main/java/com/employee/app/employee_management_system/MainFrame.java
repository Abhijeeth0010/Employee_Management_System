package com.employee.app.employee_management_system;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {

    private JPanel mainPanel; // Dynamic panel container

    public MainFrame() {
        setTitle("Employee Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // TOP MENU PANEL
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout());

        JButton btnAdd = new JButton("Add Employee");
        JButton btnView = new JButton("View All");
        JButton btnSearch = new JButton("Search by ID");
        JButton btnUpdate = new JButton("Update Employee");
        JButton btnDelete = new JButton("Delete Employee");

        // Add buttons to menu
        menuPanel.add(btnAdd);
        menuPanel.add(btnView);
        menuPanel.add(btnSearch);
        menuPanel.add(btnUpdate);
        menuPanel.add(btnDelete);

        add(menuPanel, BorderLayout.NORTH);

        // MAIN PANEL (Dynamic content goes here)
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // BUTTON ACTION LISTENERS → swap the center panel
        btnAdd.addActionListener(e -> switchPanel(new AddEmployeePanel()));
        btnView.addActionListener(e -> switchPanel(new ViewAllEmployeesPanel()));
        btnSearch.addActionListener(e -> switchPanel(new GetByIdPanel()));
        btnUpdate.addActionListener(e -> switchPanel(new UpdateEmployeePanel()));
        btnDelete.addActionListener(e -> switchPanel(new DeleteEmployeePanel()));
    }

    // Method to update the mainPanel with different panels
    private void switchPanel(JPanel newPanel) {
        mainPanel.removeAll();
        mainPanel.add(newPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // MAIN METHOD → starts the GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
