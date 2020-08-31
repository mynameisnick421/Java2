public class program {
    public static void main(String[] args) {

        Reservation[] reservations;	//statement 1

        reservations = new Reservation[4];	//statement 2

        reservations[3] = new Reservation(1001, "Mickey Mouse", true);	//statement 3

        //reservations[2].setResNumber = 2000;	//statement 4

        reservations[1] = new Reservation();	//statement 5
        reservations[4] = new Reservation(1002, "Minnie Mouse", false);	//statement 6


    }
}
