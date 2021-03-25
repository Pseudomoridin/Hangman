//imports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

class hangman
{
  //variable declaration
  private String word;
  private int guessesLeft;
  private File words;
  private ArrayList<String> wordsArray;
  private ArrayList<Integer> correctIndexes;
  private String revealedWord;
  private ArrayList<Character> incorrectGuesses;
  
  /*
  *constructors
  */
  public hangman()
  {
    guessesLeft = 6;
    correctIndexes = new ArrayList<Integer>();
    incorrectGuesses = new ArrayList<Character>();
  }

  public hangman(String word)
  {
    this.word = word;
    guessesLeft = 6;
    correctIndexes = new ArrayList<Integer>();
    incorrectGuesses = new ArrayList<Character>();
  }

  public hangman(int numGuesses)
  {
    guessesLeft = numGuesses;
    correctIndexes = new ArrayList<Integer>();
    incorrectGuesses = new ArrayList<Character>();
  }

  public hangman(String word, int numGuesses)
  {
    this.word = word;
    guessesLeft = numGuesses;
    correctIndexes = new ArrayList<Integer>();
    incorrectGuesses = new ArrayList<Character>();
  }

  /*
  *getters & setters
  */
  //getters
  public String getWord() {return word;}
  public int getGuessesLeft() {return guessesLeft;}
  public ArrayList<String> getWordsArray() {return wordsArray;}
  public String getRevealedWord() {return revealedWord;}

  //setters
  public void setWord(String word) {this.word = word;}
  public void setGuessesLeft(int guessesLeft) {this.guessesLeft = guessesLeft;}
  public void guessedWrong(char c) 
  {
    this.guessesLeft -= 1;
    incorrectGuesses.add(c);
  }

  /*
  *function declaration
  */
  //picking a word
  public void readFileAsStringArray()
  {
    try
    {
      words = new File("words.txt");
      Scanner txt = new Scanner(words);
      wordsArray = new ArrayList<String>();
      while (txt.hasNextLine())
      {
        wordsArray.add(txt.nextLine());
      }
      txt.close();
    }
    catch (FileNotFoundException e)
    {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    //randomize, skip if word is already set?
    Random rand = new Random();
    word = wordsArray.get(rand.nextInt(wordsArray.size()));
  }

  //runs automatically to check word
  public void setRevealedWord()
  {
    revealedWord = "";
    for (int i = 0; i < word.length(); i++)
    {
      if (correctIndexes.contains(i))
      {
        revealedWord += word.charAt(i);
      }
      else
      {
        revealedWord += "_";
      }
    }
  }

  //checks if a letter is in the word and runs setRevealedWord()
  public boolean playerGuessedLetter(char c)
  {
    boolean charInWord = false;
    for (int i = 0; i < word.length(); i++)
    {
      if ((word.charAt(i) == c) && (!correctIndexes.contains(i)))
      {
        correctIndexes.add(i);
        charInWord = true;
      }
    }
    setRevealedWord();
    return charInWord;
  }

  public String toString()
  {
    String classAsString = "";
    classAsString += revealedWord + "\n";
    classAsString += "what character would you like to guess?\n";
    classAsString += "so far you have incorrectly guessed:";
    for (char item : incorrectGuesses)
    {
      classAsString += " " + item + ",";
    }
    classAsString += "\nyou have " + guessesLeft + " guesses left\n";
    return classAsString;
  }

  //runs the game
  public void play()
  {
    readFileAsStringArray();
    //System.out.println(word);
  }
}