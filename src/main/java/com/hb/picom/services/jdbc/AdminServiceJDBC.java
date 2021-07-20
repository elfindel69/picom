package com.hb.picom.services.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import com.hb.picom.pojos.Admin;

public class AdminServiceJDBC extends ServiceJDBC<Admin> {

	public AdminServiceJDBC(Connection connection) {
		super(connection);
	}

	@Override
	public int createItem(Admin item) {
		Admin testAdmin = getItem(item.getId());
		if(testAdmin == null) {
			int createdRow = 0;
			try {
				 String query = "INSERT INTO admin("
				 		+ "city_login, "
				 		+ "city_password)"
				 		+ " VALUES(?,?)";
				 ps = connection.prepareStatement(query);
				 
				 ps.setString(1, item.getLogin());
				 ps.setString(2, item.getPassword());
				
				 int row = ps.executeUpdate();
				 
				if(row == 1) {
					stmt = connection.createStatement();
					 rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
					 if(rs.next()) {
						 createdRow = rs.getInt(1);
						 System.out.println("ligne insérée: "+createdRow);
					 }
					items.add(item);
				   
				}
			 } catch (SQLException ex) {
			     // handle any errors
			     System.out.println("SQLException: " + ex.getMessage());
			     System.out.println("SQLState: " + ex.getSQLState());
			     System.out.println("VendorError: " + ex.getErrorCode());
			 }
			 finally {

			    if (rs != null) {
			        try {
			            rs.close();
			        } catch (SQLException sqlEx) { 
			        	sqlEx.printStackTrace();
			        } 
			        rs = null;
			    }

			    if (ps != null) {
			        try {
			            ps.close();
			        } catch (SQLException sqlEx) {
			        	sqlEx.printStackTrace();
			        } 
			        ps = null;
			    }
			    
			    if (stmt != null) {
			        try {
			        	stmt.close();
			        } catch (SQLException sqlEx) { 
			        	sqlEx.printStackTrace();
			        }
			        stmt = null;
			    }
			    
			}
			return createdRow;
		}
		else {
			return updateItem(item.getId(), item);
		}
	}

	@Override
	protected void initList() {
		String query = "SELECT * FROM admin ";
		 try {
			
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()){
				 Admin admin = new Admin();
				
				 int id = rs.getInt("admin_id");
				 admin.setId(id);
				 String login = rs.getString("admin_login");
				 admin.setLogin(login);
				 String password = rs.getString("admin_password");
				 admin.setPassword(password);
				 
				 items.add(admin);
			 }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Admin getItem(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteItem(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int updateItem(int id, Admin item) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void showItem(int id) {
		// TODO Auto-generated method stub
		
	}

}
