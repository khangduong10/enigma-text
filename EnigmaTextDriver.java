import java.io.*;
import java.util.*;
public class EnigmaTextDriver
{
 //pre:  "fileName" is the name of a real file containing lines of text
      //post: returns the number of lines in fileName O(n)
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

 //post: returns true if a user prompt is N, No, NO, n, nO or no O(1)
   public static boolean isNo(String ans)
   {
      return (ans.toLowerCase().equals("no") || ans.toLowerCase().equals("n"));
   }
   
      //post: returns true if a user prompt is y, Y, Yes, yes, YES, yES, or yeS O(1)
   public static boolean isYes(String ans)
   {
      return (ans.toLowerCase().equals("yes") || ans.toLowerCase().equals("y"));
   }
      
   
   public static void main(String[]arg)throws IOException
   {
      Scanner input = new Scanner(System.in);
      EnigmaMachine enigma;//constructor accepts the three rotors, their initial positions, the reflector, plugboard settings, and the ring settings
   
      String user = ""; 
      String encode = "";
      int rotor1=0;
      int rotor2=0;
      int rotor3=0;
      
      char init1=' ';
      char init2= ' ';
      char init3= ' ';
      
      int reflector=0;
      
      String plugboard="";
      
      char ring1=' ';
      char ring2=' ';
      char ring3=' ';
      
      System.out.println("Enigma Machine"+"\n"+"\n"+"Would you like to encrypt? (y) Or Decrypt (n)");
      user = input.nextLine();
      
      while(!isNo(user)&&!isYes(user)){
         System.out.println("Invalid answer"+"\n"+"Encrypt? (y) or Decrypt (n)");
         user = input.nextLine();
      }
      
      if(isNo(user)){
         System.out.println("Decode Message Selected"+"\n"+"Decrypt sample message? (y) or Decrypt other (n)");
         user = input.nextLine();      
         if(isYes(user)){
            System.out.println("Decrypt sample message selected");
            enigma = new EnigmaMachine(1,1,1,'a','a','a', 1, "", 'a', 'a','a');
            enigma.makeRandomSettings();
            System.out.println(enigma.toString());
            System.out.println("Sample Message: "+enigma.encodeString(getSample("enigmaSample.txt"),enigma));
            
            System.out.println("Enter rotor model for left rotor (1-5)");
            rotor1 = Integer.valueOf(input.nextLine());
            System.out.println("Enter rotor model for middle rotor (1-5)");
            rotor2 = Integer.valueOf(input.nextLine());
            System.out.println("Enter rotor model for right rotor (1-5)");
            rotor3 = Integer.valueOf(input.nextLine());
         
            System.out.println("Enter the ring setting for the left rotor (a-z)");
            ring1 = input.nextLine().charAt(0);
            System.out.println("Enter the ring setting for the middle rotor (a-z)");
            ring2 = input.nextLine().charAt(0);
            System.out.println("Enter the ring setting for right rotor (a-z)");
            ring3 = input.nextLine().charAt(0);
            
            System.out.println("Enter the starting position for the left rotor (a-z)");
            init1 = input.nextLine().charAt(0);
            System.out.println("Enter the starting position for the middle rotor (a-z)");
            init2 = input.nextLine().charAt(0);
            System.out.println("Enter the starting position for the right rotor (a-z)");
            init3 = input.nextLine().charAt(0);
            
            System.out.println("Enter plugboard connections (Copy and Paste plugboard settings above; ABCD linkes letters A and B, as well as letters C and D)");
            plugboard = input.nextLine();
            
            
            System.out.println("Use Reflector B? (y) or Reflector C (n)");
            user = input.nextLine();
            if(isYes(user)){
               reflector = 1;
            }
            else{//user is no
               reflector = 2;
            }
            
            enigma = new EnigmaMachine(rotor1,rotor2,rotor3,init1,init2,init3,reflector,plugboard,ring1,ring2,ring3);
            
            System.out.println("Enter cyphertext (copy and paste sample above)");
            encode = input.nextLine();
            
            System.out.println(enigma.toString()+"Your decrypted cyphertext is: "+ enigma.encodeString(encode,enigma).toLowerCase());
         }
         
         else if (isNo(user)){
            System.out.println("Decrypt other message selected"+"\n"+"Enter rotor model for left rotor (1-5)");
            rotor1 = Integer.valueOf(input.nextLine());
            System.out.println("Enter rotor model for middle rotor (1-5)");
            rotor2 = Integer.valueOf(input.nextLine());
            System.out.println("Enter rotor model for right rotor (1-5)");
            rotor3 = Integer.valueOf(input.nextLine());
         
            System.out.println("Enter the ring setting for the left rotor (a-z)");
            ring1 = input.nextLine().charAt(0);
            System.out.println("Enter the ring setting for the middle rotor (a-z)");
            ring2 = input.nextLine().charAt(0);
            System.out.println("Enter the ring setting for right rotor (a-z)");
            ring3 = input.nextLine().charAt(0);
            
            System.out.println("Enter the starting position for the left rotor (a-z)");
            init1 = input.nextLine().charAt(0);
            System.out.println("Enter the starting position for the middle rotor (a-z)");
            init2 = input.nextLine().charAt(0);
            System.out.println("Enter the starting position for the right rotor (a-z)");
            init3 = input.nextLine().charAt(0);
            
            System.out.println("Enter plugboard connections (ABCD linkes letters A and B, as well as letters C and D");
            plugboard = input.nextLine();
            
            
            System.out.println("Use Reflector B? (y) or Reflector C (n)");
            user = input.nextLine();
            if(isYes(user)){
               reflector = 1;
            }
            else{//user is no
               reflector = 2;
            }
            enigma = new EnigmaMachine(rotor1,rotor2,rotor3,init1,init2,init3,reflector,plugboard,ring1,ring2,ring3);
            
            System.out.println("Enter cyphertext: ");
            encode = input.nextLine();
            
            System.out.println(enigma.toString()+"Your decrypted cyphertext is "+ enigma.encodeString(encode,enigma).toLowerCase());
         }
      }
      
      else if(isYes(user)){
         System.out.println("Encode Message Selected"+"\n"+"Encrypt with custom starting parameters? (y) or Encrypt with random starting parameters (n)");
         user = input.nextLine();
         if(isYes(user)){
            System.out.println("Custom Parameters Selected"+"\n"+"Enter rotor model for left rotor (1-5)");
            rotor1 = Integer.valueOf(input.nextLine());
            System.out.println("Enter rotor model for middle rotor (1-5)");
            rotor2 = Integer.valueOf(input.nextLine());
            System.out.println("Enter rotor model for right rotor (1-5)");
            rotor3 = Integer.valueOf(input.nextLine());
         
            System.out.println("Enter the ring setting for the left rotor (a-z)");
            ring1 = input.nextLine().charAt(0);
            System.out.println("Enter the ring setting for the middle rotor (a-z)");
            ring2 = input.nextLine().charAt(0);
            System.out.println("Enter the ring setting for right rotor (a-z)");
            ring3 = input.nextLine().charAt(0);
            
            System.out.println("Enter the starting position for the left rotor (a-z)");
            init1 = input.nextLine().charAt(0);
            System.out.println("Enter the starting position for the middle rotor (a-z)");
            init2 = input.nextLine().charAt(0);
            System.out.println("Enter the starting position for the right rotor (a-z)");
            init3 = input.nextLine().charAt(0);
            
            System.out.println("Enter plugboard connections (ABCD linkes letters A and B, as well as letters C and D");
            plugboard = input.nextLine();
            
            
            System.out.println("Use Reflector B? (y) or Reflector C (n)");
            user = input.nextLine();
            if(isYes(user)){
               reflector = 1;
            }
            else{//user is no
               reflector = 2;
            }
            enigma = new EnigmaMachine(rotor1,rotor2,rotor3,init1,init2,init3,reflector,plugboard,ring1,ring2,ring3);
            
            System.out.print("Enter plaintext: ");
            encode = input.nextLine();
            
            System.out.println(enigma.toString()+"Your encrypted plaintext is "+ enigma.encodeString(encode,enigma));
         }
         else{
            System.out.println("Random Parameters Selected"+"\n"+"Enter plaintext to encrypt");
            encode = input.nextLine();
            enigma = new EnigmaMachine(1,1,1,'a','a','a', 1, "", 'a', 'a','a');
            enigma.makeRandomSettings();
            System.out.println(enigma.toString()+"Your encrypted plaintext is "+ enigma.encodeString(encode,enigma));
         }
      }
   }
}