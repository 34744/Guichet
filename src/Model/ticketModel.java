package Model;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.event.EventListenerList;

import View.ticketView;

import Controller.ticketController;
import Controller.ticketListener;
import Controller.ticketOfficeListener;

public class ticketModel extends ticket implements Runnable, ticketOfficeListener{
	// ========================================================================
			//                              Attributs
			
			private enumState etat;
			private Date createTime = new Date(System.currentTimeMillis());
			private Thread t1 = new Thread();
			private View.ticketView tickets;
			private ticketOffice office;
			

			
			private static EventListenerList listeners = new EventListenerList();
			// ========================================================================
			
			// =========== Constructeurs ===============================
			public ticketModel(int takerTicket){
				super(takerTicket);
				
				tickets= new View.ticketView(this);
				addTicketListener(tickets, this);
				office.addTicketOfficeListener(this,office);
				ticket.setLastEditTicket();
				fireOnTakeTicket();
						
				}
				// ====== Accesseurs ==================================
			Date nowTime = new Date(System.currentTimeMillis());
			int ecart = (int) ((nowTime.getTime()-createTime.getTime())/1000);
			
			public int getEcart(){
				return ecart;
			}
			
			public Date getWaitingTime() {
				return createTime;
			}

			public void setEcart(int ecart2) {
				
				this.ecart = ecart2;
				fireOnWaiting(ecart2, this);
			}
			
			public void incLastEditTicket(){
				int newTicket = getLastEditTicket()+1;
				setLastEditTicket(newTicket);
				}


			public void setLastEditTicket(int lastEditTicket) {
				lastEditTicket = lastEditTicket+1;
			}
			
			public void run (){
				
				etat=enumState.EN_ATTENTE;
				while (etat != enumState.ECOULE){
					
					
					try {
						
						t1.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (getEtat()==enumState.EN_ATTENTE){
					nowTime=new Date(System.currentTimeMillis());
					ecart = (int) ((nowTime.getTime()-createTime.getTime())/100);
					fireOnWaiting(ecart, this);
											}
					}				
				}
			
			
			public void start(){
				Thread t= new Thread(this);
				t.start();
				tickets.attenteView();
			}
			
			public void attend(){
				etat=enumState.ATTENDU;
			}
			
		public void stop(){
				Thread t= new Thread(this);
				etat=enumState.ECOULE;
				t.stop();
				tickets.hide();
				
			}

	// ====== Ecouteurs ===================================
			public static void addTicketListener (ticketListener listener, ticketModel t1){
				listeners.add(ticketListener.class,listener);

			}
			
			public void removeTicketListener (ticketListener listener){
				listeners.remove(ticketListener.class, listener);
			}
			
			private ticketListener [] getTicketListener(){
				return listeners.getListeners(ticketListener.class);
			}

			// ---------------------------------------------
			public void fireOnTakeTicket ()
			{
				
				for (ticketListener listener : getTicketListener()){
					listener.onTakeTicket(this);
					}
			}
			
			public void fireOnWaiting(int ecart, ticket tickets)
			{
				for (ticketListener listener : getTicketListener()){
					listener.onWaiting(ecart, tickets);
					}
			}
			
			public void fireOnCallTicket(ticketOffice o1, int i, int officeNumber){
				for (ticketListener listener : getTicketListener()){
					listener.onCallTicket(o1, i, i);
					}
				fireOnChangeTicket(etat=enumState.ATTENDU,i);

			}
			
			public void fireOnGo(ticketOffice o1, int numberTicket, ticketModel tickets2){
				for (ticketListener listener : getTicketListener()){
					listener.onGo(o1, numberTicket, tickets2);
					}
				o1.fireOnAccept(o1);
				o1.setLastOfficeServedTicket(tickets2);
				fireOnChangeTicket(etat=enumState.EN_COURS, numberTicket);
				this.etat=enumState.EN_COURS;
				this.setTo(o1);
			}
			public void fireOnChangeTicket(enumState state, int ticket){
				for (ticketListener listener : getTicketListener()){
					listener.changeStateTicket(state, ticket);
					}
			}
			public void fireOnClose(int ticket){
				for (ticketListener listener : getTicketListener()){
					listener.onClose(ticket);
					}
			}
			public void fireOnEcoule(int ticket){
				for (ticketListener listener : getTicketListener()){
						listener.onEcoule(ticket);
						}
			}
			@Override
			public synchronized void callTicket(int i, ticketOffice office, int officeNumber) {
				// TODO Auto-generated method stub
				fireOnCallTicket(  office,i,officeNumber);	

			}

			@Override
			public void onAccept(ticketOffice ticketOffice) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void changeStateOffice(ticketOffice ticketOffice,
					enumState state) {
				// TODO Auto-generated method stub

			if(ticketOffice==this.getTicketOffice() & ticketOffice.getState()==enumState.EN_PAUSE) 
				{
				fireOnClose(this.getTicketNumber());	
				}
			}

			@Override
			public void closeTicket(ticket tickets) {
				// TODO Auto-generated method stub
				
			}

		}
