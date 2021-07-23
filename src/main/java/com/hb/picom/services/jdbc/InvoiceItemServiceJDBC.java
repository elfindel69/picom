package com.hb.picom.services.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.hb.picom.pojos.InvoiceItem;

public class InvoiceItemServiceJDBC extends ServiceJDBC<InvoiceItem> {

	public InvoiceItemServiceJDBC(Connection connection) {
		super(connection);
	}

	@Override
	public int createItem(InvoiceItem item) {
		InvoiceItem testInvoiceItem = getItem(item.getId());
		if(testInvoiceItem == null) {
			int createdRow = 0;
			try {
				 String query = "INSERT INTO invoice_item("
				 		+ "id_invoice, "
				 		+ "invoice_item_name,"
				 		+ "invoice_item_price,"
				 		+ "invoice_item_quantity)"
				 		+ " VALUES(?,?,?,?)";
				 ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
				 
				 ps.setInt(1, item.getInvoiceId());
				 ps.setString(2, item.getName());
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
		String query = "SELECT * FROM invoice_item ";
		 try {
			
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()){
				 InvoiceItem item = new InvoiceItem();
				
				 int id = rs.getInt("nvoice_item_id");
				 item.setId(id);
				 int invoiceId = rs.getInt("id_invoice");
				 item.setInvoiceId(invoiceId);
				 String name = rs.getString("invoice_item_name");
				 item.setName(name);
				 double price = rs.getDouble("invoice_item_price");
				 item.setPrice(price);
				 int quantity = rs.getInt("invoice_item_quantity");
				 item.setQuantity(quantity);
				 
				 
				 items.add(item);
			 }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public InvoiceItem getItem(int id) {
		for (InvoiceItem item : items) {
			if(item.getId() == id) {
				return item;
			}
		}
		return null;
	}

	@Override
	public boolean deleteItem(int id) {
		InvoiceItem itemInvoice = null;
		int idx=0;
		for (InvoiceItem item: items) {
			if(item.getId() == id) {
				itemInvoice = item;
				break;
				
			}
			idx++;
		}
		if(itemInvoice != null) {
			items.remove(idx);
			try {
				 String query = "DELETE FROM invoice_item WHERE invoice_item_id = ?";
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
	public int updateItem(int id, InvoiceItem item) {
		int idx = 0;
		for (InvoiceItem invoiceItem: items) {
			if(invoiceItem.getId() == id) {
				items.set(idx, item);
				try {
					 String query = "UPDATE invoice_item SET"
					 		+ " id_invoice = ?,"
					 		+ " invoice_item_name = ?,"
					 		+ " invoice_item_price = ?,"
					 		+ " invoice_item_quantity = ?"
					 		+ " WHERE invoice_item_id = ?";
					 ps = connection.prepareStatement(query);
					 
					 ps.setInt(1, item.getInvoiceId());
					 ps.setString(2, item.getName());
					 ps.setDouble(3, item.getPrice());
					 ps.setInt(4, item.getQuantity());
					 
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
			 String query = "SELECT * FROM invoice_item "
						+" WHERE invoice_item_id = ?";
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
