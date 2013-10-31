package edu.grinnell.csc207.goldstei1.LinkedLists;

import static org.junit.Assert.*;

import org.junit.Test;

public class DoublyLinkedListTest {

	@Test
	public void test() throws Exception {
		DoublyLinkedList<String> testDLL = new DoublyLinkedList<String>();
		testDLL.append("Sam");
		testDLL.append("ate");
		testDLL.append("no");
		testDLL.append("hot dogs");
		String[] array1 = {"Sam", "ate", "no", "hot dogs"};
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
		String[] array2 = {"My", "Professor", "Sam", "ate", "hot dogs"};
		for(int i = 0; i < 4; i++) { 
			assertEquals(array2[i], testDLL.get(cursor));
			testDLL.advance(cursor);	
		}
		assertEquals(array2[4], testDLL.get(cursor));
	}
		

}
