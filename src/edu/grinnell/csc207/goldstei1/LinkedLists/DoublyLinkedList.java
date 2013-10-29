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
        return null;    // STUB
    } // iterator()

    // LISTOF METHODS
    public void insert(T val, Cursor c) throws Exception {
        throw new UnsupportedOperationException("STUB");
    } // insert(T, Cursor)

    public void append(T val) throws Exception {
    	if (this.front == null) {
        	this.front = new Node<T>(val);
        	this.back = this.front;
        } else {
        	Node<T> n = new Node<T>(val);
        	this.back.next = n;
        	n.prev = this.back;
        	this.back = n;
        }
    } // append(T)

    public void prepend(T val) throws Exception {
        if (this.front == null) {
        	this.front = new Node<T>(val);
        	this.back = this.front;
        } else {
        	Node<T> n = new Node<T>(val);
        	this.front.prev = n;
        	n.next = this.front;
        	this.front = n;
        }
    } // prepend(T)

    public void delete(Cursor c) throws Exception {
        throw new UnsupportedOperationException("STUB");
    } // delete(Cursor)

    public Cursor front() throws Exception {
    	if (this.front == null) {
    		throw new NoSuchElementException("empty list");
    	}
        Cursor c = new DoublyLinkedListCursor<T>(this.front);
        return c;
    } // front()

    public void advance(Cursor c) throws Exception {
    	DoublyLinkedListCursor<T> dllc = (DoublyLinkedListCursor<T>) c;
    	if (this.hasNext(dllc)) {
    	    dllc.pos = dllc.pos.next;
    	} else {
    		throw new NoSuchElementException("at end of list");
    	}
        
    } // advance(Cursor)

    public void retreat(Cursor c) throws Exception {
    	DoublyLinkedListCursor<T> dllc = (DoublyLinkedListCursor<T>) c;
    	if (this.hasPrev(dllc)) {
    	    dllc.pos = dllc.pos.prev;
    	} else {
    		throw new NoSuchElementException("at beginning of list");
    	}
    } // retreat(Cursor)

    public T get(Cursor c) throws Exception {
    	DoublyLinkedListCursor<T> dllc = (DoublyLinkedListCursor<T>) c;
    	return dllc.pos.val;
    } // get

    public T getPrev(Cursor c) throws Exception {
    	DoublyLinkedListCursor<T> dllc = (DoublyLinkedListCursor<T>) c;
    	if (this.hasPrev(dllc)) {
    	    return dllc.pos.prev.val;
    	} else {
    		throw new NoSuchElementException("at beginning of list");
    	}
    } // getPrev(Cursor)

    public boolean hasNext(Cursor c) {
    	DoublyLinkedListCursor<T> dllc = (DoublyLinkedListCursor<T>) c;
    	return (dllc.pos.next != null);
    } // hasNext

    public boolean hasPrev(Cursor c) {
    	DoublyLinkedListCursor<T> dllc = (DoublyLinkedListCursor<T>) c;
    	return (dllc.pos.prev != null);
    } // hasPrev

    public void swap(Cursor c1, Cursor c2) throws Exception {
    	DoublyLinkedListCursor<T> dllc1 = (DoublyLinkedListCursor<T>) c1;
    	DoublyLinkedListCursor<T> dllc2 = (DoublyLinkedListCursor<T>) c2;
    
    	T tmp = dllc1.pos.val;
    	dllc1.pos.val = dllc2.pos.val;
    	dllc2.pos.val = tmp;
    } // swap(Cursor, Cursor)

    public boolean search(Cursor c, Predicate<T> pred) throws Exception {
    	DoublyLinkedListCursor<T> dllc = (DoublyLinkedListCursor<T>) this.front();
    	
    	while (this.hasNext(dllc)) {
    		if (pred.test(dllc.pos.val)) {
    			c.pos = dllc.pos;
    			return true;
    		}
    		this.advance(dllc);
    	}
    	return false;
    	
    	

    } // search(Cursor, Predicate<T>)
     
    public ListOf<T> select(Predicate<T> pred) throws Exception {
        throw new UnsupportedOperationException("STUB");
    } // select(Predicate<T>)
     
    public ListOf<T> subList(Cursor start, Cursor end) throws Exception {
        throw new UnsupportedOperationException("STUB");
    } // subList(Cursor, Cursor)

    public boolean precedes(Cursor c1, Cursor c2) throws Exception {
        throw new UnsupportedOperationException("STUB");
    } // precedes(Cursor, Cursor)
} // class DoublyLinkedList

/**
 * Nodes in the list.
 */
class Node<T> {
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
 * Cursors in the list.
 */
class DoublyLinkedListCursor<T> implements Cursor {
    Node<T> pos;

    /**
     * Create a new cursor that points to a node.
     */
    public DoublyLinkedListCursor(Node<T> pos) {
        this.pos = pos;
    } // DoublyLinkedListCursor
} // DoublyLinkedListCursor<T>

