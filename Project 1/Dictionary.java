import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Dictionary
{
   ArrayList<String> dict;
   Path filePath;
   Random wordPicker;
   Scanner fileRead;
   
   /**
   A dictionary that reads words from a text file.
   @param aFilePath String that specifies the relative location of a text file
   */
   public Dictionary(String aFilePath) throws FileNotFoundException
   {
      dict = new ArrayList<String>();
      fileRead = new Scanner(new File(aFilePath));
      wordPicker = new Random();
   }
   
   /**
   Adds all of the words from the specified file to a dictionary. Words in file must be separated only by a single space.
   */
   public void buildDictionary()
   {
      fileRead.useDelimiter(" ");
      while(fileRead.hasNext())
      {
         dict.add(fileRead.next());
      }
   }
   
   /**
   Chooses a random word out of all of the words in the dictionary with pseudorandom distribution (using Math.random).
   @return A random word
   */
   public String randomWord()
   {
      double wordIndex = Math.random()*dict.size();      
      String word = dict.get((int) wordIndex);
      return word;
   }
}