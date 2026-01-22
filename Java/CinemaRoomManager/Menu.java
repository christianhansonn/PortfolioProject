package cinema;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private CinemaTheater theater;

    public Menu() {
        setTheaterSize();
    }

    public static final List<String> menuItems = new ArrayList<>(List.of(
            "1. Show the seats",
            "2. Buy a ticket",
            "3. Statistics",
            "0. Exit"
    ));


    public void setTheaterSize() {
       theater = new CinemaTheater();
       System.out.println();
    }

    public void show() {
        while (true) {
            System.out.println(String.join("\n", menuItems));
            int option = InputHandler.validateMenuOption();
            System.out.println();

            switch (option) {
                case 1:
                    showSeats();
                    System.out.println();
                    break;
                case 2:
                    buyTicket();
                    System.out.println();
                    break;
                case 3:
                    showStatistics();
                    System.out.println();
                    break;
                case 0:
                    return;
            }
        }
    }

    public void showSeats() {
        theater.display();
    }

    public void buyTicket() {
        theater.reserveSeat();
    }

    public void showStatistics() {
        System.out.println("Number of purchased tickets: " + theater.getPurchasedTickets());
        System.out.println("Percentage: " + String.format("%.2f", theater.getPercentage()) + "%");
        System.out.println("Current income: $" + theater.getTicketSales());
        System.out.println("Total income: $" + theater.getTotalIncome());
    }
}
