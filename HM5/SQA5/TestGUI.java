import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;  

import org.junit.Before;
import org.junit.Test;
public class TestGUI {
	Cell c;
	MainPanel m;
	@Before  
	public void setUp() throws Exception {  
	    c = new Cell(); 
	    m=new MainPanel(10);
	}  
	//Pinning test for toInt(int x) method in MainPanel.java
	//Given the convertoInt method is a private method and I don't want to test this manually, I created a mirror for the method
	
	// convertToInt 1:
	// A cell would have at least 0 alive surrounding cells
	// Precondition: A new MainPanel m is generated with 10*10 world using MainPanel(10)
	// Execution: input 0 as augment into method convertToInt
	// Post Condition: return 0
	@Test
	public void TestToInt1() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method newm = m.getClass().getDeclaredMethod("convertToInt",int.class); 
		newm.setAccessible(true);
		int result=(int)newm.invoke(m, 0);
		assertEquals(0,result);
	}
	// convertToInt 2:
	// A cell would have at most 8 
	// Precondition: A new MainPanel m is generated with 10*10 world using MainPanel(10)
	// Execution: input 8 as augment into method convertToInt
	// Post Condition: return 8
	@Test
	public void TestToInt2() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method newm = m.getClass().getDeclaredMethod("convertToInt",int.class); 
		newm.setAccessible(true);
		int result=(int)newm.invoke(m, 8);
		assertEquals(8,result);
	}
	// convertToInt 3:
	// Consider the chance that a cell might not have to have more than 8
	// the method though should still return the integer passed in
	// Precondition: A new MainPanel m is generated with 10*10 world using MainPanel(10)
	// Execution: input 10 as augment into method convertToInt
	// Post Condition: return 10
	@Test
	public void TestToInt3() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method newm = m.getClass().getDeclaredMethod("convertToInt",int.class); 
		newm.setAccessible(true);
		int result=(int)newm.invoke(m, 10);
		assertEquals(10,result);
	}
	// convertToInt 4:
	// Consider the chance that a cell might not have to have smaller than 0
	// the method though should still return the integer passed in
	// Precondition: A new MainPanel m is generated with 10*10 world using MainPanel(10)
	// Execution: input -1 as augment into method convertToInt
	// Post Condition: return -1
	@Test
	public void TestToInt4() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method newm = m.getClass().getDeclaredMethod("convertToInt",int.class); 
		newm.setAccessible(true);
		int result=(int)newm.invoke(m, -1);
		assertEquals(-1,result);
	}
	// Test the ToString Method in Cell
	// This method convert a single character from string to string...
	// ToString 1:
	// When a cell is not alive, its text would be " ", in this case, the method should return "."
	// Precondition: A new Cell c is generated without being alive using Cell()
	// Execution: Run toString method using c.toString
	// PostCondition: Returned value should be "."
	@Test
	public void TestToString1(){
		String s= c.toString();
		assertTrue(s.equals("."));
		
	}
	// ToString 1:
	// When a cell is alive, its text would be "X", in this case, the method should return "X"
	// Precondition: A new Cell c is generated without being alive using Cell(), then make the cell alive using c.setAlive(true)
	// Execution: Run toString method using c.toString
	// PostCondition: Returned value should be "X"
	@Test
	public void TestToString2(){
		c.setAlive(true);
		String s= c.toString();
		assertTrue(s.equals("X"));
	}
	// ToString 1:
	// When a cell is alive, its text would be "X", then if the cell is reset, the text would be ".", in this case, the method should return "X"
	// Precondition: A new Cell c is generated without being alive using Cell(), then make the cell alive using c.setAlive(true), then reset the cell using c.reset()
	// Execution: Run toString method using c.toString
	// PostCondition: Returned value should be "."
	@Test
	public void TestToString3(){
		c.setAlive(true);
		c.reset();
		String s= c.toString();
		assertTrue(s.equals("."));
		
	}
}
