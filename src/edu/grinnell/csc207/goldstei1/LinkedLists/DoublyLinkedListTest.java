package edu.grinnell.csc207.goldstei1.LinkedLists;

import static org.junit.Assert.*;

import org.junit.Test;

public class DoublyLinkedListTest {


	@Test
	public void test() throws Exception {
		// Test advance, append, front, and get
		DoublyLinkedList<String> testDLL = new DoublyLinkedList<String>();
		testDLL.append("Price-Jones");
		testDLL.append("walks");
		testDLL.append("weird");
		testDLL.append("dogs");
		String[] array1 = {"Price-Jones", "walks", "weird", "dogs"};
		Cursor<String> cursor = testDLL.front();
		for(int i = 0; i < 3; i++) { 
			assertEquals("append and get test", array1[i], testDLL.get(cursor));
			testDLL.advance(cursor);	
		}
		assertEquals(array1[3], testDLL.get(cursor));
		
		// Test prepend, retreat, and delete
		testDLL.prepend("Professor");
		testDLL.prepend("My");
		testDLL.retreat(cursor);
		testDLL.delete(cursor);
		cursor = testDLL.front();
		String[] array2 = {"My", "Professor", "Price-Jones", "walks", "dogs"};
		for(int i = 0; i < 4; i++) { 
			assertEquals("retreat and prepend test", array2[i], testDLL.get(cursor));
			testDLL.advance(cursor);	
		}
		assertEquals(array2[4], testDLL.get(cursor));
		
		// Test delete if cursor is at front or back
		testDLL.delete(cursor);
		cursor = testDLL.front();
		testDLL.delete(cursor);
		
		String[] array3 = {"Professor", "Price-Jones", "walks"};
		for(int i = 0; i < 2; i++) { 
			assertEquals("delete first and last", array3[i], testDLL.get(cursor));
			testDLL.advance(cursor);	
		}
		assertEquals(array3[2], testDLL.get(cursor));
		
		
		// Test swap
		array3[0] = "walks";
		array3[2] = "Professor";
		Cursor<String> cursor2 = testDLL.front();
		testDLL.swap(cursor, cursor2);
		for(int i = 0; i < 2; i++) { 
			assertEquals("swap test", array3[i], testDLL.get(cursor2));
			testDLL.advance(cursor2);	
		}
		assertEquals(array3[2], testDLL.get(cursor2));
		
		// Test insert
		
		String[] array4 = {"Sam", "will", "not", "grade", "this"};
		DoublyLinkedList<String> testDLL2 = new DoublyLinkedList<String>();
		
		testDLL2.append("Sam");
		
		cursor = testDLL2.front();
		testDLL2.insert("will", cursor);
		testDLL2.advance(cursor);
		testDLL2.insert("not", cursor);
		testDLL2.advance(cursor);
		testDLL2.insert("grade", cursor);
		testDLL2.advance(cursor);
		testDLL2.insert("this", cursor);
		
		cursor = testDLL2.front();
		for(int i = 0; i < 4; i++) { 
			assertEquals("Insert Test", array4[i], testDLL2.get(cursor));
			testDLL2.advance(cursor);	
		}
		assertEquals(array4[4], testDLL2.get(cursor));
		

		
	}
}
