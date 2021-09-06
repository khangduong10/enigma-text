import java.io.*;
import java.util.*;
public class EnigmaTester
{
  public static int getFileSize(String fileName)throws IOException
   {
      Scanner input = new Scanner(new FileReader(fileName));
      int size=0;
      while (input.hasNextLine())				//while there is another line in the file
      {
         size++;										//add to the size
         input.nextLine();							//go to the next line in the file
      }
      input.close();									//always close the files when you are done
      return size;
   }
   
   	//pre:  "fileName" is the name of a real file containing lines of text - the first line intended to be unused
      //post:returns a String array of all the elements in <filename>.txt, with index 0 unused (heap) O(n)
   public static String getSample(String fileName)throws IOException
   {
      int size = getFileSize(fileName);		//holds the # of elements in the file
      String line="";	
      Scanner input = new Scanner(new FileReader(fileName));
      for(int x = 0;x<(int)(Math.random()*size);x++){
         line = input.nextLine();
      }									
      input.close();	
      return line;					
   }
   public static void main(String[]arg)throws IOException
   {
      Scanner input = new Scanner(System.in);
      EnigmaMachine test = new EnigmaMachine(1,2,3,'a','a','a', 1, "", 'd', 'a','a');//constructor accepts the three rotors, their initial positions, the reflector, plugboard settings, and the ring settings
   
      char user = ' ';
      String encode;
      
      System.out.println("Enter cyphertext");
      encode = input.nextLine();
      test.makeRandomSettings();
      System.out.println(test.encodeString(encode,test));//
      //
   }
}