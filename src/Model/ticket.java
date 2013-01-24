package Model;


import javax.swing.event.EventListenerList;

import Controller.ticketListener;

public class ticket  {
	// ========================================================================
			//                              Attributs
			// ========================================================================
			private static int lastEditTicket = 0;
			private enumState etat=enumState.EN_ATTENTE;
			private int ticketNumber;
			private ticketOffice ticketOffice;
			private int takerNumber;
			// ========================================================================
			//                              Methodes
			// ========================================================================

			public enumState getEtat() {
				return etat;
			}

			public void setEtat(enumState etat) {
				this.etat = etat;
			}

			// ====== Constructeurs ===============================
			public ticket(int takernumber){
			int number=getLastEditTicket()+1;
			this.ticketNumber=number;
			this.setTakerNumber(takernumber);
			}

			public int getTicketNumber() {
				return ticketNumber;
			}

			public void setTicketNumber(int ticketNumber) {
				this.ticketNumber = ticketNumber;
			}

			public static int getLastEditTicket() {
				return lastEditTicket;
			}

			public static void setLastEditTicket() {
				lastEditTicket = lastEditTicket+1;
			}

			public ticketOffice getTicketOffice() {
				return ticketOffice;
			}

			public void setTo(ticketOffice ticketOffice) {
				this.ticketOffice = ticketOffice;
			}

			public int getTakerNumber() {
				return takerNumber;
			}

			public void setTakerNumber(int takerNumber) {
				this.takerNumber = takerNumber;
			}
				
			// ====== Accesseurs ==================================

				
				
}