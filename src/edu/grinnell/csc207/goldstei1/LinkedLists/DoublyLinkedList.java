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
    public void insert(T val, Cursor<T> c) throws Exception {
        throw new UnsupportedOperationException("STUB");
    } // insert(T, Cursor<T>)

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

    public void delete(Cursor<T> c) throws Exception {
        throw new UnsupportedOperationException("STUB");
    } // delete(Cursor<T>)

    public Cursor<T> front() throws Exception {
    	if (this.front == null) {
    		throw new NoSuchElementException("empty list");
    	}
        Cursor<T> c = new DoublyLinkedListCursor<T>(this.front);
        return c;
    } // front()

    public void advance(Cursor<T> c) throws Exception {
    	DoublyLinkedListCursor<T> dllc = (DoublyLinkedListCursor<T>) c;
    	if (this.hasNext(dllc)) {
    	    dllc.pos = dllc.pos.next;
    	} else {
    		throw new NoSuchElementException("at end of list");
    	}
        
    } // advance(Cursor<T>)

    public void retreat(Cursor<T> c) throws Exception {
    	DoublyLinkedListCursor<T> dllc = (DoublyLinkedListCursor<T>) c;
    	if (this.hasPrev(dllc)) {
    	    dllc.pos = dllc.pos.prev;
    	} else {
    		throw new NoSuchElementException("at beginning of list");
    	}
    } // retreat(Cursor<T>)

    public T get(Cursor<T> c) throws Exception {
    	DoublyLinkedListCursor<T> dllc = (DoublyLinkedListCursor<T>) c;
    	return dllc.pos.val;
    } // get

    public T getPrev(Cursor<T> c) throws Exception {
    	DoublyLinkedListCursor<T> dllc = (DoublyLinkedListCursor<T>) c;
    	if (this.hasPrev(dllc)) {
    	    return dllc.pos.prev.val;
    	} else {
    		throw new NoSuchElementException("at beginning of list");
    	}
    } // getPrev(Cursor<T>)

    public boolean hasNext(Cursor<T> c) {
    	DoublyLinkedListCursor<T> dllc = (DoublyLinkedListCursor<T>) c;
    	return (dllc.pos.next != null);
    } // hasNext

    public boolean hasPrev(Cursor<T> c) {
    	DoublyLinkedListCursor<T> dllc = (DoublyLinkedListCursor<T>) c;
    	return (dllc.pos.prev != null);
    } // hasPrev

    public void swap(Cursor<T> c1, Cursor<T> c2) throws Exception {
    	DoublyLinkedListCursor<T> dllc1 = (DoublyLinkedListCursor<T>) c1;
    	DoublyLinkedListCursor<T> dllc2 = (DoublyLinkedListCursor<T>) c2;
    
    	T tmp = dllc1.pos.val;
    	dllc1.pos.val = dllc2.pos.val;
    	dllc2.pos.val = tmp;
    } // swap(Cursor<T>, Cursor<T>)

    public boolean search(Cursor<T> c, Predicate<T> pred) throws Exception {
    	DoublyLinkedListCursor<T> dllc = (DoublyLinkedListCursor<T>) c;
    	Node<T> tmpNode = dllc.pos;
    
    	while (this.hasNext(dllc)) {
    		if (pred.test(dllc.pos.val)) {
    			return true;
    		}
    		this.advance(dllc);
    	}
    	dllc.pos = tmpNode;
    	return false;
    } // search(Cursor<T>, Predicate<T>)
     
    public ListOf<T> select(Predicate<T> pred) throws Exception {
        throw new UnsupportedOperationException("STUB");
    } // select(Predicate<T>)
     
    public ListOf<T> subList(Cursor<T> start, Cursor<T> end) throws Exception {
        throw new UnsupportedOperationException("STUB");
    } // subList(Cursor<T>, Cursor<T>)

    public boolean precedes(Cursor<T> c1, Cursor<T> c2) throws Exception {
        throw new UnsupportedOperationException("STUB");
    } // precedes(Cursor<T>, Cursor<T>)
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
 * Cursor<T>s in the list.
 */
class DoublyLinkedListCursor<T> implements Cursor<T> {
    Node<T> pos;

    /**
     * Create a new Cursor<T> that points to a node.
     */
    public DoublyLinkedListCursor(Node<T> pos) {
        this.pos = pos;
    } // DoublyLinkedListCursor<T>
} // DoublyLinkedListCursor<T>

