package com.hb.picom.services.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.hb.picom.pojos.AdArea;

public class AdAreaServiceJDBC extends ServiceJDBC<AdArea> {

	public AdAreaServiceJDBC(Connection connection) {
		super(connection);
	}

	@Override
	public int createItem(AdArea item) {
		AdArea testAdArea = getItem(item.getId());
		if(testAdArea == null) {
			int createdRow = 0;
			try {
				 String query = "INSERT INTO ad_area("
				 		+ "id_ad, "
				 		+ "id_area)"
				 		+ " VALUES(?,?)";
				 ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
				 
				 ps.setInt(1, item.getAdId());
				 ps.setInt(2, item.getAreaId());
				 
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
		String query = "SELECT * FROM ad_area";
		 try {
			
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()){
				 AdArea adArea = new AdArea();
				
				 int id = rs.getInt("ad_area_id");
				 adArea.setId(id);
				 int idAd = rs.getInt("id_ad");
				 adArea.setAdId(idAd);
				 int idArea = rs.getInt("id_area");
				 adArea.setAreaId(idArea);
				 
				 
				 items.add(adArea);
			 }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public AdArea getItem(int id) {
		for (AdArea adArea : items) {
			if(adArea.getId() == id) {
				return adArea;
			}
		}
		return null;
	}

	@Override
	public boolean deleteItem(int id) {
		AdArea adAreaItem = null;
		int idx=0;
		for (AdArea adArea: items) {
			if(adArea.getId() == id) {
				adAreaItem = adArea;
				break;
			}
			idx++;
		}
		if(adAreaItem != null) {
			items.remove(idx);
			try {
				 String query = "DELETE FROM ad_area WHERE ad_area_id = ?";
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
	public int updateItem(int id, AdArea item) {
		int idx = 0;
		for (AdArea adArea: items) {
			if(adArea.getId() == id) {
				items.set(idx, item);
				try {
					 String query = "UPDATE ad_area SET"
					 		+ " id_ad = ?,"
					 		+ " id_area = ?"
					 		+ " WHERE ad_area_id = ?";
					 ps = connection.prepareStatement(query);
					 
					 ps.setInt(1, item.getAdId());
					 ps.setInt(2, item.getAreaId());
					 
					 ps.setInt(3, id);
					 
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
			 String query = "SELECT * FROM ad_area "
						+" WHERE ad_area_id = ?";
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
