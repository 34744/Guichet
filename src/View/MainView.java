package View;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.ticketModel;
import Model.ticketOffice;

public class MainView extends JFrame {
	
	private JPanel panContener = (JPanel)getContentPane();
	static  Controller.ticketController ticketController = new Controller.ticketController(null);
	
	public MainView(){
		this.setBounds(0, 0, 1024, 1200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		takeTicketView tt1 = new takeTicketView(ticketController,0);
		ticketOffice o1 = new ticketOffice();
		ticketOffice o2 = new ticketOffice();
		ticketOffice o3 = new ticketOffice();
		Logview e1 = new Logview();
		centralView c1=new centralView();
		c1.start();
		
		add(tt1);
		/*this.add(e1);
		this.add(c1);
		this.setVisible(true);*/
		
	}
	}

	
