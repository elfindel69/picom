package com.hb.picom.services.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.hb.picom.pojos.City;

public class CityServiceJDBC extends ServiceJDBC<City> {

	public CityServiceJDBC(Connection connection) {
		super(connection);
	}
	
	@Override
	protected void initList() {
		String query = "SELECT city.*, country.country_name FROM city "
				+ "INNER JOIN country "
				+ "ON city.id_country = country.country_id";
		 try {
			
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()){
				 City city = new City();
				
				 int id2 = rs.getInt("city_id");
				 city.setID(id2);
				 String cityName = rs.getString("city_name");
				 city.setName(cityName);
				 String zipCode = rs.getString("city_zip_code");
				 city.setZipCode(zipCode);
				 String countryName = rs.getString("country_name");
				 city.setCountry(countryName);
				 items.add(city);
			 }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int createItem(City item) {
		City testCity = getItem(item.getId());
		if(testCity == null) {
			int createdRow = 0;
			try {
				int idCountry = getCountryId();
				 String query = "INSERT INTO city("
				 		+ "city_name, "
				 		+ "city_zip_code, "
				 		+ "id_country)"
				 		+ " VALUES(?,?,?)";
				 ps = connection.prepareStatement(query);
				 
				 ps.setString(1, item.getName());
				 ps.setString(2, item.getZipCode());
				 ps.setInt(3, idCountry);
				
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

	private int getCountryId() throws SQLException {
		int idCountry = 0;
		String queryString = "SELECT country_id FROM country WHERE country_name = ?";
		PreparedStatement ps = connection.prepareStatement(queryString);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			idCountry = rs.getInt(1);
		}
		return idCountry;
	}

	@Override
	public City getItem(int id) {
		for (City city : items) {
			if(city.getId() == id) {
				return city;
			}
		}
		return null;
	}

	@Override
	public void deleteItem(int id) {
		City itemCity = null;
		for (City city: items) {
			if(city.getId() == id) {
				itemCity = city;
				break;
			}
		}
		if(itemCity != null) {
			items.remove(itemCity);
			try {
				 String query = "DELETE FROM city WHERE city_id = ?";
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

	@Override
	public int updateItem(int id, City item) {
		int idx = 0;
		for (City city: items) {
			if(city.getId() == id) {
				items.set(idx, item);
				try {
					int countryId = getCountryId();
					
					 String query = "UPDATE city SET"
					 		+ " city_name = ?,"
					 		+ " city_zip_code = ?,"
					 		+ " id_country = ?"
					 		+ " WHERE city_id = ?";
					 ps = connection.prepareStatement(query);
					 
					 ps.setString(1, item.getName());
					 ps.setString(2, item.getZipCode());
					 ps.setInt(3, countryId);
					 
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
				
			}
			idx++;
		}
		return id;
		
	}

	@Override
	public void showItem(int id) {
		try {
			 String query = "SELECT city.*, country.country_name FROM city "
						+ "INNER JOIN country "
						+ "ON city.id_country = country.country_id"
						+" WHERE city.city_id = ?";
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
