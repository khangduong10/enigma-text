import java.util.*;
import java.io.*;

class Reflector{
   int settings;
   
   public static final int [] in = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};	                     
   public static final int [] outB ={24,17,20,7,16,18,11,3,15,23,13,6,14,10,12,8,4,1,5,25,2,22,21,9,0,19};
   
   public static final int [] outC ={5,21,15,9,8,0,14,24,4,3,17,25,23,22,6,2,19,10,20,16,18,1,13,12,7,11};
   TreeMap <Integer, Integer> reflector = new TreeMap();
   
   
   final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
   
   public Reflector(int s){
      settings = s;
      if(s ==1){
         for(int x = 0; x<26; x++){
            reflector.put(in[x],outB[x]);
         }	
      }
      else{
         for(int x = 0; x<26; x++){
            reflector.put(in[x],outC[x]);
         }	
      }
   
   }
   
   public char reflect(char output, int Iorient){
   
      int charVal =Character.getNumericValue(Character.toUpperCase(output))-10-Iorient;   
    
      if(charVal>=26||charVal<0){
         if(charVal>=26){//if it is too big
            while(charVal>=26){
               charVal = charVal-26;//subtract 26 until it is in the alphabet
            }
         }
         else if(charVal<0){
            while(charVal<0){
               charVal = charVal+26;
            }
         }
      }
         
      int reflectedVal = this.reflector.get(charVal)+Iorient;    // also add a wrap around for charVal going into reflector
   
      if(charVal<26&&charVal>=0){//if output is in the alphabet
         if(reflectedVal<26&&reflectedVal>=0)
            output = alphabet[reflectedVal];//take that index from output. That letter gets reflected  
         else if (reflectedVal>=26)
            output = alphabet[reflectedVal-26];
         else
            output = alphabet[reflectedVal+26];
      }
         
      else if (charVal>=26){
         reflectedVal = reflector .get(charVal-26)+Iorient;
         if(reflectedVal<26&&reflectedVal>=0)
            output = alphabet[reflector .get(charVal-26)+Iorient];//take that index from output. That letter gets reflected
         else if (reflectedVal>=26)
            output = alphabet[reflector .get(charVal-26)+Iorient-26];//subtract 26 (wrap around the alphabet). This letter goes into the next rotor
         else
            output = alphabet[reflector .get(charVal-26)+Iorient+26];
      }              
         
      else if (charVal<0){
         reflectedVal = reflector .get(charVal+26)+Iorient;
         if((reflectedVal)<26&&(reflectedVal)>=0)
            output = alphabet[reflector .get(charVal+26)+Iorient];//take that index from output. That letter gets reflected
         else if (reflectedVal>=26)
            output = alphabet[reflector .get(charVal+26)+Iorient-26];//subtract 26 (wrap around the alphabet). This letter goes into the next rotor
         else
            output = alphabet[reflector.get(charVal+26)+Iorient+26];
      } 
      return output; 
   }
   
   public String getReflectorString(){
      if(settings ==1){
         return "reflector B";
      }
      else{
         return "reflector C";
      }
   }
   
   public void changeReflector(int s){
   
      if(settings == s){
         return;
      }
      
      settings = s;
      if(s ==1){
         for(int x = 0; x<26; x++){
            reflector.put(in[x],outB[x]);
         }	
      }
      else{
         for(int x = 0; x<26; x++){
            reflector.put(in[x],outC[x]);
         }	
      }
   }  
}