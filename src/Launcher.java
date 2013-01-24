
import Model.ticketModel;
import Model.ticket;
import Model.ticketOffice;
import View.Logview;
import View.MainView;
import View.centralView;
import View.takeTicketView;
import View.ticketOfficeView;
import View.ticketView;


public class Launcher extends ticketView {

	public Launcher(ticketModel ticketController) {
		super(ticketController);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	static private Controller.ticketController ticketController = new Controller.ticketController(null);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*MainView mv1= new MainView();
		mv1.setVisible(true);*/

		takeTicketView tt1 = new takeTicketView(ticketController,1);
		takeTicketView tt2 = new takeTicketView(ticketController,2);
		ticketOffice o1 = new ticketOffice();
		ticketOffice o2 = new ticketOffice();
		ticketOffice o3 = new ticketOffice();
		ticketOffice o4 = new ticketOffice();
		Logview e1 = new Logview();
		centralView c1=new centralView();
		c1.start();
	}
	
	// Le processus début par la prise d'un ticket via le takeTicketView -> création d'un ticket avec thread + affichage (passage via le constructeur de ticket)
	//appel d'un ticket via la vue ticketOfficeView (utilisation du fireCallTicket (ModelOffice) -> CallTicket utilisation du fireOnCallTicket dans ticketModel)
	// ticket accepte via ticketView (fireOnGo -> office prévenu via fireOnAccept)
	//Les méthodes fireOnChange (ticketOffice) et fireOnChangeTicket (ticketModel) permettent de changer le statut des objets "Office" et "Ticket"
	//La mise en pause se fait par le biais d'un changement via le fireOnChange
	//La méthode fireOnClose permet de clôturer un ticket après l'avoir servi
	//La méthode fireOnEcoule permet de mettre le ticket 'inappelable'
	//La méthode onWaiting permet d'incrément le temps d'attente
	//La logView est à l'écoute des ticketModel et ticketOffice afin de pouvoir afficher les intéractions
	//La centralView est à l'écoute du ticketOffice
	//La classe enumState reprend les états possibles pour les tickets et offices.

}
