package Controller;

import java.util.Date;

import Model.ticketModel;



public class ticketController {
	// ========================================================================
			//                              Attributs
			// ========================================================================
			final private ticketModel tickets;

			// ========================================================================
			//                              Methodes
			// ========================================================================


			// ====== Constructeurs ===============================
			public ticketController (ticketModel tickets){
				this.tickets=tickets;
			}

			// ====== Accesseurs ==================================

			public int getEcart(){
				return tickets.getEcart();
			}
			public void setEcart(int ecart){
				tickets.setEcart(ecart);
				tickets.fireOnWaiting(ecart,tickets);			}
			
			public int getlastEditTicket(){
				return tickets.getLastEditTicket();
			}
			
			public void incLastEditTicket(){
				int newLastEditTicket=tickets.getLastEditTicket()+1;
				tickets.setLastEditTicket(newLastEditTicket);
			}
			
			public Date getWaitingTime(){
				return tickets.getWaitingTime();
			}
			
			// ====== Gestion des vues ============================
			public void addView(ticketListener ticketView, ticketModel t1){
				tickets.addTicketListener(ticketView,t1);
				t1.start();
				
			}
			
			public void stop(){
				ticketModel t1 = new ticketModel(0);
				t1.stop();
			}
}
