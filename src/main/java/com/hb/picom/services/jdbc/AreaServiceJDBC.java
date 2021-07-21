package com.hb.picom.services.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.hb.picom.pojos.Area;

public class AreaServiceJDBC extends ServiceJDBC<Area> {

	public AreaServiceJDBC(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int createItem(Area item) {
		Area testArea = getItem(item.getId());
		if(testArea == null) {
			int createdRow = 0;
			try {
				 String query = "INSERT INTO area("
				 		+ "area_name, "
				 		+ "area_base_price)"
				 		+ " VALUES(?,?)";
				 ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
				 
				 ps.setString(1, item.getName());
				 ps.setDouble(2, item.getBasePrice());
				
				 
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
		String query = "SELECT * FROM area ";
		 try {
			
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()){
				 Area area = new Area();
				
				 int id = rs.getInt("area_id");
				 area.setId(id);
				 String name = rs.getString("area_name");
				 area.setName(name);
				 double basePrice = rs.getDouble("area_base_price");
				 area.setBasePrice(basePrice);
				 
				 
				 items.add(area);
			 }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Area getItem(int id) {
		for (Area area : items) {
			if(area.getId() == id) {
				return area;
			}
		}
		return null;
	}

	@Override
	public void deleteItem(int id) {
		Area itemArea = null;
		for (Area area: items) {
			if(area.getId() == id) {
				itemArea = area;
				break;
			}
		}
		if(itemArea != null) {
			items.remove(itemArea);
			try {
				 String query = "DELETE FROM area WHERE area_id = ?";
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
	public int updateItem(int id, Area item) {
		int idx = 0;
		for (Area area: items) {
			if(area.getId() == id) {
				items.set(idx, item);
				try {
					 String query = "UPDATE area SET"
					 		+ " area_name = ?,"
					 		+ " area_base_price = ?"
					 		+ " WHERE area_id = ?";
					 ps = connection.prepareStatement(query);
					 
					 ps.setString(1, item.getName());
					 ps.setDouble(2, item.getBasePrice());
					
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
			 String query = "SELECT * FROM area "
						+" WHERE area_id = ?";
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
