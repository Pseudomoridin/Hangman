import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    hangman game = new hangman();
    game.play();
    Scanner kb = new Scanner(System.in);
    game.setRevealedWord();
    while (game.getRevealedWord().contains("_") && (game.getGuessesLeft() != 0))
    {
      System.out.println(game);
      char c = kb.next().charAt(0);
      if (!game.playerGuessedLetter(c))
      {
        game.guessedWrong(c);
      }
    }
    kb.close();
    if (game.getGuessesLeft() == 0)
    {
      System.out.println("you lose!\nthe word was " + game.getWord());
    }
    else if (!game.getRevealedWord().contains("_"))
    {
      System.out.println("\nCongradulations, you win!");
    }
  }
}