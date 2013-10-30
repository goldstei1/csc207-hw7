package edu.grinnell.csc207.goldstei1.LinkedLists;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Doubly linked lists.
 */
public class DoublyLinkedList<T> implements ListOf<T> {

	// FIELDS
	Node<T> front;
	Node<T> back;

	// CONSTRUCTORS
	/**
	 * Create a new linked list.
	 */
	public DoublyLinkedList() {
	} // DoublyLinkedList

	// ITERABLE METHODS
	@Override
	public Iterator<T> iterator() {
		return new DoublyLinkedListIterator<T>(this);
	} // iterator()

	// LISTOF METHODS
	
	    /**
     * Insert an element at the location of the Cursor<T> (between two
     * elements).
     *
     * @pre
     *   lit must be associated with the list and in the list.
     *
     * @throws Exception
     *   If the precondition is not met.
     * @throws Exception
     *   If there is no memory to expand the list.
     *
     * @post
     *   The previous element to the iterator remains the same
     *   str is immediately after the iterator
     *   The element that previously followed the iterator follows str
     *   And writing postconditions is a PITN
     */
	public void insert(T val, Cursor<T> c) throws Exception {
		DoublyLinkedListCursor<T> dllc = (DoublyLinkedListCursor<T>) c;
		Node<T> n = new Node<T>(val);

		if (this.front == null) {
			this.front = n;
			this.back = n;
		} else if (!this.hasNext(dllc)) {
			n.prev = dllc.pos;
			dllc.pos.next = n;
		} else {
			n.prev = dllc.pos;
			n.next = dllc.pos.next;
			dllc.pos.next.prev = n;
			dllc.pos.next = n;
		} // else
	} // insert(T, Cursor<T>)
	
	 /**
     * Add an element to the end of the list.  (Creates a one-element
     * list if the list is empty.)
     *
     * @throws Exception
     *   If there is no memory to expand the list.
     */
	public void append(T val) throws Exception {
		if (this.front == null) {
			this.front = new Node<T>(val);
			this.back = this.front;
		} else {
			Node<T> n = new Node<T>(val);
			this.back.next = n;
			n.prev = this.back;
			this.back = n;
		} // else
	} // append(T)
	
	    /**
     * Add an element to the front of the list.  (Creates a one-element
     * list if the list is empty.)
     *
     * @throws Exception
     *   If there is no memory to expand the list.
     */
	public void prepend(T val) throws Exception {
		if (this.front == null) {
			this.front = new Node<T>(val);
			this.back = this.front;
		} else {
			Node<T> n = new Node<T>(val);
			this.front.prev = n;
			n.next = this.front;
			this.front = n;
		} // else
	} // prepend(T)
	
	
    // Removing Elements
    /**
     * Delete the element immediately after the iterator.
     *
     * @post
     *    The remaining elements retain their order.
     * @post
     *    The iterator is at the position
     *    The successor of the element immediately before the iterator
     *      is the successor of the now-deleted element.
     */
	public void delete(Cursor<T> c) throws Exception {
		DoublyLinkedListCursor<T> dllc = (DoublyLinkedListCursor<T>) c;
		if (!this.hasPrev(dllc) && !this.hasNext(dllc)) {
			this.front = null;
			this.back = null;
		} else if (!this.hasPrev(dllc) && this.hasNext(dllc)) {
			this.front = dllc.pos.next;
			this.front.prev = null;
			dllc.pos = this.front;
		} else if (!this.hasNext(dllc)) {
			this.back = dllc.pos.prev;
			this.back.next = null;
			dllc.pos = this.back;
		} else {
			dllc.pos.prev.next = dllc.pos.next;
			dllc.pos.next.prev = dllc.pos.prev;
			dllc.pos = dllc.pos.prev;
		} // else

	} // delete(Cursor<T>)

	 /**
     * Get an iterator at the front of the list.
     *
     * @throws Exception
     *   If the list is empty.
     */
	public Cursor<T> front() throws Exception {
		if (this.front == null) {
			throw new NoSuchElementException("empty list");
		}
		Cursor<T> c = new DoublyLinkedListCursor<T>(this.front);
		return c;
	} // front()

	  /**
     * Advance to the next position.
     *
     * @pre
     *   The list has a next element.
     * @throws Exception
     *   If there is no next element.
     */
	public void advance(Cursor<T> c) throws Exception {
		DoublyLinkedListCursor<T> dllc = (DoublyLinkedListCursor<T>) c;
		if (this.hasNext(dllc)) {
			dllc.pos = dllc.pos.next;
		} else {
			throw new NoSuchElementException("at end of list");
		}

	} // advance(Cursor<T>)

	    /**
     * Back up to the previous position.
     *
     * @pre
     *   The list has a next element.
     * @throws Exception
     *   If there is no next element.
     */
	public void retreat(Cursor<T> c) throws Exception {
		DoublyLinkedListCursor<T> dllc = (DoublyLinkedListCursor<T>) c;
		if (this.hasPrev(dllc)) {
			dllc.pos = dllc.pos.prev;
		} else {
			throw new NoSuchElementException("at beginning of list");
		}
	} // retreat(Cursor<T>)
	
	   /**
     * Get the element under the Cursor<T>.
     *
     * @pre
     *   it is valid and associated with this list.
     * @throws Exception
     *   If the preconditions are not met.
     */
	public T get(Cursor<T> c) throws Exception {
		DoublyLinkedListCursor<T> dllc = (DoublyLinkedListCursor<T>) c;
		return dllc.pos.val;
	} // get
	
	
    /**
     * Get the element immediately before the Cursor<T>.
     */
	public T getPrev(Cursor<T> c) throws Exception {
		DoublyLinkedListCursor<T> dllc = (DoublyLinkedListCursor<T>) c;
		if (this.hasPrev(dllc)) {
			return dllc.pos.prev.val;
		} else {
			throw new NoSuchElementException("at beginning of list");
		}
	} // getPrev(Cursor<T>)
	
	    /**
     * Determine if it's safe to advance to the next position.
     *
     * @pre
     *   pos is valid and associated with the list.
     */
	public boolean hasNext(Cursor<T> c) {
		DoublyLinkedListCursor<T> dllc = (DoublyLinkedListCursor<T>) c;
		return (dllc.pos.next != null);
	} // hasNext
	
	    /**
     * Determine if it's safe to retreat to the previous position.
     *
     * @pre
     *   pos is valid and associated with the list.
     */
	public boolean hasPrev(Cursor<T> c) {
		DoublyLinkedListCursor<T> dllc = (DoublyLinkedListCursor<T>) c;
		return (dllc.pos.prev != null);
	} // hasPrev
	
	    /**
     * Swap the elements at the positions the corresopnd to it1 and it2.
     *
     * @pre
     *   Both it1 and it2 are valid and associated with this list.
     *   v1 = get(it1), v2 = get(it2)
     * @post
     *   it1 and it2 are unchanged.
     *   v1 = get(it2), v2 = get(it1)
     */
	public void swap(Cursor<T> c1, Cursor<T> c2) throws Exception {
		DoublyLinkedListCursor<T> dllc1 = (DoublyLinkedListCursor<T>) c1;
		DoublyLinkedListCursor<T> dllc2 = (DoublyLinkedListCursor<T>) c2;

		T tmp = dllc1.pos.val;
		dllc1.pos.val = dllc2.pos.val;
		dllc2.pos.val = tmp;
	} // swap(Cursor<T>, Cursor<T>)
	
	   /**
     * Search for a value that meets a predicate, moving the iterator to that 
     * value.
     *
     * @return true, if the value was found
     * @return false, if the value was not found
     *
     * @post If the value is not found, the iterator has not moved.
     * @post IF the value is found, get(it) is value
     */
	public boolean search(Cursor<T> c, Predicate<T> pred) throws Exception {
		DoublyLinkedListCursor<T> dllc = (DoublyLinkedListCursor<T>) c;
		Node<T> tmpNode = dllc.pos;

		// Initial test for starting element
		if (pred.test(dllc.pos.val)) {
			return true;
		}

		// Test the rest of the elements as long as there are more in the list
		while (this.hasNext(dllc)) {
			this.advance(dllc);
			if (pred.test(dllc.pos.val)) {
				return true;
			}
		}

		// If no elements meet predicate then return the cursor to its original
		// position
		dllc.pos = tmpNode;
		return false;
	} // search(Cursor<T>, Predicate<T>)
	
	    /** 
     * Select all of the elements that meet a predicate.
     */
	public ListOf<T> select(Predicate<T> pred) throws Exception {
		DoublyLinkedListCursor<T> dllc = (DoublyLinkedListCursor<T>) this
				.front();

		DoublyLinkedList<T> sub = new DoublyLinkedList<T>();

		if (pred.test(dllc.pos.val)) {
			sub.append(dllc.pos.val);
		}

		while (this.hasNext(dllc)) {
			this.advance(dllc);
			if (pred.test(dllc.pos.val)) {
				sub.append(dllc.pos.val);
			} // if
		} // while

		return sub;
	} // select(Predicate<T>)
	
	    /** 
     * Grab a sublist.  (Detailed discussion not included.)
     *
     * @pre
     *    Valid iterators.
     *    start precedes end.
     * @throws Exception
     *    If the iterators are invalid.
     */
	public ListOf<T> subList(Cursor<T> start, Cursor<T> end) throws Exception {
		DoublyLinkedListCursor<T> dllc1 = (DoublyLinkedListCursor<T>) start;
		DoublyLinkedListCursor<T> dllc2 = (DoublyLinkedListCursor<T>) end;
		if (!this.precedes(dllc1, dllc2)) {
			throw new Exception("start does not precede end");
		} // if
			// Save initial position of dllc1
		Node<T> tmp = dllc1.pos;
		DoublyLinkedList<T> sub = new DoublyLinkedList<T>();

		while (this.precedes(dllc1, dllc2)) {
			sub.append(dllc1.pos.val);
			this.advance(dllc1);
		} // while
			// add the last element
		sub.append(dllc2.pos.val);
		// return dllc1 to its original position
		dllc1.pos = tmp;

		return sub;
	} // subList(Cursor<T>, Cursor<T>)
	
	
    /**
     * Determine if one iterator precedes another iterator.
     */
	public boolean precedes(Cursor<T> c1, Cursor<T> c2) throws Exception {
		DoublyLinkedListCursor<T> dllc1 = (DoublyLinkedListCursor<T>) c1;
		DoublyLinkedListCursor<T> dllc2 = (DoublyLinkedListCursor<T>) c2;

		Node<T> tmpNode = dllc1.pos;

		// If they point to the same element then c1 does not precede c2
		if (dllc1.pos == dllc2.pos) {
			return false;
		}

		// check if dllc1.pos equals dllc2.pos after advancing the position of
		// dllc1
		while (this.hasNext(dllc1)) {
			this.advance(dllc1);
			if (dllc1.pos == dllc2.pos) {
				dllc1.pos = tmpNode;
				return true;
			}
		}
		dllc1.pos = tmpNode;
		return false;
	} // precedes(Cursor<T>, Cursor<T>)
} // class DoublyLinkedList

/**
 * Nodes in the list.
 */
class Node<T> {
	// FIELDS
	T val;
	Node<T> next;
	Node<T> prev;

	/**
	 * Create a new node.
	 */
	public Node(T val) {
		this.val = val;
		this.next = null;
		this.prev = null;
	} // Node(T)
} // Node<T>

/**
 * Cursor<T>s in the list.
 */
class DoublyLinkedListCursor<T> implements Cursor<T> {
	// FIELDS
	Node<T> pos;

	/**
	 * Create a new Cursor<T> that points to the node pos
	 */
	public DoublyLinkedListCursor(Node<T> pos) {
		this.pos = pos;
	} // DoublyLinkedListCursor<T>
} // DoublyLinkedListCursor<T>

/**
 * Iterator<T> implementation for the DoublyLinkedList class
 *
 * @param <T>
 */
class DoublyLinkedListIterator<T> implements Iterator<T> {
	// FIELDS
	Node<T> pos;
	Node<T> lastElementReturned;
	DoublyLinkedList<T> workingList;

	/**
	 * Create a new DoublyLinkedListIterator for a DoublyLinkedList<T>
	 * @throws Exception
	 */
	public DoublyLinkedListIterator(DoublyLinkedList<T> dll) {
		this.workingList = dll;
		pos = dll.front;
		lastElementReturned = null;
	}

	/**
	 * @return true, if the iterator is at an element in the list
	 * @return false, if the iterator has advanced past the last element or the
	 *         list has no values in it
	 */
	public boolean hasNext() {
		return (pos == null);
	}// 

	/**
	 * Returns the value that the iterator is currently at and then advances. If
	 * it is the last value in the list, pos will be set to null.
	 */
	public T next() throws NoSuchElementException {
		if (this.hasNext()) {
			T tmp = pos.val;
			this.lastElementReturned = pos;
			pos = pos.next;
			return tmp;
		} else {
			throw new NoSuchElementException("no more elements");
		}// else
	}// next()

	/**
	 * Removes from the underlying collection the last element returned by this
	 * iterator. This method can be called only once per call to next(). The
	 * behavior of an iterator is unspecified if the underlying collection is
	 * modified while the iteration is in progress in any way other than by
	 * calling this method. (Taken from Iterator<E> javadoc) found online at:
	 * http://docs.oracle.com/javase/7/docs/api/java/util/Iterator.html
	 * 
	 * @post all other iterators are invalid
	 */
	public void remove() throws IllegalStateException {

		if (this.lastElementReturned == null) {
			throw new IllegalStateException(
					"next has not been called since last call to remove");
		}

		if (this.hasNext()) {
			this.pos.prev = this.lastElementReturned.prev;
			if (this.lastElementReturned.prev != null) {
				// If pos is not the second element of list
				this.lastElementReturned.prev.next = this.pos;
			} else {
				// If pos is the 2nd element in the list, make it the new front
				this.workingList.front = this.pos;
			}// else
		} else {
			// next has caused the iterator to go beyond the last element
			// lastElementReturned = this.workingList.back
			this.lastElementReturned.prev.next = null;
			this.workingList.back = this.lastElementReturned.prev;
		}// else
		
		// set lastElementReturned to null so remove cannot be called more than
		// once per call to next()
		this.lastElementReturned = null;
	}// remove
}// class DoublyLinkedListIterator<T>
