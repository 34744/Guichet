package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Controller.LogListener;
import Controller.ticketListener;
import Controller.ticketOfficeListener;
import Model.enumState;
import Model.ticket;
import Model.ticketModel;
import Model.ticketOffice;

public class Logview extends JFrame implements ticketListener, ticketOfficeListener{
	
	private ticketOffice office;
	private ticketModel ticket;
	private Font f = new Font("Serif", Font.PLAIN, 18); 
	private JTextArea texte = new JTextArea(("Heure   -  Action" ));
	private JPanel panContener = (JPanel) getContentPane();			
	private JScrollPane scrollPane = new JScrollPane(texte);

	
	public Logview(){
		
		
		office.addTicketOfficeListener(this, office);
		ticket.addTicketListener(this, ticket);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(750,0, 280, 300);
		setPreferredSize(new Dimension(500, 100));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Visualisation des évenements");
		setVisible(true);
		setResizable(true);
		Date nowTime = new Date(System.currentTimeMillis());
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(100, 250));
		panContener.add(scrollPane);
		texte.setEditable(false);



	}


	@Override
	public void onClose(int ticket) {
		// TODO Auto-generated method stub
		texte.setText(texte.getText()+"\n" + this.getTime()+" Ticket  " + ticket + " est parti");		
	}
	

	@Override
	public void callTicket(int i, ticketOffice office, int numberOffice) {
		// TODO Auto-generated method stub

		this.office=office;
		texte.setText(texte.getText()+"\n" +this.getTime()+ " Ticket " + i + " appelé par le guchet " + numberOffice);
	}


	@Override
	public void onAccept(ticketOffice ticketOffice) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void changeStateOffice(ticketOffice ticketOffice, enumState state) {
		// TODO Auto-generated method stub
		if(ticketOffice.getState()==enumState.EN_PAUSE)
			texte.setText(texte.getText()+"\n" + this.getTime()+" Guichet " + ticketOffice.getNumber() + " mise en pause");			
		if(ticketOffice.getState()==enumState.DEBUT)
			texte.setText(texte.getText()+"\n" + this.getTime()+" Guichet " + ticketOffice.getNumber() + " fin de la pause");			
	}


	@Override
	public void closeTicket(ticket tickets) {
		// TODO Auto-generated method stub
		texte.setText(texte.getText()+"\n" + this.getTime()+" Ticket  " + tickets .getTicketNumber()+ " terminé");
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
		texte.setText(texte.getText()+"\n" + this.getTime()+" Ticket  " + ticket + " va au guichet "+ ticketOffice.getNumber());
	}


	@Override
	public void changeStateTicket(enumState state, int ticket) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTakeTicket(ticket tickets) {
		// TODO Auto-generated method stub
		texte.setText(texte.getText()+"\n" + this.getTime() + " Le distributeur " + tickets.getTakerNumber() + " a émis le ticket : " + tickets.getTicketNumber());
		
	}
	
public String getTime(){
	TimeZone tz = TimeZone.getTimeZone("Europe/Paris");
	Calendar calendrier = Calendar.getInstance(tz);
	SimpleDateFormat formatH = new SimpleDateFormat("HH:mm:ss");
	SimpleDateFormat formatD = new SimpleDateFormat("dd/MM/yyyy");
	formatH.setCalendar(calendrier);
	formatD.setCalendar(calendrier);
	String heureActu = formatH.format(calendrier.getTime());
	String dateActu	 = formatD.format(calendrier.getTime());
	return(heureActu);
}
	@Override
	public void onEcoule(int ticket) {
		// TODO Auto-generated method stub
		
	}

	
}
