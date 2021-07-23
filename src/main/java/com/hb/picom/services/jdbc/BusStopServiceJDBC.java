package com.hb.picom.services.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.hb.picom.pojos.BusStop;

public class BusStopServiceJDBC extends ServiceJDBC<BusStop> {

	public BusStopServiceJDBC(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int createItem(BusStop item) {
		BusStop testStop = getItem(item.getId());
		if(testStop == null) {
			int createdRow = 0;
			try {
				 String query = "INSERT INTO bus_stop("
				 		+ "id_area, "
				 		+ "bus_stop_name,"
				 		+ "bus_stop_ip,"
				 		+ "bus_stop_gps)"
				 		+ " VALUES(?,?,?,?)";
				 ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
				 
				 ps.setInt(1, item.getAreaID());
				 ps.setString(2, item.getName());
				 ps.setString(3, item.getIPAddress());
				 ps.setString(4, item.getGps());
				 
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
		String query = "SELECT * FROM bus_stop ";
		 try {
			
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()){
				 BusStop stop = new BusStop();
				
				 int id = rs.getInt("bus_stop_id");
					stop.setId(id);
				int areaID = rs.getInt("id_area");
				stop.setAreaId(areaID);
				 String name = rs.getString("bus_stop_name");
				 stop.setName(name);
				 String ipAddress = rs.getString("bus_stop_ip");
				 stop.setIPAddress(ipAddress);
				 String gps = rs.getString("bus_stop_gps");
				 stop.setGps(gps);
				 
				 items.add(stop);
			 }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public BusStop getItem(int id) {
		for (BusStop stop : items) {
			if(stop.getId() == id) {
				return stop;
			}
		}
		return null;
	}

	@Override
	public boolean deleteItem(int id) {
		BusStop itemStop = null;
		int idx = 0;
		for (BusStop stop: items) {
			if(stop.getId() == id) {
				itemStop = stop;
				break;
			}
			idx++;
		}
		if(itemStop != null) {
			items.remove(idx);
			try {
				 String query = "DELETE FROM bus_stop WHERE bus_stop_id = ?";
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
	public int updateItem(int id, BusStop item) {
		int idx = 0;
		for (BusStop stop: items) {
			if(stop.getId() == id) {
				items.set(idx, item);
				try {
					 String query = "UPDATE bus_stop SET"
					 		+ " id_area = ?,"
					 		+ " bus_stop_name = ?,"
					 		+ " bus_stop_ip = ?,"
					 		+ " bus_stop_gps = ?"
					 		+ " WHERE bus_stop_id = ?";
					 ps = connection.prepareStatement(query);
					 
					 ps.setInt(1, item.getAreaID());
					 ps.setString(2, item.getName());
					 ps.setString(3, item.getIPAddress());
					 ps.setString(4, item.getGps());
					 ps.setInt(5, id);
					 
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
			 String query = "SELECT * FROM bus_stop "
						+" WHERE bus_stop_id = ?";
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
