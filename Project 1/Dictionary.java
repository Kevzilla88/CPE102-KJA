import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;

public class Dictionary
{
   ArrayList<String> dict;
   Path filePath;
   Random wordPicker;
   Scanner fileRead;
   
   public Dictionary(String aFilePath)
   {
      dict = new ArrayList<String>();
      fileRead = new Scanner(aFilePath);
      wordPicker = new Random();
   }
   
   public void buildDictionary()
   {
      while(fileRead.hasNext())
      {
         dict.add(fileRead.next());
      }
   }
   
   public String randomWord()
   {
      int wordIndex = (int) Math.random()*dict.size();
      String word = dict.get(wordIndex);
      return word;
   }
}