package edu.grinnell.csc207.goldstei1.LinkedLists;

public interface Predicate<T> {
    /**
	 * Determine if a value meets the predicate.
	 */
	boolean test(T val);
} // Predicate<T>