package LeetcodePrograms.InterviewQuestions.Reddit;
// Let's pair code a program that can do scoring for a game:
// The game has exactly 2 players. Each player scores 1pt at a time. Each player starts with 0 points.
// The player with a score >= 4 wins but they must win by 2 pts.
// 4 to 2 wins
// 4 to 3 does not win
// 5 to 3 wins
// If the score is tied at or above 3, we change both scores to 3 and keep going until one player wins by 2
// We should be able to:
//(1) Write a function that returns the current score
//(2) Write a function that increments the score of a given player, error if the game is already over
//(3) Write a function that returns which player won or an error when the game is not over yet.
public class Part1WithoutOOP {

    private int player1Score;
    private int player2Score;
    private boolean isGameOver;

    public Part1WithoutOOP() {
        this.player1Score = 0;
        this.player2Score = 0;
        this.isGameOver = false;
    }

    // (1) Function to get current score
    public String getCurrentScore() {
        return "Player 1: " + player1Score + ", Player 2: " + player2Score;
    }

    // (2) Function to increment score of a given player
    public void scorePoint(int playerNumber) {
        if (isGameOver) {
            throw new IllegalStateException("Game is already over.");
        }

        if (playerNumber == 1) {
            player1Score++;
        } else if (playerNumber == 2) {
            player2Score++;
        } else {
            throw new IllegalArgumentException("Invalid player number. Must be 1 or 2.");
        }

        // Check tie at or above 3
        if (player1Score >= 3 && player2Score >= 3 && player1Score == player2Score) {
            player1Score = 3;
            player2Score = 3;
        }

        // Check win condition
        if ((player1Score >= 4 || player2Score >= 4) && Math.abs(player1Score - player2Score) >= 2) {
            isGameOver = true;
        }
    }

    // (3) Function to get winner
    public String getWinner() {
        if (!isGameOver) {
            throw new IllegalStateException("Game is not over yet.");
        }

        return player1Score > player2Score ? "Player 1 wins" : "Player 2 wins";
    }

    // Simple test
    public static void main(String[] args) {
        Part1WithoutOOP game = new Part1WithoutOOP();

        game.scorePoint(1); // P1: 1
        game.scorePoint(2); // P2: 1
        game.scorePoint(1); // P1: 2
        game.scorePoint(2); // P2: 2
        game.scorePoint(1); // P1: 3
        game.scorePoint(2); // P2: 3 -> both set to 3
        game.scorePoint(1); // P1: 4
        game.scorePoint(1); // P1: 5 -> wins


        System.out.println(game.getCurrentScore()); // Player 1: 5, Player 2: 3
        System.out.println(game.getWinner());       // Player 1 wins
    }






}