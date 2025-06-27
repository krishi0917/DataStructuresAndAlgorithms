package LeetcodePrograms.InterviewQuestions.Reddit;

import java.util.HashMap;
import java.util.Map;


// (existing)
class Playerabc {
    private final String name;          // (existing)
    private int score;                  // (existing)

    public Playerabc(String name) {        // (existing)
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
class Game3 {
    private final Playerabc player1;                   // (existing)
    private final Playerabc player2;                   // (existing)
    private boolean isGameOver;                     // (existing)

    public Game3(Playerabc player1, Playerabc player2) {   // (new - constructor changed to accept existing players)
        this.player1 = player1;                     // (existing2)
        this.player2 = player2;                     // (existing2)
        resetScores();                              // (existing2)
    }

    public void resetScores() {                     // (new - reusable reset logic)
        player1.setScore(0);                        // (existing2)
        player2.setScore(0);                        // (existing2)
        isGameOver = false;                         // (existing2)
    }

    public String getCurrentScore() {               // (existing)
        return player1.getName() + ": " + player1.getScore() + ", " +
                player2.getName() + ": " + player2.getScore();
    }

    public void scorePoint(String playerName) {     // (existing)
        if (isGameOver) {
            throw new IllegalStateException("Game is already over.");
        }

        Playerabc scoringPlayer = getPlayerByName(playerName);  // (existing)
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

    public boolean isGameOver() {                             // (existing2)
        return isGameOver;
    }

    private Playerabc getPlayerByName(String name) {             // (existing)
        if (player1.getName().equals(name)) return player1;
        if (player2.getName().equals(name)) return player2;
        throw new IllegalArgumentException("Unknown player: " + name);
    }

    // 🎾 NEW METHOD: Tennis-style score description
    public String getTennisScore() {                       // (new)
        if (isGameOver) {                                  // (new)
            return getWinner() + " Wins";                  // (new)
        }

        int p1 = player1.getScore();                       // (new)
        int p2 = player2.getScore();                       // (new)

        if (p1 >= 3 && p2 >= 3 && p1 == p2) {              // (new)
            return "Deuce";                                // (new)
        }

        if (p1 >= 4 && p1 - p2 == 1) {                     // (new)
            return "Advantage " + player1.getName();       // (new)
        }

        if (p2 >= 4 && p2 - p1 == 1) {                     // (new)
            return "Advantage " + player2.getName();       // (new)
        }

        if (p1 == p2 && p1 < 3) {                          // (new)
            return mapScore(p1) + " All";                  // (new)
        }

        return mapScore(p1) + " - " + mapScore(p2);        // (new)
    }

    // 🎾 Helper to map numeric score to tennis terms
    private String mapScore(int score) {                   // (new)
        switch (score) {                                   // (new)
            case 0: return "Love";                         // (new)
            case 1: return "15";                           // (new)
            case 2: return "30";                           // (new)
            case 3: return "40";                           // (new)
            default: return "";                            // (new)
        }
    }
}

class Setabc {                                            // (existing2)
    private final Playerabc player1;                             // (existing2)
    private final Playerabc player2;                             // (existing2)
    private final Game3 currentGame;                           // (existing2)
    private final Map<String, Integer> gamesWonabc;              // (existing2)
    private boolean isSetOver;                                // (existing2)

    public Setabc(String player1Name, String player2Name) {      // (existing2)
        this.player1 = new Playerabc(player1Name);               // (existing2)
        this.player2 = new Playerabc(player2Name);               // (existing2)
        this.currentGame = new Game3(player1, player2);        // (existing2)
        this.gamesWonabc = new HashMap<>();                      // (existing2)
        gamesWonabc.put(player1Name, 0);                         // (existing2)
        gamesWonabc.put(player2Name, 0);                         // (existing2)
        this.isSetOver = false;                               // (existing2)
    }

    public void scorePoint(String playerName) {               // (existing2)
        if (isSetOver) {
            throw new IllegalStateException("Set is already over.");
        }

        currentGame.scorePoint(playerName);                   // (existing2)

        if (currentGame.isGameOver()) {                       // (existing2)
            String winner = currentGame.getWinner();          // (existing2)
            gamesWonabc.put(winner, gamesWonabc.get(winner) + 1);   // (existing2)

            System.out.println("Game won by: " + winner);     // (existing2)

            if (gamesWonabc.get(winner) == 3) {                  // (existing2)
                isSetOver = true;
                System.out.println("Set won by: " + winner);
            } else {
                currentGame.resetScores();                    // (existing2)
            }
        }
    }

    public String getSetScore() {                             // (existing2)
        return "Games Won - " + player1.getName() + ": " + gamesWonabc.get(player1.getName()) +
                ", " + player2.getName() + ": " + gamesWonabc.get(player2.getName());
    }

    public boolean isSetOver() {                              // (existing2)
        return isSetOver;
    }

    public Game3 getCurrentGame() {             // (new)
        return currentGame;
    }
}
class Main3{

        /*
        Setabc tennisSet = new Setabc("Alice", "Bob");              // (existing2)

        // Simulate a full set with Alice winning 3 games
        while (!tennisSet.isSetOver()) {                      // (existing2)
            while (true) {
                try {
                    tennisSet.scorePoint("Alice");            // (existing2)
                } catch (IllegalStateException e) {
                    break;  // game ended
                }
            }
        }

        System.out.println(tennisSet.getSetScore());          // (existing2)

         */

        public static void main(String[] args) {
            Setabc tennisSet = new Setabc("Alice", "Bob");                    // (existing)

            // Access current game and score with tennis terms
            Game3 currentGame = tennisSet.getCurrentGame();              // (new)

            currentGame.scorePoint("Alice");                            // (existing)
            System.out.println(currentGame.getTennisScore());           // (new) → "15 - Love"

            currentGame.scorePoint("Bob");
            currentGame.scorePoint("Bob");
            System.out.println(currentGame.getTennisScore());           // (new) → "15 - 30"

            currentGame.scorePoint("Alice");
            currentGame.scorePoint("Alice");
            System.out.println(currentGame.getTennisScore());           // (new) → "40 - 30"

            currentGame.scorePoint("Bob");
            System.out.println(currentGame.getTennisScore());           // (new) → "Deuce"

            currentGame.scorePoint("Alice");
            System.out.println(currentGame.getTennisScore());           // (new) → "Advantage Alice"

            currentGame.scorePoint("Alice");
            System.out.println(currentGame.getTennisScore());           // (new) → "Alice Wins"
        }


}


