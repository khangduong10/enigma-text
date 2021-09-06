import java.util.*;
import java.io.*;

class Plugboard{
   String settings;
   TreeMap <Character, Character> swap = new TreeMap();
   char [] toMap;
//assuming that string s has an even length, put its contents into a treeMap
   public Plugboard(String s){
      settings = s.trim();
      toMap = settings.toUpperCase().toCharArray();
      
      for (int x = 0; x<toMap.length;x+=2){
         swap.put(toMap[x],toMap[x+1]);
         swap.put(toMap[x+1],toMap[x]);
      }
   }

   public char swap(char output){
      output = Character.toUpperCase(output);
      if (swap.get(output) == null){
         return output;
      }
      else{
         return swap.get(output);
      }
   }
   
   public void addPlug(char a, char b){//consider adding a setPlug and removePlug
      swap.put(a,b);
      swap.put(b,a);
   }
   public String getPlugboard(){
      return settings.toUpperCase();
   }
   
   public void changePlugboard(String s){
      settings = s.trim();
      toMap = settings.toUpperCase().toCharArray();
      
      for (int x = 0; x<toMap.length;x+=2){
         swap.put(toMap[x],toMap[x+1]);
         swap.put(toMap[x+1],toMap[x]);
      }
   }
}