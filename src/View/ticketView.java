package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.ticketController;
import Controller.ticketListener;
import Model.enumState;
import Model.office;
import Model.ticketModel;
import Model.ticket;
import Model.ticketOffice;

//import Controller.PointeuseController;

public class ticketView extends JFrame implements ActionListener, ticketListener{
	// __________________________________________________________________________
	
			// ========================================================================
			//                              Attributs
			// ========================================================================
			final private ticketModel tickets;
			private ticketOffice office;
			private JLabel number = new JLabel();
			private JLabel waitingTime = new JLabel();
			private JButton btnGoTo = new JButton();
			private JButton btnExit = new JButton("Partir");
			private JPanel panContener = (JPanel)getContentPane();
			private Font f = new Font("Serif", Font.PLAIN, 36); 
			private int numberTicket;
			private enumState state;
			SimpleDateFormat h = new SimpleDateFormat("mm:ss");		
			
			// ========================================================================
			//                              Methodes
			// ========================================================================

			// ====== Constructeurs ===============================
			public ticketView (ticketModel tickets){
				this.tickets=tickets;
				int numberTicket = tickets.getLastEditTicket()+1;
				this.numberTicket=numberTicket;
				int ecart = tickets.getEcart();
				setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				setLayout(null);
				if(numberTicket%10<11){
				setBounds(260*(numberTicket%10-7)-260,260,260,170);
				}
				
				if(numberTicket%10<8){
				setBounds(260*(numberTicket%10-4)-260,430,260,170);
				}
				
				if(numberTicket%10<=4){
				setBounds(260*(numberTicket%10)-260,600,260,170);
				}
				
				if(numberTicket%10==0){
					setBounds(520,260,260,170);
				}
					
						
				
				setTitle("Ticket");
				setVisible(true);
				setResizable(false);
				
				number.setBounds(130,15, 75, 75);
				number.setFont(f);
				number.setText(Integer.toString(numberTicket));
				panContener.add(number);
				
				waitingTime.setBounds(210, 0, 75, 25);
				waitingTime.setText(Integer.toString(ecart));
				panContener.add(waitingTime);
				
				btnGoTo.setBounds(10, 100, 150, 25);
				btnGoTo.setText("Aller au guichet x");
				btnGoTo.addActionListener(this);
				btnGoTo.setActionCommand("goTo");
				btnGoTo.setEnabled(false);
				panContener.add(btnGoTo);
				
				btnExit.setBounds(170, 100, 75, 25);
				btnExit.addActionListener(this);
				btnExit.setActionCommand("exit");
				panContener.add(btnExit);
								
			}

			public void attenteView(){
				this.btnGoTo.enable(false);
			}


			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String action = arg0.getActionCommand();
				if(action=="exit"){
					tickets.fireOnClose(numberTicket);
				}
				
				if (action=="goTo"){
					tickets.setEtat(enumState.EN_COURS);
					tickets.fireOnGo(office, numberTicket, tickets);
					tickets.fireOnChangeTicket(tickets.getEtat(), tickets.getTicketNumber());
					office.setState(enumState.EN_COURS);
				}
			}
			
			public String setConvert(int s){
				
				String hour;
				  int annee   = s / 60 / 60 / 24 / 365;
				  int jour    = s / 60 / 60 / 24 % 365;
				  int heure   = s / 60 / 60 % 24;
				  int minute  = s / 60 % 60;
				  int seconde = s % 60;

				if (minute<10)
					{
					if (seconde<10)hour=heure+":0"+minute+":0"+seconde;
					else hour=heure+":0"+minute+":"+seconde;
					}
					
				else hour=heure+":"+minute+":"+seconde;
				return hour;
				
			}

			@Override
			public void onWaiting(int ecarts, ticket t1) {
				// TODO Auto-generated method stub
				if (tickets == t1)waitingTime.setText(setConvert(ecarts));
			}

			
			@Override
			public void onCallTicket(ticketOffice ticketOffice, int ticket,
					int officeNumber) {
				// TODO Auto-generated method stub
				if(numberTicket==ticket)
					{
					btnGoTo.setText("Aller au guichet "+ ticketOffice.getNumber());
					tickets.setEtat(state=enumState.ATTENDU);
					office=ticketOffice;
					btnGoTo.setEnabled(true);
					this.setAlwaysOnTop(true);
					}
				if(ticketOffice==this.office){
					if(ticket>this.numberTicket){
						if(tickets.getEtat()!=enumState.EN_COURS){
							btnGoTo.setEnabled(false);
							this.state=enumState.ECOULE;	
							tickets.fireOnChangeTicket(state, this.numberTicket);
						}
				}
					
				
					if(ticketOffice==this.office){
						if(tickets.getEtat()==enumState.EN_COURS){
							this.setVisible(false);
							tickets.stop();
							tickets.removeTicketListener(this);
						}	
						
					}
					
				}
				
			}

			@Override
			public void onGo(ticketOffice ticketOffice, int nbTicket, ticket tickets) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void changeStateTicket(enumState state, int ticket) {
				// TODO Auto-generated method stub
				if(numberTicket==ticket){
					if(state==enumState.ATTENDU){
						panContener.setBackground(Color.YELLOW);
					}
					if(state==enumState.EN_COURS){
						panContener.setBackground(Color.GREEN);
						btnGoTo.setEnabled(false);

					}
					
					if(state==enumState.ECOULE){
						panContener.setBackground(Color.GRAY);
						btnGoTo.setEnabled(false);
					}
					
				}	
			}

			@Override
			public void onClose(int ticket) {
				// TODO Auto-generated method stub
				if(this.numberTicket==ticket){
					this.setVisible(false);
					tickets.stop();
					tickets.removeTicketListener(this);
				}

			}

			@Override
			public void onTakeTicket(ticket tickets) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onEcoule(int ticket) {
				// TODO Auto-generated method stub
				if(this.numberTicket==ticket){
					tickets.fireOnChangeTicket(tickets.getEtat(), tickets.getTicketNumber());
				}
			}


}
