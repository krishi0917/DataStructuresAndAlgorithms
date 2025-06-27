package LeetcodePrograms.InterviewQuestions.Reddit;

    class Playerxyz {
        private final String name;
        private int score;

        public Playerxyz(String name) {
            this.name = name;
            this.score = 0;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        public void incrementScore() {
            this.score++;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }

     class Game {
    private final Playerxyz player1;
    private final Playerxyz player2;
    private boolean isGameOver;

    public Game(String player1Name, String player2Name) {
        this.player1 = new Playerxyz(player1Name);
        this.player2 = new Playerxyz(player2Name);
        this.isGameOver = false;
    }

    // (1) Get current score
    public String getCurrentScore() {
        return player1.getName() + ": " + player1.getScore() + ", " +
                player2.getName() + ": " + player2.getScore();
    }

    // (2) Increment score for a player
    public void scorePoint(String playerName) {
        if (isGameOver) {
            throw new IllegalStateException("Game is already over.");
        }

        Playerxyz scoringPlayer = getPlayerByName(playerName);
        scoringPlayer.incrementScore();

        // Handle deuce/tie at or above 3
        if (player1.getScore() >= 3 && player2.getScore() >= 3 &&
                player1.getScore() == player2.getScore()) {
            player1.setScore(3);
            player2.setScore(3);
        }

        // Check win condition
        if ((player1.getScore() >= 4 || player2.getScore() >= 4) &&
                Math.abs(player1.getScore() - player2.getScore()) >= 2) {
            isGameOver = true;
        }
    }

    // (3) Get the winner
    public String getWinner() {
        if (!isGameOver) {
            throw new IllegalStateException("Game is not over yet.");
        }
        return (player1.getScore() > player2.getScore()) ?
                player1.getName() + " wins" : player2.getName() + " wins";
    }

    // Helper
    private Playerxyz getPlayerByName(String name) {
        if (player1.getName().equals(name)) return player1;
        if (player2.getName().equals(name)) return player2;
        throw new IllegalArgumentException("Unknown player: " + name);
    }
}

        public class Main1{
        // Sample run
        public static void main(String[] args) {
            Game game = new Game("Alice", "Bob");

            game.scorePoint("Alice"); // 1
            game.scorePoint("Bob");   // 1
            game.scorePoint("Alice"); // 2
            game.scorePoint("Bob");   // 2
            game.scorePoint("Alice"); // 3
            game.scorePoint("Bob");   // 3 -> both = 3
            game.scorePoint("Alice"); // 4
            game.scorePoint("Alice"); // 5 -> Alice wins

            System.out.println(game.getCurrentScore()); // Alice: 5, Bob: 3
            System.out.println(game.getWinner());       // Alice wins
        }
    }
