package com.hb.picom.services.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import com.hb.picom.pojos.TimeSlot;

public class TimeSlotServiceJDBC extends ServiceJDBC<TimeSlot> {

	public TimeSlotServiceJDBC(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int createItem(TimeSlot item) {
		TimeSlot testTS = getItem(item.getId());
		if(testTS == null) {
			int createdRow = 0;
			try {
				 String query = "INSERT INTO time_slot("
				 		+ "time_slot_start_time,"
				 		+ "time_slot_end_time,"
				 		+ "time_slot_price)"
				 		+ " VALUES(?,?,?)";
				 ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
				 
				 ps.setTime(1, Time.valueOf(item.getStartTime()));
				 ps.setTime(1, Time.valueOf(item.getEndTime()));
				 ps.setDouble(3, item.getPrice());
				
				 
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
		String query = "SELECT * FROM time_slot ";
		 try {
			
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()){
				 TimeSlot timeSlot = new TimeSlot();
				
				int id = rs.getInt("time_slot_id");
				 timeSlot.setId(id);
				 Time startTime = rs.getTime("time_slot_start_time");
				 timeSlot.setStartTime(startTime.toLocalTime());
				 Time endTime = rs.getTime("time_slot_end_time");
				 timeSlot.setEndTime(endTime.toLocalTime());
				 double basePrice = rs.getDouble("time_slot_price");
				 timeSlot.setPrice(basePrice);
				 
				 
				 items.add(timeSlot);
			 }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public TimeSlot getItem(int id) {
		for (TimeSlot timeSlot : items) {
			if(timeSlot.getId() == id) {
				return timeSlot;
			}
		}
		return null;
	}

	@Override
	public boolean deleteItem(int id) {
		TimeSlot itemTS= null;
		int idx = 0;
		for (TimeSlot timeSlot: items) {
			if(timeSlot.getId() == id) {
				itemTS = timeSlot;
				break;
			}
			idx++;
		}
		if(itemTS != null) {
			items.remove(idx);
			try {
				 String query = "DELETE FROM time_slot WHERE time_slot_id = ?";
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
	public int updateItem(int id, TimeSlot item) {
		int idx = 0;
		for (TimeSlot timeSlot: items) {
			if(timeSlot.getId() == id) {
				items.set(idx, item);
				try {
					 String query = "UPDATE time_slot SET"
					 		+ " time_slot_start_time = ?,"
					 		+ " time_slot_end_time = ?,"
					 		+ " time_slot_price = ?"
					 		+ " WHERE time_slot_id = ?";
					 ps = connection.prepareStatement(query);
					 
					 ps.setTime(1, Time.valueOf(item.getStartTime()));
					 ps.setTime(1, Time.valueOf(item.getEndTime()));
					 ps.setDouble(3, item.getPrice());
					
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
			 String query = "SELECT * FROM time_slot "
						+" WHERE time_slot_id = ?";
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
