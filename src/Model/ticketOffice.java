package Model;

import java.awt.Color;

import javax.swing.event.EventListenerList;

import View.ticketOfficeView;

import Controller.ticketListener;
import Controller.ticketOfficeListener;

public class ticketOffice extends office implements ticketListener{
	// ========================================================================
				//                              Attributs
	
	private enumState etat;
	private View.ticketOfficeView office;
	private static  ticketModel ticket;
	private static ticketOffice to;
	private int lastCallTicket;
	
	private static EventListenerList listeners = new EventListenerList();
	// ========================================================================
	
	// =========== Constructeurs ===============================
	public ticketOffice (){
		super();
		office= new View.ticketOfficeView(this);
		ticketOffice.addTicketOfficeListener(office, this);
		ticketModel.addTicketListener(this,ticket);
	}
	
	// ====== Ecouteurs ===================================
				public static void addTicketOfficeListener (ticketOfficeListener listener,ticketOffice to1 ){
					listeners.add(ticketOfficeListener.class, listener);	
										
				}
				
				public void removeTicketOfficeListener (ticketListener listener){
					listeners.remove(ticketListener.class, listener);
				}
				
				private static  ticketOfficeListener [] getTicketOfficeListener(){
					return listeners.getListeners(ticketOfficeListener.class);
				}

				// ---------------------------------------------	
				public static void fireCallTicket (ticketOffice office, int tickets)
				{						
					  if (getLastServeTicket()<ticket.getLastEditTicket() ){
						for (ticketOfficeListener listener : getTicketOfficeListener()){
							listener.callTicket(getLastServeTicket()+1,office, office.getNumber());
						}
						
												
						setLastServeTicket();
						office.setState(enumState.ATTENDU);
						
						office.fireOnChange(enumState.ATTENDU);	
						office.setLastCallTicket(getLastServeTicket()+1);
					}
					  else{
						if(office.getState()==enumState.EN_COURS){
							
						ticket.fireOnClose(office.getLastOfficeServedTicket().getTicketNumber());	
						}
						if(office.getState()==enumState.ATTENDU && ticket.getEtat()==enumState.ATTENDU){
							ticket.setEtat(enumState.ECOULE);
							ticket.fireOnEcoule(ticket.getTicketNumber());
						}
						office.setState(enumState.EN_ATTENTE);
						office.fireOnChange(enumState.EN_ATTENTE);
						ticket=(ticketModel) office.getLastOfficeServedTicket();
					  }
					}
						
								
				
				
				public void fireOnAccept(ticketOffice office){
					for (ticketOfficeListener listener : getTicketOfficeListener()){
						listener.onAccept(office);
						}
					fireOnChange(etat=enumState.EN_COURS);
				}

				public void fireOnChange(enumState state){

					for (ticketOfficeListener listener : getTicketOfficeListener()){
						listener.changeStateOffice(this,state);
						}
					}

				@Override
				public void onWaiting(int ecart, ticket tickets) {
					// TODO Auto-generated method stub
					this.ticket=(ticketModel) tickets;
				}

				@Override
				public void onCallTicket(ticketOffice ticketOffice, int ticket,
						int officeNumber) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onGo(ticketOffice ticketOffice, int ticket,ticket tickets) {
					// TODO Auto-generated method stub
					ticketOffice.setState(enumState.EN_COURS);
					ticketOffice.fireOnChange(ticketOffice.getState());
					this.ticket=(ticketModel) tickets;
				}

				@Override
				public void changeStateTicket(enumState state, int ticket) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onClose(int ticket) {
					// TODO Auto-generated method stub
					
				}

				public int getLastCallTicket() {
					return lastCallTicket;
				}

				public void setLastCallTicket(int number) {
					this.lastCallTicket=number;
				}

				@Override
				public void onTakeTicket(Model.ticket tickets) {
					// TODO Auto-generated method stub
					this.ticket=(ticketModel) tickets;
					if(this.getState()==enumState.EN_ATTENTE){
						fireCallTicket(this, getLastCallTicket());
					}
				}

				@Override
				public void onEcoule(int ticket) {
					// TODO Auto-generated method stub
					
				}

}
