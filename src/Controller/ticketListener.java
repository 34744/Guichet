package Controller;

import java.util.EventListener;

import Model.enumState;
import Model.ticket;
import Model.ticketOffice;

public interface ticketListener extends EventListener{
public void onTakeTicket(ticket tickets);
public void onWaiting(int ecart, ticket tickets);
public void onCallTicket(ticketOffice ticketOffice, int ticket,int officeNumber);
public void onGo(ticketOffice ticketOffice, int ticket, ticket tickets);
public void changeStateTicket(enumState state,int ticket);
public void onClose(int ticket);
public void onEcoule(int ticket);
}
