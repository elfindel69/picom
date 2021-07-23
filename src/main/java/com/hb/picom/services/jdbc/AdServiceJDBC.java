package com.hb.picom.services.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.hb.picom.pojos.Advertisment;

public class AdServiceJDBC extends ServiceJDBC<Advertisment> {

	public AdServiceJDBC(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int createItem(Advertisment item) {
		Advertisment testAd = getItem(item.getId());
		if(testAd == null) {
			int createdRow = 0;
			try {
				 String query = "INSERT INTO advertisment("
				 		+ "id_client, "
				 		+ "ad_is_active,"
				 		+ "ad_title,"
				 		+ "ad_description,"
				 		+ "ad_image_url,"
				 		+ "ad_html_text,"
				 		+ "ad_start_date,"
				 		+ "ad_end_date)"
				 		+ " VALUES(?,?,?,?,?,?,?,?)";
				 ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
				 
				 ps.setInt(1, item.getClientID());
				 ps.setBoolean(2, item.isActive());
				 ps.setString(3, item.getTitle());
				 ps.setString(4, item.getDescription());
				 ps.setString(5, item.getUrlImage());
				 ps.setString(6, item.getHtmlText());
				 ps.setTimestamp(7, Timestamp.valueOf(item.getStartDate()));
				 ps.setTimestamp(8, Timestamp.valueOf(item.getEndDate()));
				 
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
		else {
			return updateItem(item.getId(), item);
		}
	}

	@Override
	protected void initList() {
		String query = "SELECT * FROM advertisment ";
		 try {
			
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()){
				 Advertisment ad = new Advertisment();
				
				 int id = rs.getInt("ad_id");
				 ad.setId(id);
				 int clientId = rs.getInt("id_client");
				 ad.setClientID(clientId);
				 
				 Boolean isActive = rs.getBoolean("ad_is_active");
				 ad.setActive(isActive);
				 String title = rs.getString("ad_title");
				 ad.setTitle(title);
				 String desc = rs.getString("ad_description");
				 ad.setDescription(desc);
				 String urlImage = rs.getString("ad_image_url");
				 ad.setUrlImage(urlImage);
				 String htmlText = rs.getString("ad_html_text");
				 ad.setHtmlText(htmlText);
				 Timestamp startDate = rs.getTimestamp("ad_start_date");
				 ad.setStartDate(startDate.toLocalDateTime());
				 Timestamp endDate = rs.getTimestamp("ad_end_date");
				 ad.setEndDate(endDate.toLocalDateTime());
				 
				 items.add(ad);
			 }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public Advertisment getItem(int id) {
		for (Advertisment ad : items) {
			if(ad.getId() == id) {
				return ad;
			}
		}
		return null;
	}

	@Override
	public boolean deleteItem(int id) {
		Advertisment itemAd = null;
		int idx = 0;
		for (Advertisment ad: items) {
			if(ad.getId() == id) {
				itemAd = ad;
				break;
			}
			idx++;
		}
		if(itemAd != null) {
			items.remove(idx);
			try {
				 String query = "DELETE FROM advertisment WHERE ad_id = ?";
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
	public int updateItem(int id, Advertisment item) {
		int idx = 0;
		for (Advertisment ad: items) {
			if(ad.getId() == id) {
				items.set(idx, item);
				try {
					 String query = "UPDATE advertisment SET"
					 		+ " id_client = ?,"
					 		+ " ad_is_active = ?,"
					 		+ " ad_title = ?,"
					 		+ " ad_description = ?,"
					 		+ " ad_image_url = ?,"
					 		+ " ad_html_text = ?,"
					 		+ " ad_start_date = ?,"
					 		+ " ad_end_date = ?"
					 		+ " WHERE ad_id = ?";
					 ps = connection.prepareStatement(query);
					 
					 ps.setInt(1, item.getClientID());
					 ps.setBoolean(2, item.isActive());
					 ps.setString(3, item.getTitle());
					 ps.setString(4, item.getDescription());
					 ps.setString(5, item.getUrlImage());
					 ps.setString(6, item.getHtmlText());
					 ps.setTimestamp(7, Timestamp.valueOf(item.getStartDate()));
					 ps.setTimestamp(8, Timestamp.valueOf(item.getEndDate()));
					 ps.setInt(9, id);
					 
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
			 String query = "SELECT * FROM advertisment "
						+" WHERE ad_id = ?";
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
