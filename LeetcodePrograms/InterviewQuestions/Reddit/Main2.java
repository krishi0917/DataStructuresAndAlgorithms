package LeetcodePrograms.InterviewQuestions.Reddit;

import java.util.HashMap;
import java.util.Map;


    // (existing)
     class Player {
        private final String name;          // (existing)
        private int score;                  // (existing)

        public Player(String name) {        // (existing)
            this.name = name;               // (existing)
            this.score = 0;                 // (existing)
        }

        public String getName() {           // (existing)
            return name;                    // (existing)
        }

        public int getScore() {             // (existing)
            return score;                   // (existing)
        }

        public void incrementScore() {      // (existing)
            this.score++;                   // (existing)
        }

        public void setScore(int score) {   // (existing)
            this.score = score;             // (existing)
        }
    }
class Game2 {
    private final Player player1;                   // (existing)
    private final Player player2;                   // (existing)
    private boolean isGameOver;                     // (existing)

    public Game2(Player player1, Player player2) {   // (new - constructor changed to accept existing players)
        this.player1 = player1;                     // (new)
        this.player2 = player2;                     // (new)
        resetScores();                              // (new)
    }

    public void resetScores() {                     // (new - reusable reset logic)
        player1.setScore(0);                        // (new)
        player2.setScore(0);                        // (new)
        isGameOver = false;                         // (new)
    }

    public String getCurrentScore() {               // (existing)
        return player1.getName() + ": " + player1.getScore() + ", " +
                player2.getName() + ": " + player2.getScore();
    }

    public void scorePoint(String playerName) {     // (existing)
        if (isGameOver) {
            throw new IllegalStateException("Game is already over.");
        }

        Player scoringPlayer = getPlayerByName(playerName);  // (existing)
        scoringPlayer.incrementScore();                      // (existing)

        if (player1.getScore() >= 3 && player2.getScore() >= 3 &&
                player1.getScore() == player2.getScore()) {
            player1.setScore(3);                             // (existing)
            player2.setScore(3);                             // (existing)
        }

        if ((player1.getScore() >= 4 || player2.getScore() >= 4) &&
                Math.abs(player1.getScore() - player2.getScore()) >= 2) {
            isGameOver = true;                                // (existing)
        }
    }

    public String getWinner() {                               // (existing)
        if (!isGameOver) {
            throw new IllegalStateException("Game is not over yet.");
        }
        return player1.getScore() > player2.getScore() ?
                player1.getName() : player2.getName();
    }

    public boolean isGameOver() {                             // (new)
        return isGameOver;
    }

    private Player getPlayerByName(String name) {             // (existing)
        if (player1.getName().equals(name)) return player1;
        if (player2.getName().equals(name)) return player2;
        throw new IllegalArgumentException("Unknown player: " + name);
    }
}

    class Set {                                            // (new)
        private final Player player1;                             // (new)
        private final Player player2;                             // (new)
        private final Game2 currentGame;                           // (new)
        private final Map<String, Integer> gamesWon;              // (new)
        private boolean isSetOver;                                // (new)

        public Set(String player1Name, String player2Name) {      // (new)
            this.player1 = new Player(player1Name);               // (new)
            this.player2 = new Player(player2Name);               // (new)
            this.currentGame = new Game2(player1, player2);        // (new)
            this.gamesWon = new HashMap<>();                      // (new)
            gamesWon.put(player1Name, 0);                         // (new)
            gamesWon.put(player2Name, 0);                         // (new)
            this.isSetOver = false;                               // (new)
        }

        public void scorePoint(String playerName) {               // (new)
            if (isSetOver) {
                throw new IllegalStateException("Set is already over.");
            }

            currentGame.scorePoint(playerName);                   // (new)

            if (currentGame.isGameOver()) {                       // (new)
                String winner = currentGame.getWinner();          // (new)
                gamesWon.put(winner, gamesWon.get(winner) + 1);   // (new)

                System.out.println("Game won by: " + winner);     // (new)

                if (gamesWon.get(winner) == 3) {                  // (new)
                    isSetOver = true;
                    System.out.println("Set won by: " + winner);
                } else {
                    currentGame.resetScores();                    // (new)
                }
            }
        }

        public String getSetScore() {                             // (new)
            return "Games Won - " + player1.getName() + ": " + gamesWon.get(player1.getName()) +
                    ", " + player2.getName() + ": " + gamesWon.get(player2.getName());
        }

        public boolean isSetOver() {                              // (new)
            return isSetOver;
        }
    }
class Main2{
        public static void main(String[] args) {
            Set tennisSet = new Set("Alice", "Bob");              // (new)

            // Simulate a full set with Alice winning 3 games
            while (!tennisSet.isSetOver()) {                      // (new)
                while (true) {
                    try {
                        tennisSet.scorePoint("Alice");            // (new)
                    } catch (IllegalStateException e) {
                        break;  // game ended
                    }
                }
            }

            System.out.println(tennisSet.getSetScore());          // (new)
        }
    }


