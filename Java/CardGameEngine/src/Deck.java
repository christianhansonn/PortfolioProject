import java.util.ArrayList;
import java.util.List;

public class Deck {

    public String[] cards = {"A","K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"};
    public String[] suits = {"Spades", "Clubs", "Diamonds", "Hearts"};
    public List<String> deck = new ArrayList<>();

    public Deck() {
        for (String suit : suits) {
            for (String card: cards) {
                deck.add(card + " " + suit);
            }
        }
    }

    public List<String> getDeck() {
        return deck;
    }

}