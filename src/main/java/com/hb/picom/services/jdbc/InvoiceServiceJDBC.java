package com.hb.picom.services.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.hb.picom.pojos.Invoice;

public class InvoiceServiceJDBC extends ServiceJDBC<Invoice> {

	public InvoiceServiceJDBC(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int createItem(Invoice item) {
		Invoice testInvoice = getItem(item.getId());
		if(testInvoice == null) {
			int createdRow = 0;
			try {
				 String query = "INSERT INTO invoice("
				 		+ "invoice_company_name, "
				 		+ "invoice_company_SIRET,"
				 		+ "invoice_client_address,"
				 		+ "invoice_gross_price,"
				 		+ "invoice_VAT_price,"
				 		+ "invoice_net_price)"
				 		+ " VALUES(?,?,?,?,?,?)";
				 ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
				 
				 ps.setString(1, item.getCompanyName());
				 ps.setString(2, item.getCompanySIRET());
				 ps.setString(3, item.getCompanyAddress());
				 ps.setDouble(4, item.getGrossPrice());
				 ps.setDouble(5, item.getVATPrice());
				 ps.setDouble(6, item.getNetPrice());
				 
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
		String query = "SELECT * FROM invoice ";
		 try {
			
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()){
				 Invoice invoice = new Invoice();
				 int id = rs.getInt("invoice_id");
				 invoice.setId(id);
				 String name = rs.getString("invoice_company_name");
				 invoice.setCompanyName(name);
				 String siret = rs.getString("invoice_company_SIRET");
				 invoice.setCompanySIRET(siret);
				 String address = rs.getString("invoice_client_address");
				 invoice.setCompanyAddress(address);
				 double grossPrice = rs.getDouble("invoice_gross_price");
				 invoice.setGrossPrice(grossPrice);
				 double vatPrice = rs.getDouble("invoice_VAT_price");
				 invoice.setVATPrice(vatPrice);
				 double netPrice = rs.getDouble("invoice_net_price");
				 invoice.setNetPrice(netPrice);
				 
				 items.add(invoice);
			 }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public Invoice getItem(int id) {
		for (Invoice invoice : items) {
			if(invoice.getId() == id) {
				return invoice;
			}
		}
		return null;
	}

	@Override
	public boolean deleteItem(int id) {
		Invoice itemInvoice = null;
		int idx = 0;
		for (Invoice invoice: items) {
			if(invoice.getId() == id) {
				itemInvoice = invoice;
				break;
			}
			idx++;
		}
		if(itemInvoice != null) {
			items.remove(idx);
			try {
				 String query = "DELETE FROM invoice WHERE invoice_id = ?";
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
	public int updateItem(int id, Invoice item) {
		int idx = 0;
		for (Invoice invoice: items) {
			if(invoice.getId() == id) {
				items.set(idx, item);
				try {
					 String query = "UPDATE invoice SET"
					 		+ " invoice_company_name = ?,"
					 		+ " invoice_company_SIRET = ?,"
					 		+ " invoice_client_address = ?,"
					 		+ " invoice_gross_price = ?,"
					 		+ " invoice_VAT_price = ?,"
					 		+ " invoice_net_price = ?"
					 		+ " WHERE invoice_id = ?";
					 ps = connection.prepareStatement(query);
					 
					 ps.setString(1, item.getCompanyName());
					 ps.setString(2, item.getCompanySIRET());
					 ps.setString(3, item.getCompanyAddress());
					 ps.setDouble(4, item.getGrossPrice());
					 ps.setDouble(5, item.getVATPrice());
					 ps.setDouble(6, item.getNetPrice());
					 
					 ps.setInt(7, id);
					 
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
			 String query = "SELECT * FROM invoice "
						+" WHERE invoice_id = ?";
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
