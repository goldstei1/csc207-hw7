package edu.grinnell.csc207.goldstei1.LinkedLists;

/**
 * Cursors in the list.
 */
public class DoublyLinkedListCursor<T> implements Cursor {
    Node<T> pos;

    /**
     * Create a new cursor that points to a node.
     */
    public DoublyLinkedListCursor(Node<T> pos) {
        this.pos = pos;
    } // DoublyLinkedListCursor
} // DoublyLinkedListCursor<T>

