package edu.grinnell.csc207.goldstei1.LinkedLists;

/**
 * Nodes in the list.
 */
public class Node<T> {
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
