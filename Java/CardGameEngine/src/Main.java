public class Main {
    public static void main(String[] args) {
        Game game = new Game(4);
        game.play();
        System.out.println("Player " + game.winner + " had the most tricks with " + game.maxTricks + " total tricks.");
        System.out.println("Leaderboard:");
        for (Player player : game.players) {
            System.out.println(player.name + " had " + player.tricks + " total tricks");
        }
    }
}