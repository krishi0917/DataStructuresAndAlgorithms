package LeetcodePrograms.InterviewQuestions.Reddit;

import java.util.HashMap;
import java.util.Map;


// (existing)
class Playerqwe {
    private final String name;          // (existing)
    private int score;                  // (existing)

    public Playerqwe(String name) {        // (existing)
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
class Game4 {
    private final Playerqwe player1;                   // (existing)
    private final Playerqwe player2;                   // (existing)
    private boolean isGameOver;                     // (existing)

    public Game4(Playerqwe player1, Playerqwe player2) {   // (new - constructor changed to accept existing players)
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

        Playerqwe scoringPlayer = getPlayerByName(playerName);  // (existing)
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

    private Playerqwe getPlayerByName(String name) {             // (existing)
        if (player1.getName().equals(name)) return player1;
        if (player2.getName().equals(name)) return player2;
        throw new IllegalArgumentException("Unknown player: " + name);
    }

    // 🎾 NEW METHOD: Tennis-style score description
    public String getTennisScore() {                       // (existing3)
        if (isGameOver) {                                  // (existing3)
            return getWinner() + " Wins";                  // (existing3)
        }

        int p1 = player1.getScore();                       // (existing3)
        int p2 = player2.getScore();                       // (existing3)

        if (p1 >= 3 && p2 >= 3 && p1 == p2) {              // (existing3)
            return "Deuce";                                // (existing3)
        }

        if (p1 >= 4 && p1 - p2 == 1) {                     // (existing3)
            return "Advantage " + player1.getName();       // (existing3)
        }

        if (p2 >= 4 && p2 - p1 == 1) {                     // (existing3)
            return "Advantage " + player2.getName();       // (existing3)
        }

        if (p1 == p2 && p1 < 3) {                          // (existing3)
            return mapScore(p1) + " All";                  // (existing3)
        }

        return mapScore(p1) + " - " + mapScore(p2);        // (existing3)
    }

    // 🎾 Helper to map numeric score to tennis terms
    private String mapScore(int score) {                   // (existing3)
        switch (score) {                                   // (existing3)
            case 0: return "Love";                         // (existing3)
            case 1: return "15";                           // (existing3)
            case 2: return "30";                           // (existing3)
            case 3: return "40";                           // (existing3)
            default: return "";                            // (existing3)
        }
    }
}

class Setqwe {                                            // (existing2)
    private Playerqwe leftPlayer;                             // (existing2)
    private Playerqwe rightPlayer;                             // (existing2)
    private Game4 currentGame;                           // (existing2)
    private final Map<String, Integer> gamesWonabc;              // (existing2)
    private boolean isSetOver;                                // (existing2)

    public Setqwe(String player1Name, String player2Name) {      // (existing2)
        this.leftPlayer = new Playerqwe(player1Name);         // (new)
        this.rightPlayer = new Playerqwe(player2Name);        // (new)
        this.currentGame = new Game4(leftPlayer, rightPlayer); // (new)
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
                // may need to resetscore as well not sure
                switchSides();                             // ✅ (new)
                currentGame = new Game4(leftPlayer, rightPlayer);  // ✅ (new)
            }
        }
    }

    // ✅ NEW: swap players after every game
    private void switchSides() {                           // (new)
        Playerqwe temp = leftPlayer;                          // (new)
        leftPlayer = rightPlayer;                          // (new)
        rightPlayer = temp;                                // (new)
        System.out.println("Players switched sides.");     // (new)
    }

    public String getSetScore() {                             // (existing2)
        return "Games Won - " + leftPlayer.getName() + ": " + gamesWonabc.get(leftPlayer.getName()) +
                ", " + rightPlayer.getName() + ": " + gamesWonabc.get(rightPlayer.getName());
    }

    public boolean isSetOver() {                              // (existing2)
        return isSetOver;
    }

    public Game4 getCurrentGame() {             // (existing3)
        return currentGame;
    }
}
class Main4{
    public static void main(String[] args) {

        Setqwe tennisSet = new Setqwe("Alice", "Bob");                 // (existing)

        while (!tennisSet.isSetOver()) {                         // (existing)
            Game4 game = tennisSet.getCurrentGame();              // (existing)
            while (!game.isGameOver()) {
                game.scorePoint(game.getWinner() == null ? "Alice" : game.getWinner());  // Simulate
                System.out.println(game.getTennisScore());       // (existing)
            }
            System.out.println(); // line break between games
        }

        System.out.println(tennisSet.getSetScore());             // (existing)
    }

    }





