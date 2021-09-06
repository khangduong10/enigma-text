//things an enigma machine should be able to do: encode, decode, changing settings
//https://en.wikipedia.org/wiki/Enigma_rotor_details
import java.util.Map;
import java.util.*;
public class EnigmaMachine{
 //rotor settings
   final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
   final char[] rotorI =   "EKMFLGDQVZNTOWYHXUSPAIBRCJ".toCharArray();//Q
   final char[] rotorII =  "AJDKSIRUXBLHWTMCQGZNPYFVOE".toCharArray();//E
   final char[] rotorIII = "BDFHJLCPRTXVZNYEIWGAKMUSQO".toCharArray();//V
   final char[] rotorIV =  "ESOVPZJAYQUIRHXLNFTGKDCMWB".toCharArray();//J
   final char[] rotorV =   "VZBRGITYUPSDNHLXAWMJQOFECK".toCharArray();//Z
   
 
   
   Rotor I = new Rotor(rotorI, 'a', 'q', false, 'a',1);//initial settings for rotor
   Rotor II = new Rotor(rotorII, 'a', 'e', false, 'a',1);//the second char, notch is the same for any given rotor ex. rotor I always has a notch at Q
   Rotor III = new Rotor(rotorII, 'a', 'v',true, 'a',1);
   Reflector ref = new Reflector(1);
   Plugboard plug;
   

   public EnigmaMachine(int one, int two, int three, char init1, char init2, char init3, int r, String p, char ring1, char ring2, char ring3){//an enigma machine would have which rotors to use, and their initial settings
      if(one ==1){//use rotor 1 for the rightmost rotor
         I.setRotor(rotorI);
         I.setNotch('Q');
         I.setRotorNumber(1);
      }
      else if(one ==2){
         I.setRotor(rotorII);
         I.setNotch('E');
         I.setRotorNumber(2);
      }
      else if(one ==3){
         I.setRotor(rotorIII);
         I.setNotch('V');
         I.setRotorNumber(3);
      }
      else if(one ==4){
         I.setRotor(rotorIV);
         I.setNotch('J');
         I.setRotorNumber(4);
      }
      else if(one ==5){
         I.setRotor(rotorV);
         I.setNotch('Z');  
         I.setRotorNumber(5);
      }
      I.setInit(init1);
      I.setLastRotor(false);
   
      if (two == 1){
         II.setRotor(rotorI);
         II.setRotorNumber(1);
         II.setNotch('Q');
      }
      else if (two == 2){//use rotor 2 for the middle rotor
         II.setRotor(rotorII);
         II.setNotch('E');
         II.setRotorNumber(2);
      }
      else if (two == 3){
         II.setRotor(rotorIII);
         II.setNotch('V');
         II.setRotorNumber(3);
      }
      else if (two == 4){
         II.setRotor(rotorIV);
         II.setNotch('J');
         II.setRotorNumber(4);
      }
      else if (two == 5){
         II.setRotor(rotorV);
         II.setNotch('Z');
         II.setRotorNumber(5);
      }
      II.setInit(init2);//sets the initial position of the rotor
      II.setLastRotor(false);
      
      
      if (three == 1){
         III.setRotor(rotorI);
         III.setNotch('Q');
         III.setRotorNumber(1);
      }
      else if (three == 2){
         III.setRotor(rotorII);
         III.setNotch('E');
         III.setRotorNumber(2);
      }
      else if (three == 3){//set the leftmost rotor as rotor 3
         III.setRotor(rotorIII);
         III.setNotch('V');
         III.setRotorNumber(3);
      }
      else if (three == 4){
         III.setRotor(rotorIV);
         III.setNotch('J');
         III.setRotorNumber(4);
      }
      else if (three == 5){
         III.setRotor(rotorV);
         III.setNotch('Z');
         III.setRotorNumber(5);
      }
      III.setInit(init3);
      III.setLastRotor(true);
     
      ref.changeReflector(r);     
      plug = new Plugboard(p);
      
      I.setRingSettings(ring1);
      II.setRingSettings(ring2);
      III.setRingSettings(ring3);
   }
      
   public String encodeString(String words, EnigmaMachine m){
    //make an array of words from the sentence
      String output = "";
      String finished = "";
      
      if (words.indexOf(" ")>-1){//if the words have a space between them (there are multiple words)
         String[] encode = words.replaceAll("[^a-zA-Z ]", "").split("\\s+");
         
         for (String word : encode){//for each word in the array
            word.trim();//trims each word for spaces
            
            char[]wordToChar=word.toCharArray();
            for (int x = 0; x<wordToChar.length; x++){//for each letter in word
               output+=m.encodeChar(wordToChar[x]);
            }
          
            finished+=output;
            output = "";
         }
      }
      else{//if it is not composed of multiple words
         char[]wordToChar=words.toCharArray();
         for (int x = 0; x<wordToChar.length; x++){//for each letter in word
            output+=m.encodeChar(wordToChar[x]);
         }
         for(int x = 5;x<output.length();x=x+6){//for loop adds a space every five letters to make it harder to decrypt
            if(x>output.length()-1){
               break;
            }
            else{
               output = output.substring(0,x)+" "+output.substring(x);
            }
         }
      
         return output;
      }
      for(int x = 5;x<finished.length();x=x+6){//for loop adds a space every five letters to make it harder to decrypt
         if(x>finished.length()-1){
            break;
         }
         else{
            finished = finished.substring(0,x)+" "+finished.substring(x);
         }
      }
      return finished; //return  array
   
   }

   public char encodeChar(char letter){
   //rotors turning
      III.turn();
      //implement other rotors turning
      if(III.fullRevolution()){
         II.turn();
      }
      if(II.fullRevolution()){
         I.turn();
      }
      
      letter = plug.swap(letter);
      
      char output =III.singleEncodeRight(letter, 0);//goes through rotor III
      output = II.singleEncodeRight(output, III.getOrientation()-III.getRingSettings());//output from that goes into rotor II
      output = I.singleEncodeRight(output, II.getOrientation()-II.getRingSettings());//output from that goes into rotor I
     
      output = ref.reflect(output, I.getOrientation()-I.getRingSettings());//gets reflected by the reflector
      
      output =I.singleEncodeLeft(output,I.getOrientation()-I.getRingSettings());//goes through rotor I, but this time, backwards
      output = II.singleEncodeLeft(output, I.getOrientation()-I.getRingSettings());//output from that goes into rotor II
      output = III.singleEncodeLeft(output, II.getOrientation()-II.getRingSettings());//output from that goes into rotor III
   
      output = plug.swap(output);
      return output;
   }

   //pre: there are three rotors, user inputs 3 ints between 1 and 5
   //post: rotors are changed according to inputs
   public void changeRotors(int one, int two, int three){
      if(one ==1){//use rotor 1 for the rightmost rotor
         I.setRotor(rotorI);
         I.setNotch('Q');
         I.setRotorNumber(1);
      }
      else if(one ==2){
         I.setRotor(rotorII);
         I.setNotch('E');
         I.setRotorNumber(2);
      }
      else if(one ==3){
         I.setRotor(rotorIII);
         I.setNotch('V');
         I.setRotorNumber(3);
      }
      else if(one ==4){
         I.setRotor(rotorIV);
         I.setNotch('J');
         I.setRotorNumber(4);
      }
      else if(one ==5){
         I.setRotor(rotorV);
         I.setNotch('Z');  
         I.setRotorNumber(5);
      }
   
      if (two == 1){
         II.setRotor(rotorI);
         II.setRotorNumber(1);
         II.setNotch('Q');
      }
      else if (two == 2){//use rotor 2 for the middle rotor
         II.setRotor(rotorII);
         II.setNotch('E');
         II.setRotorNumber(2);
      }
      else if (two == 3){
         II.setRotor(rotorIII);
         II.setNotch('V');
         II.setRotorNumber(3);
      }
      else if (two == 4){
         II.setRotor(rotorIV);
         II.setNotch('J');
         II.setRotorNumber(4);
      }
      else if (two == 5){
         II.setRotor(rotorV);
         II.setNotch('Z');
         II.setRotorNumber(5);
      }
      
      if (three == 1){
         III.setRotor(rotorI);
         III.setNotch('Q');
         III.setRotorNumber(1);
      }
      else if (three == 2){
         III.setRotor(rotorII);
         III.setNotch('E');
         III.setRotorNumber(2);
      }
      else if (three == 3){//set the leftmost rotor as rotor 3
         III.setRotor(rotorIII);
         III.setNotch('V');
         III.setRotorNumber(3);
      }
      else if (three == 4){
         III.setRotor(rotorIV);
         III.setNotch('J');
         III.setRotorNumber(4);
      }
      else if (three == 5){
         III.setRotor(rotorV);
         III.setNotch('Z');
         III.setRotorNumber(5);
      }
   }
   
   public void changeInitial(char init1, char init2, char init3){
      I.setInit(init1);
      II.setInit(init2);
      III.setInit(init3);
   }
   public String toString(){
      return "Rotor Order: " +I.getRotorNumber()+II.getRotorNumber()+III.getRotorNumber()+"\nRing Settings: "+alphabet[I.getRingSettings()]+alphabet[II.getRingSettings()]+alphabet[III.getRingSettings()]+"\nStarting Positions: "+alphabet[I.getOrientation()]+alphabet[II.getOrientation()]+alphabet[III.getOrientation()]+"\nPlugboard Settings: "+plug.getPlugboard()+"\n"+ref.getReflectorString()+"\n";
   }
  
   //makes completely random starting settings   
   public void makeRandomSettings(){
      changeRotors((int)(Math.random()*5+1),(int)(Math.random()*5+1),(int)(Math.random()*5+1));//randomizing rotors
      changeInitial(alphabet[(int)(Math.random()*26)],alphabet[(int)(Math.random()*26)],alphabet[(int)(Math.random()*26)]);//randomizing initial
      ref.changeReflector((int)(Math.random()*2+1));//randomizing reflector
      I.setRingSettings(alphabet[(int)(Math.random()*26)]);//randomizing ring settings
      II.setRingSettings(alphabet[(int)(Math.random()*26)]);
      III.setRingSettings(alphabet[(int)(Math.random()*26)]);
      
      String randomPlug="";//randomizing plugboard
      dLinkedList<Character> alphabetList = new dLinkedList<Character>();
      for(int x = 0;x<26;x++){
         alphabetList.add(alphabet[x]);
      }
      
      for(int x = 0;x<((int)(Math.random()*14))*2;x++){//random amount of plug connections (0-26 evens)
         if(alphabetList.size()==0){
            return;
         }
         if(alphabetList.size()==1){
            randomPlug+=alphabetList.remove(0);
         }
         randomPlug+=alphabetList.remove((int)(Math.random()*alphabetList.size()));
      }
      if(randomPlug.length()%2==0){
         plug.changePlugboard(randomPlug);
      }
      else{
         plug.changePlugboard(randomPlug.substring(0,randomPlug.length()-1));
      }
   }
   
}