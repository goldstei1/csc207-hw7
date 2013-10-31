package edu.grinnell.csc207.goldstei1.LinkedLists;

import static org.junit.Assert.*;

import org.junit.Test;

public class DoublyLinkedListTest {

	@Test
	public void test() throws Exception {
		DoublyLinkedList<String> testDLL = new DoublyLinkedList<String>();
		testDLL.append("Price-Jones");
		testDLL.append("walks");
		testDLL.append("weird");
		testDLL.append("dogs");
		String[] array1 = {"Price-Jones", "walks", "weird", "dogs"};
		Cursor<String> cursor = testDLL.front();
		for(int i = 0; i < 3; i++) { 
			assertEquals(array1[i], testDLL.get(cursor));
			testDLL.advance(cursor);	
		}
		assertEquals(array1[3], testDLL.get(cursor));
		testDLL.prepend("Professor");
		testDLL.prepend("My");
		testDLL.retreat(cursor);
		testDLL.delete(cursor);
		cursor = testDLL.front();
		String[] array2 = {"My", "Professor", "Price-Jones", "walks", "dogs"};
		for(int i = 0; i < 4; i++) { 
			assertEquals(array2[i], testDLL.get(cursor));
			testDLL.advance(cursor);	
		}
		assertEquals(array2[4], testDLL.get(cursor));
		
		testDLL.delete(cursor);
		cursor = testDLL.front();
		testDLL.delete(cursor);
		
		String[] array3 = {"Professor", "Price-Jones", "walks"};
		for(int i = 0; i < 2; i++) { 
			assertEquals(array3[i], testDLL.get(cursor));
			testDLL.advance(cursor);	
		}
		assertEquals(array3[2], testDLL.get(cursor));
	}
		

}
