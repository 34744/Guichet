package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.ticketListener;
import Controller.ticketOfficeListener;
import Model.enumState;
import Model.ticket;
import Model.ticketModel;
import Model.ticketOffice;

public class ticketOfficeView extends JFrame implements ActionListener, ticketOfficeListener,ticketListener{
	// ========================================================================
				//                              Attributs
				// ========================================================================
			 	private ticketOffice office;
		
				private JLabel numberTicket = new JLabel("");
				private JLabel waitingTicket = new JLabel("0:23:12");
				private JLabel LabelBreak = new JLabel("Pause");
				private JLabel officeNumber = new JLabel("");
				private JButton btnNextTicket = new JButton("Ticket suivant");
				private JCheckBox cbBreak = new JCheckBox();
				private JPanel panContener = (JPanel)getContentPane();
				private Font f = new Font("Serif", Font.PLAIN, 36); 
				
				
				// ========================================================================
				//                              Methodes
				// ========================================================================

				// ====== Constructeurs ===============================
				public ticketOfficeView (ticketOffice ticketOffice){
					this.office=ticketOffice;
					setLayout(null);
					setDefaultCloseOperation(EXIT_ON_CLOSE);
					setBounds(1030,180*(office.getNumber()-1),250,180);
					setTitle("Guichet");
					setVisible(true);
					setResizable(false);
					
					officeNumber.setBounds(50,10, 150, 75);
					officeNumber.setFont(f);
					officeNumber.setText("Guichet " + ticketOffice.getNumber());
					panContener.add(officeNumber);
					
					numberTicket.setBounds(80, 75, 100, 25);
					panContener.add(numberTicket);
					
					
					LabelBreak.setBounds(180, 0, 40, 25);
					panContener.add(LabelBreak);
					
					cbBreak.setBounds(220, 3, 20, 20);
					cbBreak.setSelected(false);
					cbBreak.addActionListener(new StateListener());
					cbBreak.setActionCommand("break");
					panContener.add(cbBreak);
					
					btnNextTicket.setBounds(50,120,140,20);
					btnNextTicket.setActionCommand("Next");
					btnNextTicket.addActionListener(this);
					panContener.add(btnNextTicket);
					
				}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String action = e.getActionCommand();
		if(action== "Next"){
			int i = 0;
			ticketOffice.fireCallTicket(office, i);
		}
	}
	
	class StateListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	      if(cbBreak.isSelected())
	    	  {
	    	  btnNextTicket.setEnabled(false);
	    	  btnNextTicket.setText("en pause");
	    	  panContener.setBackground(Color.RED);
	    	  office.setState(enumState.EN_PAUSE);
	    	  office.fireOnChange(enumState.EN_PAUSE);
	    	  numberTicket.setText(null);
	    	  
	    	  }
			else{ 
				btnNextTicket.setEnabled(true);
				btnNextTicket.setText("Ticket suivant");
				panContener.setBackground(null);
				office.setState(enumState.DEBUT);
		    	office.fireOnChange(office.getState());
			}
	    }

	}

	@Override
	public void callTicket(int i, ticketOffice office, int officeNumber) {
		// TODO Auto-generated method stub
		if(this.office==office)	
			{
			this.numberTicket.setText("On sert le " + i);
			this.cbBreak.setEnabled(false);
			}
	}

	@Override
	public void onWaiting(int ecart, ticket tickets) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onCallTicket(ticketOffice ticketOffice, int ticket,
			int officeNumber) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onGo(ticketOffice ticketOffice, int ticket, ticket tickets) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onAccept(ticketOffice ticketOffice) {
		// TODO Auto-generated method stub
		if(this.office==ticketOffice){
			cbBreak.setEnabled(true);
			
		}
	}
	
	@Override
	public void changeStateOffice(ticketOffice ticketOffice, enumState state) {
		// TODO Auto-generated method stub
		
		if(ticketOffice==office){
			if(state==enumState.EN_COURS){
				panContener.setBackground(Color.GREEN);
			}
			if(state==enumState.EN_ATTENTE){
				panContener.setBackground(Color.ORANGE);
				numberTicket.setText(null);
				cbBreak.setEnabled(true);
			}
			if(state==enumState.ATTENDU){
				panContener.setBackground(Color.YELLOW);
			}
			if(state==enumState.DEBUT){
				panContener.setBackground(Color.MAGENTA);
			}
		}
		
	}


	@Override
	public void changeStateTicket(enumState state, int ticket) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onClose(int ticket) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void closeTicket(ticket tickets) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onTakeTicket(ticket tickets) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onEcoule(int ticket) {
		// TODO Auto-generated method stub
		
	}

}
