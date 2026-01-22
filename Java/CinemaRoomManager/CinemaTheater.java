package cinema;

import java.util.ArrayList;
import java.util.List;

public class CinemaTheater {
    private final List<List<String>> grid;
    private int rows;
    private int cols;
    private int purchasedTickets;
    private int ticketSales;

    public CinemaTheater() {
        getTheaterSize();
        this.grid = generateSeatingGrid(rows, cols);
    }

    private void getTheaterSize() {
         rows = InputHandler.getUserInput("Enter the number of rows: ");
         cols = InputHandler.getUserInput("Enter the number of seats in each row: ");
    }

    private List<List<String>> generateSeatingGrid(int rows, int cols) {
        List<List<String>> seats = new ArrayList<>();

        List<String> rowNumbers = new ArrayList<>(List.of(" "));
        for (int i = 1; i <= cols; i++) {
            rowNumbers.add(String.valueOf(i));
        }

        seats.add(rowNumbers);

        for (int i = 1; i <= rows; i++) {
            List<String> subList = new ArrayList<>();
            subList.add(String.valueOf(i));
            for (int j = 1; j <= cols; j++) {
                subList.add("S");
            }
            seats.add(subList);
        }

        return seats;
    }

    private boolean validReservationOption(int row, int seat) {
        try {
            return grid.get(row).get(seat).equals("S");
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public void reserveSeat() {
        int selectedRow = InputHandler.getUserInput("Enter a row number: ");
        int selectedSeat = InputHandler.getUserInput("Enter a seat number in that row: ");

        if (!validReservationOption(selectedRow, selectedSeat)) {
            if (selectedRow > getRows() || selectedSeat > getCols()) {
                System.out.println("Wrong input");
            } else {
                System.out.println("That ticket has already been purchased!");
            }
            System.out.println();
            reserveSeat();
        }

        int ticketPrice = PricingService.calculateTicketPrice(selectedRow, getRows(), getTotalSeats());
        System.out.println("Ticket price: $" + ticketPrice);

        purchasedTickets++;
        ticketSales += ticketPrice;

        grid.get(selectedRow).set(selectedSeat, "B");
        display();
    }


    public void display() {
        System.out.println("Cinema: ");
        for (List<String> row : grid) {
            System.out.println(String.join(" ", row));
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getTotalSeats() {
        return rows * cols;
    }

    public int getPurchasedTickets() { return purchasedTickets;}

    public int getTicketSales() { return ticketSales;}

    public double getPercentage() {
        return Math.round((double) purchasedTickets / getTotalSeats() * 100 * 100.0) / 100.0;
    }

    public int getTotalIncome() { return PricingService.calculateTotalProfit(getRows(), getCols()); };
}
