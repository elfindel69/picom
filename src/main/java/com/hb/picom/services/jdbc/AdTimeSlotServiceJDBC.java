package com.hb.picom.services.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.hb.picom.pojos.AdTimeSlot;

public class AdTimeSlotServiceJDBC extends ServiceJDBC<AdTimeSlot> {

	public AdTimeSlotServiceJDBC(Connection connection) {
		super(connection);
	}

	@Override
	public int createItem(AdTimeSlot item) {
		AdTimeSlot testAdTS = getItem(item.getId());
		if(testAdTS == null) {
			int createdRow = 0;
			try {
				 String query = "INSERT INTO ad_time_slot("
				 		+ "id_time_slot, "
				 		+ "id_ad)"
				 		+ " VALUES(?,?)";
				 ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
				 
				 ps.setInt(1, item.getTimeSlotId());
				 ps.setInt(2, item.getAdId());
				 
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
		String query = "SELECT * FROM ad_time_slot";
		 try {
			
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()){
				 AdTimeSlot adTS = new AdTimeSlot();
				
				 int id = rs.getInt("ad_time_slot_id");
				 adTS.setId(id);
				 int idTimeSlot = rs.getInt("id_time_slot");
				 adTS.setTimeSlotId(idTimeSlot);
				 int idAd = rs.getInt("id_ad");
				 adTS.setAdId(idAd);
				 
				 
				 items.add(adTS);
			 }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public AdTimeSlot getItem(int id) {
		for (AdTimeSlot adTS : items) {
			if(adTS.getId() == id) {
				return adTS;
			}
		}
		return null;
	}

	@Override
	public void deleteItem(int id) {
		AdTimeSlot adTSItem = null;
		for (AdTimeSlot adTS: items) {
			if(adTS.getId() == id) {
				adTSItem = adTS;
				break;
			}
		}
		if(adTSItem != null) {
			items.remove(adTSItem);
			try {
				 String query = "DELETE FROM ad_time_slot WHERE ad_time_slot_id = ?";
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
		}
		
	}

	@Override
	public int updateItem(int id, AdTimeSlot item) {
		int idx = 0;
		for (AdTimeSlot adTS: items) {
			if(adTS.getId() == id) {
				items.set(idx, item);
				try {
					 String query = "UPDATE ad_time_slot SET"
					 		+ " id_time_slot = ?,"
					 		+ " id_ad = ?";
					 ps = connection.prepareStatement(query);
					 
					 ps.setInt(1, item.getTimeSlotId());
					 ps.setInt(2, item.getAdId());
					 
					 ps.setInt(3, item.getId());
					 
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
			 String query = "SELECT * FROM ad_time_slot "
						+" WHERE ad_time_slot_id = ?";
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
