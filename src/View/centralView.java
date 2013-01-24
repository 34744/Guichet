package View;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

import Controller.LogListener;
import Controller.ticketListener;
import Controller.ticketOfficeListener;
import Model.enumState;
import Model.ticket;
import Model.ticketModel;
import Model.ticketOffice;

public class centralView extends JFrame implements Runnable, ticketOfficeListener{

	// __________________________________________________________________________
	
	// ========================================================================
	//                              Attributs
	// ========================================================================
	private ticketOffice office;
	private centralView c1;
	private Thread t1 = new Thread();
	private JLabel clock = new JLabel();
	private Font f = new Font("Serif", Font.PLAIN, 24);
	private Font h = new Font("Serif", Font.PLAIN, 18);
	private JLabel office1 = new JLabel();
	private JLabel office2 = new JLabel();
	private JLabel office3 = new JLabel();
	private JLabel office4 = new JLabel();
	private JPanel panContener = (JPanel)getContentPane();	
	
	private static EventListenerList listeners = new EventListenerList();
	// ========================================================================
	//                              Methodes
	// ========================================================================

	// ====== Constructeurs ===============================
	
	public centralView(){
		this.start();
		addLogListener((LogListener) c1);
		office.addTicketOfficeListener(this, office);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(400,0,250,220);
		setTitle("Afficheur");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		clock.setBounds(5, 5, 75, 35);
		clock.setFont(h);
		clock.setText(this.getTime());
		clock.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panContener.add(clock);
		
		office1.setBounds(0, 50, 250, 40);
		office1.setText("Guichet 1:");
		office1.setFont(f);
		panContener.add(office1);
		
		office2.setBounds(0, 50, 250, 100);
		office2.setText("Guichet 2:");
		office2.setFont(f);
		panContener.add(office2);
		
		office3.setBounds(0, 50, 250, 160);
		office3.setText("Guichet 3:");
		office3.setFont(f);
		panContener.add(office3);
		
		office4.setBounds(0, 50, 250, 220);
		office4.setText("Guichet 4:");
		office4.setFont(f);
		panContener.add(office4);
	}
	
	public static void addLogListener (LogListener listener){
		listeners.add(LogListener.class,listener);
	}
	
	public void removeLogListener (LogListener listener){
		listeners.remove(LogListener.class, listener);
	}
	
	private LogListener [] getLogListener(){
		return listeners.getListeners(LogListener.class);
	}
	public void fireOnWaiting(String hour)
	{		
		for (LogListener listener : getLogListener()){
			listener.onLogWaiting(hour);
			}			

	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub	
		while(this.isVisible()==true){
			try {
				t1.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fireOnWaiting(this.getTime());
			clock.setText(getTime());
		}

	}
	
	public void start(){
		Thread t= new Thread(this);
		t.start();
	}
	
	public void stop(){
			Thread t= new Thread(this);
			t.stop();
			
		}

	@Override
	public void callTicket(int i, ticketOffice offices, int numberOffice) {
		// TODO Auto-generated method stub

		if(offices.getNumber()==1){
			office1.setText("Guichet 1: on sert le "+ i);
		}
		
		if(offices.getNumber()==2){
			office2.setText("Guichet 2: on sert le "+ i);
		}
		
		if(offices.getNumber()==3){
			office3.setText("Guichet 3: on sert le "+ i);
		}
		
		if(offices.getNumber()==4){
			office4.setText("Guichet 4: on sert le "+ i);
		}

	}

	@Override
	public void onAccept(ticketOffice ticketOffice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeStateOffice(ticketOffice ticketOffice, enumState state) {
		// TODO Auto-generated method stub
		this.office=ticketOffice;
		if(ticketOffice.getNumber()==1 & ticketOffice.getState()==enumState.EN_ATTENTE){
			office1.setText("Guichet 1: on sert le ---");
		}
		
		if(office.getNumber()==2 & ticketOffice.getState()==enumState.EN_ATTENTE){
			office2.setText("Guichet 2: on sert le --- ");
		}
		
		if(office.getNumber()==3 & ticketOffice.getState()==enumState.EN_ATTENTE){
			office3.setText("Guichet 3: on sert le ---");
		}
		
		if(ticketOffice.getNumber()==1 & ticketOffice.getState()==enumState.EN_PAUSE){
			office1.setText("Guichet 1: ");
		}
		
		if(office.getNumber()==2 & ticketOffice.getState()==enumState.EN_PAUSE){
			office2.setText("Guichet 2: ");
		}
		
		if(office.getNumber()==3 & ticketOffice.getState()==enumState.EN_PAUSE){
			office3.setText("Guichet 3: ");
		}
		
	}

	@Override
	public void closeTicket(ticket tickets) {
		// TODO Auto-generated method stub
		
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

	
}
