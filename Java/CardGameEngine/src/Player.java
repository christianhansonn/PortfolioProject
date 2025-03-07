import java.util.*;
import java.util.stream.Collectors;

public class Player {
    String name;
    int tricks;
    List<String> hand = new ArrayList<>();
    HashMap<String, Integer> cardRanking = new HashMap<>();

    public Player(String name) {
        this.name = name;
        initializeCardRanking();
    }

    private void initializeCardRanking() {
        int rank = 13;
        for (String card: new Deck().cards) {
            cardRanking.put(card, rank--);
        }
    }

    public void addTrick(int points) {
        tricks += points;
    }

    public int getCardRanking(String cardName) {
        // Extract just the card value (e.g. "2" from "2 Clubs")
        String cardValue = cardName.split(" ")[0];
        return cardRanking.getOrDefault(cardValue, 0);
    }
    
    /**
     * The function `playCard` determines which method to call based on the suit parameter and whether
     * hearts are broken.
     * 
     * @param suit The `suit` parameter in the `playCard` method represents the suit of the card that
     * the player wants to play. It is a string value that can be "hearts", "diamonds", "clubs", or
     * "spades".
     * @param cardName The `cardName` parameter represents the name of the card that the player wants
     * to play.
     * @param heartsBroken The `heartsBroken` parameter is a boolean flag indicating whether the hearts
     * suit has been broken in the current game. If `heartsBroken` is true, it means that at least one
     * heart card has been played in the game, and players are now allowed to play heart cards. Otherwise
     * the `playcard` function will filter out hearts from the deck of available cards
     * @return The `playCard` method returns a `Map<String, Integer>`.
     */
    public Map<String, Integer> playCard(String suit, String cardName, boolean heartsBroken) {
        if (suit == null && cardName != null) {
            return playSpecificCard(cardName);
        }
        return playCardOfSuit(suit, heartsBroken);
    }

    private Map<String, Integer> playSpecificCard(String cardName) {
        if (hand.contains(cardName)) {
            // Extract just the card value (e.g. "2" from "2 Clubs")
            String cardValue = cardName.split(" ")[0];
            int selectedRanking = cardRanking.get(cardValue);
            
            hand.remove(cardName);
            Map<String, Integer> cardPlayed = new HashMap<>();
            cardPlayed.put(cardName, selectedRanking);
            
            System.out.println("Player " + name + " just played " + cardName);
            return cardPlayed;
        } else {
            System.out.println("Card '" + cardName + "' is not in player's hand.");
            return null;
        }
    }

    private Map<String, Integer> playCardOfSuit(String suit, boolean heartsBroken) {
        if (suit == null) {
            return playAnyCard(heartsBroken);
        }

        List<String> cardsOfSuit = new ArrayList<>();
        for (String card: hand) {
            if (card.contains(suit)) {
                cardsOfSuit.add(card);
            }
        }

        if (cardsOfSuit.isEmpty()) {
            // If the player doesn't have cards of that suit then they can play hearts
            return playAnyCard(true);
        }

        // Find the card with the lowest rank
        String cardToPlay = cardsOfSuit.stream()
                .min(Comparator.comparingInt(this::getCardRanking))
                .orElseThrow();

        hand.remove(cardToPlay);
        Map<String, Integer> cardPlayed = new HashMap<>();
        cardPlayed.put(cardToPlay, getCardRanking(cardToPlay));
        System.out.println("Player " + name + " just played " + cardToPlay);
        return cardPlayed;
    }

   private Map<String, Integer> playAnyCard(boolean heartsBroken) {
        if (!heartsBroken) {
            List<String> filteredHand = hand.stream()
                    .filter(card -> !card.split(" ")[1].equals("Hearts"))
                    .collect(Collectors.toList());
            
            if (filteredHand.isEmpty()) {
                // If no non-heart cards, must play hearts even if not broken
                return playCardFromDeck(hand);
            }
            return playCardFromDeck(filteredHand);
        }
        return playCardFromDeck(hand);
   }

   private HashMap<String, Integer> playCardFromDeck(List<String> deckOfCards) {
        if (deckOfCards.isEmpty()) {
            System.out.println("Player " + name + " has no cards to play!");
            return new HashMap<>();
        }

        String cardToPlay = deckOfCards.stream()
                .min(Comparator.comparingInt(this::getCardRanking))
                .orElseThrow();

        hand.remove(cardToPlay);
        HashMap<String, Integer> cardPlayed = new HashMap<>();
        cardPlayed.put(cardToPlay, getCardRanking(cardToPlay));
        System.out.println("Player " + name + " just played " + cardToPlay);
        return cardPlayed;
   }

   @Override
   public String toString() {
       return name;
   }
}
