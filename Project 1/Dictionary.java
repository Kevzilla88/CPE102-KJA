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
   
   public Dictionary(String aFilePath) throws FileNotFoundException
   {
      dict = new ArrayList<String>();
      fileRead = new Scanner(new File(aFilePath));
      wordPicker = new Random();
   }
   
   public void buildDictionary()
   {
      fileRead.useDelimiter(" ");
      while(fileRead.hasNext())
      {
         dict.add(fileRead.next());
      }
   }
   
   public String randomWord()
   {
      double wordIndex = Math.random()*dict.size();      
      String word = dict.get((int) wordIndex);
      return word;
   }
}