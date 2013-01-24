package Controller;

import java.util.EventListener;

import Model.enumState;
import Model.ticket;
import Model.ticketOffice;

public interface ticketOfficeListener extends EventListener{
	public void callTicket(int i, ticketOffice office, int numberOffice);
	public void onAccept(ticketOffice ticketOffice);
	public void changeStateOffice(ticketOffice ticketOffice, enumState state);
	public void closeTicket(ticket tickets);
}
