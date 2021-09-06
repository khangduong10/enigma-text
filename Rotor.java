class Rotor{
   char [] rotor = {};
   char initial =' ';//INITIAL DOES NOTHING WHY IS IT HERE (maybe fix this later)
   int orient = 0;
   int notch = 0;
   boolean revo = false;
   final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
   private int charVal;
   private boolean lastRotor;
   private int ringSetting;//ringSetting is a caesar shift for each rotor
   private int rotorNumber;
   
   public Rotor(char [] settings, char initPos, char n, boolean last, char ringstellung,int r){//basic rotors will have the subsitution cypher and the initial settings
      rotor = settings;
      rotorNumber = r;
      ringSetting = Character.getNumericValue(Character.toUpperCase(ringstellung))-10;
      lastRotor = last;
      initial = initPos;
      notch = Character.getNumericValue(Character.toUpperCase(n))-10;//notch is the int that states where the next rotor is kicked
      orient = Character.getNumericValue(Character.toUpperCase(initPos))-10;//orient states the beginning of the rotor; a becomes 0, b becomes 1, c becomes 2, etc etc 
   }
   ///////////////////////////////
   //pre: letter is a valid letter in the Enigma machine
   //post encodes letter according to the single rotor (right to left)
   public char singleEncodeRight(char letter, int prevOrient){//prevOrient is the orientation of the previous rotor
      charVal = Character.getNumericValue(Character.toUpperCase(letter))-10+orient;
      
      int locate =  charVal-prevOrient-ringSetting;//locate is what goes into the rotor 
      char next = ' ';
      
      if(locate<26&&locate>=0){//if locate is in the alphabet
         next = rotor[locate];//take that index from locate. That letter is now goes into the next rotor
      }
      else{
         if(locate>=26){//if it is too big
            while(locate>=26){
               locate = locate-26;//subtract 26 until it is in the alphabet
            }
         }
         else if(locate<0){
            while(locate<0){
               locate = locate+26;
            }
         }
         next = rotor[locate];
      }
      return next;
   }
   
    //pre: letter is a valid letter in the Enigma machine
   //post encodes letter according to the single rotor (left to right)
   public char singleEncodeLeft(char letter, int prevOrient){
      charVal = Character.getNumericValue(Character.toUpperCase(letter))-10+orient-ringSetting-prevOrient;
      int locate = 0;//locate is what goes into the rotor
      char trueChar= ' ';   
      char next = ' ';
      
      
      if(charVal<26&&charVal>=0)//if charVal is in the alphabet
         trueChar = alphabet[charVal];//take that index from charVal. That letter is now goes into the next rotor 
      else{
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
         trueChar = alphabet[charVal];
      }
   
      for(int x=0;x<rotor.length;x++){//finds the index of letter in rotor
         if(trueChar==rotor[x]){
            locate = x;//+orient -prevOrient
            break;
         }
      }
      
      if(lastRotor){//if it is the last rotor, take into account shifting to get to the output
         locate = locate-orient+ringSetting;            
         if(locate<26&&locate>=0)//if locate is in the alphabet
            next = alphabet[locate];//take that index from locate. That letter is now goes into the next rotor
            
         else{
            if(locate>=26){//if it is too big
               while(locate>=26){
                  locate = locate-26;//subtract 26 until it is in the alphabet
               }
            }
            else if(locate<0){
               while(locate<0){
                  locate = locate+26;
               }
            }
            next = alphabet[locate];
         }
      }
      else{//if it is not the last rotor
         if(locate<26&&locate>=0)//if locate is in the alphabet
            next = alphabet[locate];//take that index from locate. That letter is now goes into the next rotor
         
         else{
            if(locate>=26){//if it is too big
               while(locate>=26){
                  locate = locate-26;//subtract 26 until it is in the alphabet
               }
            }
            else if(locate<0){
               while(locate<0){
                  locate = locate+26;
               }
            }
            next = alphabet[locate];
         }
      }       
      
      return next;
   }
  
  //pre: rotor exists
  //post:the orientation of the rotor is kicked forward by one notch; edits whether the next rotor should be kicked forward a notch
   public void turn(){
      if(orient==notch){
         revo = true;
      }
      
      if(orient<25){//25 is the biggest index in a 26 alphabet array
         orient++;
      }
      else{//the rotor goes back to the start
         orient = 0; 
      }
   }
   
   //pre: there is a rotor
   //post: returns whether to kick the next rotor down a notch
   public boolean fullRevolution(){
      if (revo ==true){
         revo = false;
         return true;
      }
      return false;
   }
   
   public void setNotch(char n){
      notch = Character.getNumericValue(Character.toUpperCase(n))-10;
   }
   
   public char getNotch(){
      return alphabet[notch];
   }
   
   //returns the orientation of the rotor (where does it start?)
   public int getOrientation(){
      return orient;
   }
   //sets the orientation of the rotor
   public void setRotor(char [] change){
      rotor = change;
   }
   //changes the initial settings
   public void setInit(char change){
      initial = change;
      orient = Character.getNumericValue(Character.toUpperCase(change))-10;
   }
   //returns the rotor setttings
   public char [] getRotor(){
      return rotor;
   }
   public boolean isLastRotor(){
      if (lastRotor)
         return true;
      return false;
   }
   public void setLastRotor(boolean s){
      lastRotor = s;
   }
   public void setRingSettings(char a){
      ringSetting = Character.getNumericValue(Character.toUpperCase(a))-10;
   }
   public int getRingSettings(){
      return ringSetting;
   }
   public int getRotorNumber(){
      return rotorNumber;
   }
   public void setRotorNumber(int i){
      rotorNumber = i;
   }
}
