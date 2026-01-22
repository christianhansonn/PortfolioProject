package cinema;

public class PricingService {

    static int calculateProfit(int totalSeats, int pricePerSeat) {
        return totalSeats * pricePerSeat;
    }

    static int smallRoomProfit(int totalSeats) {
        return calculateProfit(totalSeats, 10);
    }

    static int largeRoomProfit(int rows, int cols) {
        int frontHalf = rows / 2;
        int backHalf = rows - frontHalf;

        int frontSeats = frontHalf * cols;
        int backSeats = backHalf * cols;

        int frontProfit = calculateProfit(frontSeats, 10);
        int backProfit = calculateProfit(backSeats, 8);
        return frontProfit + backProfit;
    }

    public static int calculateTotalProfit(int rows, int cols) {
        int totalSeats = rows * cols;

        if (totalSeats <= 60) {
            return smallRoomProfit(totalSeats);
        }

        return largeRoomProfit(rows, cols);
    }

    public static int calculateTicketPrice(int selectedRow, int totalRows, int totalSeats) {
        if (totalSeats <= 60) {
            return 10;
        }

        int frontHalf = totalRows / 2;
        if (selectedRow <= frontHalf) {
            return 10;
        } else {
            return 8;
        }
    }
}
