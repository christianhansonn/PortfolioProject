import java.util.*;

public class Game {
    Deck deck;
    int numberOfPlayers;
    List<Player> players = new ArrayList<>();
    String previousCard;
    Map<Player, Map<String, Integer>> currentHand = new HashMap<>();
    int maxTricks;
    boolean heartsBroken = false;
    Player winner;


    public Game(int numberOfPlayers) {
        if (numberOfPlayers < 2 || numberOfPlayers > 6) {
            throw new IllegalArgumentException("Number of players must be between 2 and 6");
        }

        this.numberOfPlayers = numberOfPlayers;

        System.out.println("New game started with " + numberOfPlayers + " players");

        for (int i = 1; i <= numberOfPlayers; i++) {
            players.add(new Player("Player " + i));
        }

        // Initialize the deck
        this.deck = new Deck();
        removeCardsForPlayers(numberOfPlayers);
    }

    /**
     * The function `removeCardsForPlayers` removes specific cards from a deck based on the number of
     * players in a game.
     * 
     * @param numberOfPlayers The `numberOfPlayers` parameter in the `removeCardsForPlayers` method
     * indicates the index of the array in `cardsToRemove` that contains the cards to be removed for a
     * specific number of players. For example, if `numberOfPlayers` is 5, it will access the array at
     * index
     */
    
    private void removeCardsForPlayers(int numberOfPlayers) {
        String[][] cardsToRemove = {
                {},
                {},
                {},
                {"2 Diamonds"}, 
                {},
                {"2 Diamonds", "2 Clubs"},
                {"2 Diamonds", "3 Diamonds", "3 Clubs", "4 Clubs"}
        };

        for (String card : cardsToRemove[numberOfPlayers]) {
            deck.deck.remove(card);
        }
    }

    /**
     * The `shuffleDeck` function shuffles a deck of cards and deals them to players in a round-robin
     * fashion.
     */
    public void shuffleDeck() {
        Collections.shuffle(deck.deck);
        int playerIndex = 0;
        while (!deck.deck.isEmpty()) {
            String card = deck.deck.removeFirst();
            players.get(playerIndex).hand.add(card);
            playerIndex = (playerIndex + 1) % numberOfPlayers;
        }
    }

    /**
     * The function checks if a specific card has been played by any player in the current hand.
     * 
     * @param card The `cardPlayed` method checks if a specific card is present in any of the hands in
     * the `currentHand` map. The `card` parameter represents the card that you want to check for in
     * the hands.
     * @return The method `cardPlayed` returns a boolean value indicating whether the specified card is
     * present in any of the hands in the `currentHand` map.
     */
    public boolean cardPlayed(String card) {
        return currentHand.values().stream().anyMatch(hand -> hand.containsKey(card));
    }

    /**
     * The function calculates the total tricks / points in the hand
     * 
     * @return The method `calculateTrickFromCurrentHand` returns the total value of certain cards in
     * the current hand. The value is calculated based on the presence of cards with the name
     * containing "Hearts" and the card named "Q Spades". The total value is stored in the variable
     * `max` and is returned at the end of the method.
     */
    private int calculateTrickFromCurrentHand(Map<Player, Map<String, Integer>> hand) {
        int max = 0;
        for (Map<String, Integer> card: hand.values()) {
            String cardName = card.entrySet().iterator().next().getKey();
            if (cardName.contains("Hearts")) {
                max++;
            } else if (cardName.equals("Q Spades")) {
                max += 13;
            }
        }
        System.out.println("Calculated tricks: " + max);
        return max;
    }

    /*

    The play function should play one full game of Hearts till the maxTricks >= 100

    - 2 Clubs needs to be played first at the start of each game
    - Cards will need to be reshuffled once each player runs out of cards
    - Once the all players have played their cards, and a winner adds the tricks to their player and to maxTricks, a new random card will need to played

     */
    public void play() {
        while (maxTricks < 100) {
            // Start a new round
            currentHand.clear();
            deck = new Deck();
            removeCardsForPlayers(numberOfPlayers);

            // Initialize cards per player before shuffle (shuffle removes all cards from deck)
            int cardsPerPlayer = deck.deck.size() / numberOfPlayers;

            System.out.println("Each player will get " + cardsPerPlayer + " cards.");
            System.out.println("Dealing cards to players...");
            shuffleDeck();
            heartsBroken = false;
            
            // Find the starting player for the new round
            Player startingPlayer = null;
            for (Player player: players) {
                if (numberOfPlayers != 5 && player.hand.contains("2 Clubs")) {
                    startingPlayer = player;
                    System.out.println("Player " + player.name + " has the 2 Clubs. They will start the game.");
                    break;
                } else if (numberOfPlayers == 5 && player.hand.contains("2 Spades")) {
                    startingPlayer = player;
                    System.out.println("Player " + player.name + " has the 2 Spades. They will start the game.");
                    break;
                }
            }

            // Add safety check to avoid infinite loop if no player has 2 Clubs || 2 Spades
            if (startingPlayer == null) {
                if (numberOfPlayers != 5) {
                    System.out.println("Error: No player has the 2 Clubs card. Exiting game.");
                    return;
                } else {
                    System.out.println("Error: No player has the 2 Spades card. Exiting game.");
                    return;
                }
            }

            for (int trick = 0; trick < cardsPerPlayer; trick++) {
                String leadSuit = null;
                Player trickWinner = null;
                int highestRank = -1;
                currentHand.clear();

                // Make sure to start the first trick with 2 Clubs || 2 Spades
                if (trick == 0) {
                    if (numberOfPlayers != 5) {
                        Map<String, Integer> firstCard = startingPlayer.playCard(null, "2 Clubs", false);
                        currentHand.put(startingPlayer, firstCard);
                        leadSuit = "Clubs";
                        highestRank = firstCard.values().iterator().next();
                    } else {
                        Map<String, Integer> firstCard = startingPlayer.playCard(null, "2 Spades", false);
                        currentHand.put(startingPlayer, firstCard);
                        leadSuit = "Spades";
                        highestRank = firstCard.values().iterator().next();
                    }
                } else {
                    trickWinner = startingPlayer;
                    System.out.println("Starting new hand. " + startingPlayer.name + " will go first.");
                    System.out.println("Hearts broken: " + heartsBroken);
                    Map<String, Integer> firstCard = startingPlayer.playCard(null, null, heartsBroken);    
                    String playedCard = firstCard.keySet().iterator().next();
                    currentHand.put(startingPlayer, firstCard);
                    leadSuit = playedCard.split(" ")[1];
                    highestRank = firstCard.values().iterator().next();
                }

                // Fix the player turn ordering
                int currentPlayerIndex = players.indexOf(startingPlayer);
                for (int i = 1; i < numberOfPlayers; i++) {
                    // Calculate the correct index for the next player (moving clockwise around table)
                    int nextPlayerIndex = (currentPlayerIndex + i) % numberOfPlayers;
                    Player nextPlayer = players.get(nextPlayerIndex);
                    
                    if (nextPlayer.hand.isEmpty()) {
                        // Skipping player since they have an empty hand
                        continue;
                    }
                    Map<String, Integer> playedCard = nextPlayer.playCard(leadSuit, null, heartsBroken);
                    
                    // Error handling for null card plays
                    if (playedCard == null || playedCard.isEmpty()) {
                        continue;
                    }
                    
                    currentHand.put(nextPlayer, playedCard);
                    String cardName = playedCard.keySet().iterator().next();
                    int cardRank = playedCard.values().iterator().next();
                    
                    // Check if this is a card of the lead suit and has a higher rank
                    if (cardName.contains(leadSuit) && cardRank > highestRank) {
                        highestRank = cardRank;
                        trickWinner = nextPlayer;
                    }
                    
                    // Check if a heart was played
                    if (cardName.contains("Hearts")) {
                        heartsBroken = true;
                    }
                }

                int totalTricks = calculateTrickFromCurrentHand(currentHand);
                trickWinner.addTrick(totalTricks);

                System.out.println("Player " + trickWinner + " won the hand and got " + totalTricks + " additional tricks.");
                System.out.println("Player " + trickWinner + "'s new total is now " + trickWinner.tricks);
                System.out.println("--------------------------END OF HAND--------------------------");
                
                if (trickWinner.tricks > maxTricks) {
                    winner = trickWinner;
                    maxTricks = trickWinner.tricks;
                    startingPlayer = trickWinner;
                }
                
                // Check if we have a winner after this trick
                if (maxTricks >= 100) {
                    // Game over, we have a winner
                    return;
                }
            }
            
            // After a round ends, reset player hands for the next round
            for (Player player : players) {
                player.hand.clear();
            }
        }
    }
}
