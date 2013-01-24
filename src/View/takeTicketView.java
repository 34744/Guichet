package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.View;

import Controller.ticketListener;
import Model.ticketModel;

public class takeTicketView extends JFrame implements ActionListener{
	// __________________________________________________________________________
	
				// ========================================================================
				//                              Attributs
				// ========================================================================
				final private Controller.ticketController ticketController;
		
				private JButton takeTicket = new JButton("Prendre un ticket");
				private JPanel panContener = (JPanel)getContentPane();	
				private int takerTicket; 

				
				// ========================================================================
				//                              Methodes
				// ========================================================================

				// ====== Constructeurs ===============================
				public takeTicketView (Controller.ticketController ticketController, int takerTicket){
					this.ticketController=ticketController;
					this.takerTicket=takerTicket;
					setLayout(null);
					setDefaultCloseOperation(EXIT_ON_CLOSE);
					setBounds(this.takerTicket*150-150,0,150,100);
					setTitle("Distributeur " + takerTicket);
					setVisible(true);
					setResizable(false);
					
					takeTicket.setBounds(0, 0, 142, 65);
					takeTicket.addActionListener(this);
					takeTicket.setActionCommand("Distributeur");
					panContener.add(takeTicket);
				}

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String action = arg0.getActionCommand();
					if(action=="Distributeur")
						{
						ticketModel t1= new ticketModel(takerTicket);
						t1.start();
						}
				}
}
