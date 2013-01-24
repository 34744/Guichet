package Controller;


import Model.ticketModel;
import Model.ticketOffice;

public class ticketOfficeController {
	//                              Attributs
	// ========================================================================
	final private ticketOffice office;
	private int i;
	// ========================================================================
	//                              Methodes
	// ========================================================================


	// ====== Constructeurs ===============================
	public ticketOfficeController (ticketOffice office){
		this.office=office;
	}
	
	public void callTicket(){
		
		office.fireCallTicket(office, i);
	}
	
	
	
	

}
