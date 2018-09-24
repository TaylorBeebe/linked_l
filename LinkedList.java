// Taylor Beebe

import structure5.Assert;
import structure5.DoublyLinkedList;
import structure5.DoublyLinkedNode;
import structure5.DoublyLinkedListIterator;
import java.util.Iterator;

public class LinkedList<E> extends DoublyLinkedList<E>
{
    /**
     * Number of elements within the list.
     */
    protected int count;
    /**
     * Reference to head of the list.
     */
    protected DoublyLinkedNode<E> head;
    /**
     * Reference to tail of the list.
     */
    protected DoublyLinkedNode<E> tail;

    /**
     * Constructs an empty list.
     *
     * @post constructs an empty list
     * 
     */
    public LinkedList()
    {
      head = new DoublyLinkedNode<E>(null, tail, null);
      tail = new DoublyLinkedNode<E>(null, null, head);
      count = 0;
    }
    
    /**
     * Determine the number of elements in the list.
     *
     * @post returns the number of elements in list
     * 
     * @return The number of elements found in the list.
     */
    public int size()
    {
      return count;
    }
    
    /**
     * Determine if the list is empty.
     *
     * @post returns true iff the list has no elements.
     * 
     * @return True iff list has no values.
     */
    public boolean isEmpty()
    {
      return size() == 0;
    }
    
    /**
     * Remove all values from list.
     *
     * @post removes all the elements from the list
     */
    public void clear()
    {
      // Students: modify this code
      head = new DoublyLinkedNode<E>(null, tail, null);
      tail = new DoublyLinkedNode<E>(null, null, head);
      count = 0;
    }
    
    /**
     * A private routine to add an element after a node.
     * @param value the value to be added
     * @param previous the node the come before the one holding value
     * @pre previous is non null
     * @post list contains a node following previous that contains value
     */
    protected void insertAfter(E value, DoublyLinkedNode<E> previous)
    {
      DoublyLinkedNode<E> elem = new DoublyLinkedNode<E>(value, previous.next(), previous);
      elem.previous().setNext(elem);
      elem.next().setPrevious(elem);
      count++;
    }
    
    /**
     * A private routine to remove a node.
     * @param node the node to be removed
     * @pre node is non null
     * @post node node is removed from the list
     * @return the value of the node removed
     */
    protected E remove(DoublyLinkedNode<E> node)
    {
      node.previous().setNext(node.next());
      node.next().setPrevious(node.previous());
      count--;
      return node.value();
    }
    
    /**
     * Add a value to the head of the list.
     *
     * @pre value is not null
     * @post adds element to head of list
     * 
     * @param value The value to be added.
     */
    public void addFirst(E value)
    {
      insertAfter(value, head);
    }

    /**
     * Add a value to the tail of the list.
     *
     * @pre value is not null
     * @post adds new value to tail of list
     * 
     * @param value The value to be added.
     */
    public void addLast(E value)
    {
      insertAfter(value, tail.previous());
    }

    /**
     * Remove a value from the head of the list.
     * Value is returned.
     *
     * @pre list is not empty
     * @post removes first value from list
     * 
     * @return The value removed from the list.
     */
    public E removeFirst()
    {
     return remove(head.next());
    }

    /**
     * Remove a value from the tail of the list.
     *
     * @pre list is not empty
     * @post removes value from tail of list
     * 
     * @return The value removed from the list.
     */
    public E removeLast()
    {
      if (count > 0){
        return remove(tail.previous());
      }
      else{
        return tail.value();
      }
    }

    /**
     * Get a copy of the first value found in the list.
     *
     * @pre list is not empty
     * @post returns first value in list.
     * 
     * @return A reference to first value in list.
     */
    public E getFirst()
    {
      return head.next().value();
    }
    
    /**
     * Get a copy of the last value found in the list.
     *
     * @pre list is not empty
     * @post returns last value in list.
     * 
     * @return A reference to the last value in the list.
     */
    public E getLast()
    {
      return tail.previous().value();
    }
    
    /**
     *interate through the list to a specific index
     *
     * @pre i is greater than 0 and less than the length of the list
     *@post return the node at index i
     * 
     * @param index to get to
     */
    protected DoublyLinkedNode<E> walkList(int i){
      Assert.pre((0 <= i) && (i <= size()-1), "Index not in range.");
           DoublyLinkedNode<E> walk = head.next();
           // search for ith position, or end of list
           while (i > 0) {
             walk = walk.next();
             i--;
           }
           return walk;
    }
    
    /**
     * Insert the value at location.
     *
     * @pre 0 <= i <= size()
     * @post adds the ith entry of the list to value o
     * @param i the index of this new value
     * @param o the the value to be stored
     */
    public void add(int i, E o)
 {
      insertAfter(o, walkList(i).previous());
    }
    /**
     * Remove and return the value at location i.
     *
     * @pre 0 <= i < size()
     * @post removes and returns the object found at that location.
     *
     * @param i the position of the value to be retrieved.
     * @return the value retrieved from location i (returns null if i invalid)
     */
    public E remove(int i)
    {
      return remove(walkList(i));
    }
    
    /**
     * Get the value at location i.
     *
     * @pre 0 <= i < size()
     * @post returns the object found at that location.
     *
     * @param i the position of the value to be retrieved.
     * @return the value retrieved from location i (returns null if i invalid)
     */
    public E get(int i)
    {
      return walkList(i).value();
    }
    
    /**
     * Set the value stored at location i to object o, returning the old value.
     *
     * @pre 0 <= i < size()
     * @post sets the ith entry of the list to value o, returns the old value.
     * @param i the location of the entry to be changed.
     * @param o the new value
     * @return the former value of the ith entry of the list.
     */
    public E set(int i, E o)
    {
      DoublyLinkedNode<E> node = walkList(i);
      // get old value, update new value
      E result = node.value();
      node.setValue(o);
      return result;
    }
    
    /**
     * Determine the first location of a value in the list.
     *
     * @pre value is not null
     * @post returns the (0-origin) index of the value,
     *   or -1 if the value is not found
     * 
     * @param value The value sought.
     * @return the index (0 is the first element) of the value, or -1
     */
    public int indexOf(E value)
    {
      if( value == null){
        return -1;
      }
      int i = 0;
      DoublyLinkedNode<E> node = head.next();
      // search for value or end of list, counting along the way
      while (node != tail && !node.value().equals(value))
      {
        node = node.next();
        i++;
      }
      // finger points to value, i is index
      if (node == tail)
      {   // value not found, return indicator
        return -1;
      } else {
        // value found, return index
        return i;
      }
    }
    
    /**
     * Determine the last location of a value in the list.
     *
     * @pre value is not null
     * @post returns the (0-origin) index of the value,
     *   or -1 if the value is not found
     * 
     * @param value the value sought.
     * @return the index (0 is the first element) of the value, or -1
     */
    public int lastIndexOf(E value)
    {
      if( value == null){
        return -1;
      }
      int i = size()-1;
      DoublyLinkedNode<E> node = tail.previous();
 // search for the last matching value, result is desired index
      while (node != head && !node.value().equals(value))
      {
        node = node.previous();
        i--;
      }
      if (node == head)
      {   // value not found, return indicator
        return -1;
      } else {
        // value found, return index
        return i;
      }
    }
    
    /**
     * Check to see if a value is within the list.
     *
     * @pre value not null
     * @post returns true iff value is in the list
     * 
     * @param value A value to be found in the list.
     * @return True if value is in list.
     */
    public boolean contains(E value)
    {
      if(indexOf(value) != -1){
        return true;
      }
      return false;
    }
    
    /**
     * Remove a value from the list.  At most one value is removed.
     * Any duplicates remain.  Because comparison is done with "equals,"
     * the actual value removed is returned for inspection.
     *
     * @pre value is not null.  List can be empty.
     * @post first element matching value is removed from list
     * 
     * @param value The value to be removed.
     * @return The value actually removed.
     */
    public E remove(E value)
    {
        int index = indexOf(value);
        if(index != -1){
          return remove(index);
        }
        return null;
    }
    
    /**
     * Construct an iterator to traverse the list.
     *
     * @post returns iterator that allows the traversal of list.
     * 
     * @return An iterator that traverses the list from head to tail.
     */
    public Iterator<E> iterator()
    {
      // Students: This code should not be modified
      return new DoublyLinkedListIterator<E>(head,tail);
    }

    /**
     * Construct a string representation of the list.
     *
     * @post returns a string representing list
     * 
     * @return A string representing the elements of the list.
     */
    public String toString()
    {
      StringBuffer s = new StringBuffer();
      s.append("<LinkedList:");
      Iterator<E> li = iterator();
      while (li.hasNext())
      {
        s.append(" "+li.next());
      }
      s.append(">");
      return s.toString();
    }
}