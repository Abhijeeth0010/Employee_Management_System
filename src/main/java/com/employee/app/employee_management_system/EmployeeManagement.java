package com.employee.app.employee_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class EmployeeManagement {
	Scanner sc = new Scanner(System.in);
	String dpath = "com.mysql.cj.jdbc.Driver";
	Connection conn = null;
	String url = "jdbc:mysql://localhost:3306/employee";
	String user = "root";
	String password = "Root";
	
	public EmployeeManagement() {
		try {
			Class.forName(dpath);
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void getEmployeeById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "select * from employee_table where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3) + " " + rs.getString(4) + " " + rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	void getAllEmployee() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "select * from employee_table";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1) + ",  " + rs.getString(2) + ",  " + rs.getInt(3) + ",  " + rs.getString(4) + ",  " + rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	void addEmployee(int ID, String Name, int Salary, String Email, String Phone) {
		PreparedStatement ps = null;
		try {
			String sql = "insert into employee_table values(?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ID);
			ps.setString(2, Name);
			ps.setInt(3, Salary);
			ps.setString(4, Email);
			ps.setString(5, Phone);
			int nora = ps.executeUpdate();
			System.out.println(nora + " row added.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	void updateEmployee(int id) {
		try {
			System.out.println("A -> Update Name, B -> Update Phone, C -> Update Email");
			char ch = sc.next().charAt(0);
			switch(ch) {
			case 'A' : {
				String sql = "update employee_table set name = ? where id = ?";
				PreparedStatement ps1 = conn.prepareStatement(sql);
				System.out.println("Enter the name to update");
				String name = sc.next();
				ps1.setString(1, name);
				ps1.setInt(2, id);
				int nora = ps1.executeUpdate();
				System.out.println(nora + " row updated");
				ps1.close();
				break;
			}
			
			case 'B' : {
				String sql = "update employee_table set Phone = ? where id = ?";
				PreparedStatement ps2 = conn.prepareStatement(sql);
				System.out.println("Enter the Phone to update");
				String phone = sc.next();
				ps2.setString(1, phone);
				ps2.setInt(2, id);
				int nora = ps2.executeUpdate();
				System.out.println(nora + " row updated");
				ps2.close();
				break;
			}
			
			case 'C' : {
				String sql = "update employee_table set Email = ? where id = ?";
				PreparedStatement ps3 = conn.prepareStatement(sql);
				System.out.println("Enter the email to update");
				String email = sc.next();
				ps3.setString(1, email);
				ps3.setInt(2, id);
				int nora = ps3.executeUpdate();
				System.out.println(nora + " row updated");
				ps3.close();
				break;
			}
			
			default : {
				System.out.println("Invalid Choice.");
				updateEmployee(id);
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	void deleteEmployee(int id) {
		PreparedStatement ps = null;
		try {
			String sql = "delete from employee_table where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int nora = ps.executeUpdate();
			System.out.println(nora + " row deleted");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//GUI Based Methods.....
	public Employee findEmployeeById(int id) {
	    try {
	        String sql = "select * from employee_table where id=?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            return new Employee(
	                rs.getInt("id"),
	                rs.getString("name"),
	                rs.getInt("salary"),
	                rs.getString("email"),
	                rs.getString("phone")
	            );
	        }
	    } catch (Exception e) { e.printStackTrace(); }

	    return null; // not found
	}
	
	public List<Employee> findAllEmployees() {
	    List<Employee> list = new ArrayList<>();

	    try {
	        String sql = "select * from employee_table";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            list.add(new Employee(
	                rs.getInt("id"),
	                rs.getString("name"),
	                rs.getInt("salary"),
	                rs.getString("email"),
	                rs.getString("phone")
	            ));
	        }
	    } catch (Exception e) { e.printStackTrace(); }

	    return list;
	}

	public boolean insertEmployee(Employee emp) {
	    try {
	        String sql = "insert into employee_table values(?, ?, ?, ?, ?)";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, emp.getId());
	        ps.setString(2, emp.getName());
	        ps.setInt(3, emp.getSalary());
	        ps.setString(4, emp.getEmail());
	        ps.setString(5, emp.getPhone());

	        return ps.executeUpdate() > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public boolean updateName(int id, String name) {
	    return updateField("name", name, id);
	}

	public boolean updatePhone(int id, String phone) {
	    return updateField("phone", phone, id);
	}

	public boolean updateEmail(int id, String email) {
	    return updateField("email", email, id);
	}

	private boolean updateField(String column, String value, int id) {
	    try {
	        String sql = "update employee_table set " + column + "=? where id=?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, value);
	        ps.setInt(2, id);
	        return ps.executeUpdate() > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public boolean removeEmployee(int id) {
	    try {
	        String sql = "delete from employee_table where id=?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, id);
	        return ps.executeUpdate() > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}
