//d oberle - 2015 - a double linked list object
public class dLinkedList<anyType> implements ListInterface<anyType>
{
   private dListNode<anyType> head;			//refers to the first element
   private dListNode<anyType> tail;
   private int size;
   
   public dLinkedList()						//constructor
   {
      head = null;
      tail = null;
      int size = 0;
   }

  //WRITE THIS METHOD***********************************************DONE 
  //post: adds x to the front of the list O(1)
   public void addFirst(anyType x)				
   {
      if (head == null){
         dListNode insert = new dListNode(x,null,null);
         head = insert;
         tail = insert;
         size = 1;
      }
      else{
         head = new dListNode(x,null,head);
         head.getNext().setPrev(head);
         size++;
      }
   
   }
  //****************************************************************

  //WRITE THIS METHOD***********************************************DONE
  //post:  adds x to the end of the list O(n)
   public void addLast(anyType x)
   {
      if (head==null){//if the list is empty
         dListNode insert = new dListNode(x,null,null);
         head =insert;
         tail = insert;
         size = 1;
      }
      
      else{
         size++;
         dListNode insert = new dListNode(x,tail,null);
         tail.setNext(insert);
         tail = insert;
      }
   }
  //****************************************************************

//pre:  the head is not null
//post: returns the head's value O(1)
   public anyType getFirst()
   {
      if (head==null)							//if list is empty
         return null;
      return head.getValue();
   }

//pre:  the lastNode is not null
//post: returns the lastNode's value O(n)
   public anyType getLast()
   {   
      dListNode<anyType> current = head;
      while(current.getNext()!= null)		//make current go to the last element
         current = current.getNext();
      return current.getValue();
   }

//WRITE THIS METHOD***********************************************DONE
//pre:  the head is not null
//post: removes the first element from the list O(1) and returns its value
   public anyType removeFirst()
   {
      if(head == null){
         return null;
      }
      
      if(size == 1){//if there is only one value in the list
         anyType temp = head.getValue();
         head = null;
         tail = null;
         size = 0;
         return temp;
      }
      else{
         anyType temp = head.getValue();
         head = head.getNext();
         head.setPrev(null);
         size--;
         return temp;
      }
   }
//****************************************************************

//WRITE THIS METHOD***********************************************DONE
//pre:  the head is not null
//post: removes the last element from the list O(n) and returns its value
   public anyType removeLast()
   {
      if (head ==null){
         return null;
      }
      if(size == 1){//if there is only one node in the list
         anyType temp = head.getValue();
         head = null;
         tail = null;
         size = 0;
         return temp;
      }
      else{
         anyType temp = tail.getValue();
         tail.getPrev().setNext(null);
         tail = tail.getPrev();
         size--;
         return temp; 
      }
   }
//****************************************************************

//WRITE THIS METHOD***********************************************DONE
//post: returns the number of elements
   public int size()
   {
      return size;							
   }
//****************************************************************


//WRITE THIS METHOD***********************************************DONE
//pre: index >=0 and index < size()
//post: returns the object at a specific index (first element is index 0)
   public anyType get(int index)		
   {
      if (index == size-1){
         return tail.getValue();
      }
      else if (index == 0){
         return head.getValue();
      }
      if(size/2>index){//if the index is less than the half mark, then start from the front
         dListNode<anyType> current = head;
         int trace = 0;
         while(trace!=index){
            current = current.getNext();
            trace++;
         }
         return current.getValue();
      }
      else{//otherwise, start from the back
         dListNode<anyType> current = tail;
         int trace = size-1;
         while(trace!=index){
            current = current.getPrev();
            trace--;
         }
         return current.getValue();
      }						
   }	
//****************************************************************	

//WRITE THIS METHOD***********************************************DONE
//pre:  index >=0 and index < size()
//post: changes the element at a specific index to x, returning the element that was originally there
   public anyType set(int index, anyType x)
   {
      if (index == 0){//if the index is the first one
         anyType retVal = head.getValue();
         head.setValue(x);
         return retVal;
      }
      else if (index == size-1){//if the index is the last one
         anyType retVal = tail.getValue();
         tail.setValue(x);
         return retVal;
      }
      if(size/2>index){//if the index is less than the half mark, then start from the front
         dListNode<anyType> current = head;
         int trace = 0;
         while(trace!=index){
            current = current.getNext();
            trace++;
         }
         anyType retVal = current.getValue();
         current.setValue(x);
         return retVal;
      }
      else{//otherwise, start from the back
         dListNode<anyType> current = tail;
         int trace = size-1;
         while(trace!=index){
            current = current.getPrev();
            trace--;
         }
         anyType retVal = current.getValue();
         current.setValue(x);
         return retVal;
      }						
   }	
//****************************************************************

//post: adds element x to the end of the list, returns true if successful
   public boolean add(anyType x)
   {
      addLast(x);
      return true;			
   }	

//WRITE THIS METHOD***********************************************probably not finished but it's done (add a thing where if it is the last index, call the get last method)
//pre:  index >=0 and index < size()
//post: adds element x at index i, returns true if successful
   public boolean add(int index, anyType x)
   {
      if (index == size-1){//if the index is the last one
         if (head==null){//if the list is empty
            dListNode insert = new dListNode(x,null,null);
            head =insert;
            tail = insert;
            size = 1;
         }
         else{
            size++;
            dListNode insert = new dListNode(x,tail.getPrev(),tail);
            tail.getPrev().setNext(insert);
            tail.setPrev(insert);
         }
         return true;
      }
      else if (index == 0){//if the index is the first one
         if (head == null){//if the list if empty
            dListNode insert = new dListNode(x,null,null);
            head = insert;
            tail = insert;
            size = 1;
         }
         else{
            head = new dListNode(x,null,head);
            head.getNext().setPrev(head);
            size++;
         }
         return true;
      }
      
      if((size/2)>index){//if the index is less than the half mark, then start from the front
         dListNode<anyType> current = head;
         int trace = 0;
         while(trace!=index){
            current = current.getNext();
            trace++;
         }
         dListNode insert = new dListNode(x,current.getPrev(),current);
         current.getPrev().setNext(insert);
         insert.setPrev(insert);
         size++;
         return true;
      }
      else{//otherwise, start from the back
         dListNode<anyType> current = tail;
         int trace = size-1;
         while(trace!=index){
            current = current.getPrev();
            trace--;
         }
         dListNode insert = new dListNode(x,current.getPrev(),current);
         current.getPrev().setNext(insert);
         insert.setPrev(insert);
         size++;
         return true;    
      }							
   }	
//****************************************************************

//WRITE THIS METHOD***********************************************
//pre: index >=0 and index < size()
//post: removes and returns the object at a specific index (first element is index 0)

   public anyType remove(int index)		
   {
      if (index == size-1){//if the node is the last one
         anyType retVal = tail.getValue();
         tail = tail.getPrev();
         tail.setNext(null);
         size--;
         return retVal;
      }
      else if (index == 0){//if the node is the first one
         anyType retVal = head.getValue();
         head = head.getNext();
         head.setPrev(null);
         size--;
         return retVal;
      }
   
      if(size/2>index){//if the index is less than the half mark, then start from the front
         dListNode<anyType> current = head;
         int trace = 0;
         while(trace!=index){
            current = current.getNext();
            trace++;
         }
         anyType retVal = current.getValue();
         current.getNext().setPrev(current.getPrev());//sets the next value's previous to current's previous
         current.getPrev().setNext(current.getNext());//sets the previous's next to current's next
         size--;
         return retVal;
      }
      else{//otherwise, start from the back
         dListNode<anyType> current = tail;
         int trace = size-1;
         while(trace!=index){
            current = current.getPrev();
            trace--;
         }
         
         anyType retVal = current.getValue();
         current.getNext().setPrev(current.getPrev());//sets the next value's previous to current's previous
         current.getPrev().setNext(current.getNext());//sets the previous's next to current's next
         size--;
         return retVal;
         
      }		
   }	
//****************************************************************	


//pre:  the head is not null
//post: shows all elements of the list O(n)
   public void showList()
   {
      if (head==null)						//if list is empty
         System.out.println("List is empty");
      else
      {
         dListNode<anyType> current =  head;
         while(current != null)
         {
            System.out.print(current.getValue() + " ");
            current = current.getNext();
         }	
         System.out.println();
      }
   
   }

  //post: returns all elements of the list as a String 
//in the form [a0, a1, a2, . . . , an-1],  O(n)
   public String toString()
   {
      String ans = "[";									//start with left bookend						
      dListNode<anyType> current =  head;
      while(current != null)
      {
         ans += current.getValue().toString();
         current = current.getNext();
         if (current != null)							//don't add comma after the last element
            ans += ",";
      }
      ans += "]";											//end with right bookend
      return ans;
   }


   public boolean isEmpty()
   {
      if (head == null)
         return true;
      return false;
   }

}