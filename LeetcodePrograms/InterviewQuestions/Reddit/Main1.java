package LeetcodePrograms.InterviewQuestions.Reddit;
/*

Part 1
Let's pair code a program that can do scoring for a game:
The game has exactly 2 players. Each player scores 1pt at a time. Each player starts with 0 points.
The player with a score >= 4 wins but they must win by 2 pts.
4 to 2 wins
4 to 3 does not win
5 to 3 wins
If the score is tied at or above 3, we change both scores to 3 and keep going until one player wins by 2
We should be able to:
(1) Write a function that returns the current score
(2) Write a function that increments the score of a given player, error if the game is already over
(3) Write a function that returns which player won or an error when the game is not over yet.

Part 2
Congrats, you basically implemented the scoring mechanism for Tennis!
In our modified tennis game, sets are made up of games. A set is a group of 5 games.
A player wins a set if they win 3 games.
After a game is done, the same two players play another game until the set is over.
Let's implement sets now.

Part 3
There are a few tweaks though.
Tennis has different names for the scores:
0 is called 'love'. 1 is called '15'. 2 is called '30'. 3 is called '40'.
A tied score below 3 is called “All”.
0-0 is "Love All", 1-1 is "15 All", 2-2 is "30 All"
3-3 is called “Deuce”
4-3 is called “Advantage Player 1”
3-4 is called “Advantage Player 2”
If the game is over, the score should show as "Player 1 Wins” or "Player 2 Wins"
Let's add a new function to get the current tennis score for a game.

Part 4
Let's update the code to handle the fact that players switch sides between games.

[execution time limit] 3 seconds (java)

[memory limit] 1 GB

 */
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
