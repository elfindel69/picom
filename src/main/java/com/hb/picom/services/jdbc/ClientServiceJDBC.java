package com.hb.picom.services.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.hb.picom.pojos.City;
import com.hb.picom.pojos.Client;

public class ClientServiceJDBC extends ServiceJDBC<Client> {

	public ClientServiceJDBC(Connection connection) {
		super(connection);
		initList();
	}
	
	@Override
	protected void initList() {
		String query = "SELECT * FROM client";
		 try {
			
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()){
				 Client client = new Client();
				 int id = rs.getInt("client_id");
				 client.setId(id);
				 String firstName = rs.getString("client_first_name");
				 client.setFirstName(firstName);
				 String lastName = rs.getString("client_last_name");
				 client.setLastName(lastName);
				 Timestamp creationDate = rs.getTimestamp("client_creation_date");
				 client.setCreationDate(creationDate.toLocalDateTime());
				 String email = rs.getString("client_email");
				 client.setEmail(email);
				 String password = rs.getString("client_password");
				 client.setPassword(password);
				 String phone = rs.getString("client_phone");
				 client.setPhone(phone);
				 String creditCard = rs.getString("client_credit_card");
				 client.setCreditCardNb(creditCard);
				 String expirationDate = rs.getString("client_expiration_date");
				 client.setExpirationDate(expirationDate);
				 String cvvCode = rs.getString("client_CVV_code");
				 client.setCVVCode(cvvCode);
				 String companyName = rs.getString("client_company_name");
				 client.setCompanyName(companyName);
				 String companySiret = rs.getString("client_company_SIRET");
				 client.setCompanySIRET(companySiret);
				 String companyAddress = rs.getString("client_address");
				 client.setCompanyAddress(companyAddress);
			 
				 String query2 = "SELECT city.*, country.country_name FROM client "+
						 "INNER JOIN city ON client.id_city = city.city_id INNER JOIN country "
						 + "ON city.id_country = country.country_id"
						 + " WHERE client.client_id = ?";
				 PreparedStatement ps2 = connection.prepareStatement(query2);
				 ps2.setInt(1, id);
				 
				 ResultSet rs2 = ps2.executeQuery();
				 
				 if(rs2.next()) {
					 City city = new City();
					 int id2 = rs2.getInt("city_id");
					 city.setID(id2);
					 String cityName = rs2.getString("city_name");
					 city.setName(cityName);
					 String zipCode = rs2.getString("city_zip_code");
					 city.setZipCode(zipCode);
					 String countryName = rs2.getString("country_name");
					 city.setCountry(countryName);
					 client.setCompanyCity(city);
				 }
				items.add(client);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int createItem(Client item) {
		Client testClient = getItem(item.getId());
		if(testClient == null) {
			int createdRow = 0;
			try {
				 String query = "INSERT INTO client("
				 		+ "client_first_name, "
				 		+ "client_last_name, "
				 		+ "client_email, "
				 		+ "client_password, "
				 		+ "client_phone, "
				 		+ "client_company_name, "
				 		+ "client_address, "
				 		+ "id_city)"
				 		+ " VALUES(?,?,?,?,?,?,?,?)";
				 ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
				 
				 ps.setString(1, item.getFirstName());
				 ps.setString(2, item.getLastName());
				 ps.setString(3, item.getEmail());
				 ps.setString(4, item.getPassword());
				 ps.setString(5, item.getPhone());
				 ps.setString(6, item.getCompanyName());
				 ps.setString(7, item.getCompanyAddress());
				 ps.setInt(8, item.getCompanyCity().getId());
				
				 int row = ps.executeUpdate();
				if(row == 1) {
					try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
			            if (generatedKeys.next()) {
			            	createdRow = generatedKeys.getInt(1);
			            	item.setId(createdRow);
			            }
			            else {
			                throw new SQLException("Creating user failed, no ID obtained.");
			            }
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
		else{
			return updateItem(item.getId(), item);
		}
	}

	@Override
	public Client getItem(int id) {
		for (Client client : items) {
			if(client.getId() == id) {
				return client;
			}
		}
		return null;
		
	}

	@Override
	public boolean deleteItem(int id) {
		Client itemClient = null;
		int idx = 0;
		for (Client client: items) {
			if(client.getId() == id) {
				itemClient = client;
				break;
			}
			idx++;
		}
		if(itemClient != null) {
			items.remove(idx);
			try {
				 String query = "DELETE FROM client WHERE client_id = ?";
				 ps = connection.prepareStatement(query);
				 
				 ps.setInt(1, id);
				
				 int row = ps.executeUpdate();

	             // rows affected
				 if(row  == 1) {
					  System.out.println("ligne supprimée: "+id);
				 }
				 
				
			 } catch (SQLException ex) {
			     // handle any errors
			     System.out.println("SQLException: " + ex.getMessage());
			     System.out.println("SQLState: " + ex.getSQLState());
			     System.out.println("VendorError: " + ex.getErrorCode());
			 }
			 finally {
			    // it is a good idea to release
			    // resources in a finally{} block
			    // in reverse-order of their creation
			    // if they are no-longer needed

			    if (rs != null) {
			        try {
			            rs.close();
			        } catch (SQLException sqlEx) { } // ignore

			        rs = null;
			    }

			    if (ps != null) {
			        try {
			            ps.close();
			        } catch (SQLException sqlEx) { } // ignore

			        ps = null;
			    }
			}
			return true;
		}
		return false;
	}
	
	@Override
	public int updateItem(int id, Client item) {
		int idx = 0;
		for (Client client: items) {
			if(client.getId() == id) {
				items.set(idx, item);
				try {
					 String query = "UPDATE client SET"
					 		+ " client_first_name = ?,"
					 		+ " client_last_name = ?,"
					 		+ " client_email = ?,"
					 		+ " client_phone = ?,"
					 		+ " client_company_name = ?,"
					 		+ " client_address = ?,"
					 		+ " id_city = ?"
					 		+ " WHERE client_id = ?";
					 ps = connection.prepareStatement(query);
					 
					 ps.setString(1, item.getFirstName());
					 ps.setString(2, item.getLastName());
					 ps.setString(3, item.getEmail());
					 ps.setString(4, item.getPhone());
					 ps.setString(5, item.getCompanyName());
					 ps.setString(6, item.getCompanyAddress());
					 ps.setInt(7, item.getCompanyCity().getId());
					 ps.setInt(8, id);
					 
					 int row = ps.executeUpdate();

					// rows affected
					 if(row  == 1) {
						  System.out.println("ligne modifiée: "+id);
					 }
					
				 } catch (SQLException ex) {
				     // handle any errors
				     System.out.println("SQLException: " + ex.getMessage());
				     System.out.println("SQLState: " + ex.getSQLState());
				     System.out.println("VendorError: " + ex.getErrorCode());
				 }
				 finally {
				    // it is a good idea to release
				    // resources in a finally{} block
				    // in reverse-order of their creation
				    // if they are no-longer needed

				    if (rs != null) {
				        try {
				            rs.close();
				        } catch (SQLException sqlEx) { } // ignore

				        rs = null;
				    }

				    if (ps != null) {
				        try {
				            ps.close();
				        } catch (SQLException sqlEx) { } // ignore

				        ps = null;
				    }
				}
				break;
			}
			idx++;
		}
		return id;
		
	}

	@Override
	public void showItem(int id) {
		 try {
			 String query = "SELECT client_id,client_first_name,client_last_name,client_email,"
			 		+ "client_phone,client_company_name,client_address,city_name,country_name"
			 		+ " FROM client INNER JOIN city ON client.id_city = city.city_id "
			 		+ "INNER JOIN country ON city.id_country= country.country_id"
			 		+ " WHERE client.client_id = ?";
			 ps = connection.prepareStatement(query);
			 ps.setInt(1, id);
			 
			 rs = ps.executeQuery();
			 ResultSetMetaData metaData  = rs.getMetaData();
			 int columsNb = metaData.getColumnCount();
			 showColumnNames(metaData, columsNb);
			 
			 
			 while(rs.next()){
				 for (int i = 1;i<=columsNb;i++) {
		        	  if(i>1) {
		        		  System.out.print("|");
		        		
		        	  }
		        	  Object value = rs.getObject(i);
		        	  System.out.print(value);
		          }
		            System.out.println("");
		            
	        }
			
		 } catch (SQLException ex) {
		     // handle any errors
		     System.out.println("SQLException: " + ex.getMessage());
		     System.out.println("SQLState: " + ex.getSQLState());
		     System.out.println("VendorError: " + ex.getErrorCode());
		 }
		 finally {
		    // it is a good idea to release
		    // resources in a finally{} block
		    // in reverse-order of their creation
		    // if they are no-longer needed

		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException sqlEx) { } // ignore

		        rs = null;
		    }

		    if (ps != null) {
		        try {
		            ps.close();
		        } catch (SQLException sqlEx) { } // ignore

		        ps = null;
		    }
		}
	}

}
