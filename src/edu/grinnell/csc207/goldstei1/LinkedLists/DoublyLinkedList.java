package edu.grinnell.csc207.goldstei1.LinkedLists;

import java.util.Iterator;

/**
 * Doubly linked lists.
 */
public class DoublyLinkedList<T> implements ListOf<T> {

    // FIELDS

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
        throw new UnsupportedOperationException("STUB");
    } // append(T)

    public void prepend(T val) throws Exception {
        throw new UnsupportedOperationException("STUB");
    } // prepend(T)

    public void delete(Cursor c) throws Exception {
        throw new UnsupportedOperationException("STUB");
    } // delete(Cursor)

    public Cursor front() throws Exception {
        throw new UnsupportedOperationException("STUB");
    } // front()

    public void advance(Cursor c) throws Exception {
        throw new UnsupportedOperationException("STUB");
    } // advance(Cursor)

    public void retreat(Cursor c) throws Exception {
        throw new UnsupportedOperationException("STUB");
    } // retreat(Cursor)

    public T get(Cursor c) throws Exception {
        throw new UnsupportedOperationException("STUB");
    } // get

    public T getPrev(Cursor c) throws Exception {
        throw new UnsupportedOperationException("STUB");
    } // getPrev(Cursor)

    public boolean hasNext(Cursor c) {
        return false;   // STUB
    } // hasNext

    public boolean hasPrev(Cursor c) {
        return false;   // STUB
    } // hasPrev

    public void swap(Cursor c1, Cursor c2) throws Exception {
        throw new UnsupportedOperationException("STUB");
    } // swap(Cursor, Cursor)

    public boolean search(Cursor c, Predicate<T> pred) throws Exception {
        throw new UnsupportedOperationException("STUB");
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

