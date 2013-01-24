package Model;

public class office {
	// ========================================================================
				//                              Attributs
				// ========================================================================
				private static int lastServeTicket = 0;
				private int number=0;
				private static int nbGuichet = 0;
				private enumState state;
				private ticket lastOfficeServedTicket;

				// ========================================================================
				//                              Methodes
				// ========================================================================

				// ====== Constructeurs ===============================
				public office(){
					this.nbGuichet++;
					this.number=nbGuichet;
				}

				public int getNumber() {
					return number;
				}
				
				public void setNumber(){
					number=getNumber()+1;
				}

				public static int getLastServeTicket() {
					return lastServeTicket;
				}

				public static void setLastServeTicket() {
					office.lastServeTicket = lastServeTicket+1;
				}

				public enumState getState() {
					return state;
				}

				public void setState(enumState state) {
					this.state = state;
				}

				public ticket getLastOfficeServedTicket() {
					return lastOfficeServedTicket;
				}

				public void setLastOfficeServedTicket(
						ticket lastOfficeServedTicket) {
					this.lastOfficeServedTicket = lastOfficeServedTicket;
				}
				
				
}
