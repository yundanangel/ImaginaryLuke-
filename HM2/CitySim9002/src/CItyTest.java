
import static org.junit.Assert.*;
import org.mockito.*;
import org.junit.Test;
public class CItyTest {
	//test if Visitor will generate type bigger than 4 or less than 0
	@Test
	public void testVisitorType() {
		Visitor v = new Visitor();
		Generator gen=new Generator();
		v.setType(gen);
		assertFalse(v.type>4);
		assertFalse(v.type<0);
	}
	//test if Visitor type would be set to generator's returned value
	//stub test
	//set generator return value to 1
	//the result should also be 1
	@Test
	public void testGenerator() {
		Visitor v = new Visitor();
		Generator gen=Mockito.mock(Generator.class);
		Mockito.when(gen.Generator(4)).thenReturn(1);
		v.setType(gen);
		assertEquals(1,v.type);
	}
	//Stub test
	//set student type to "HAHA", number to 1
	//the returned message should be "Visitor 1 is a HAHA."
	@Test
	public void testGetType() {
		Visitor v = new Visitor();
		v.n=1;
		v.type=1;
		Type t=Mockito.mock(Type.class);
		Mockito.when(t.Type(1)).thenReturn("HAHA");
		assertEquals("Visitor 1 is a HAHA.", v.getType(t));
	}
	//test if Type function return the right type of visitor
	//0-Student, 1-Professor,2-Businessman,3-Blogger
	@Test
	public void testType() {
		Type t = new Type();
		assertEquals("Student", t.Type(0));
		assertEquals("Professor", t.Type(1));
		assertEquals("Businessman", t.Type(2));
		assertEquals("Blogger", t.Type(3));
	}
	//test if Location function return the right Location name
	//0-The Cathedral of Learning,1- Squirrel Hill, 2-The Point, 3-Downtown
	@Test
	public void testLocation() {
		Location l = new Location();
		assertEquals("The Cathedral of Learning", l.Location(0));
		assertEquals("Squirrel Hill", l.Location(1));
		assertEquals("The Point", l.Location(2));
		assertEquals("Downtown", l.Location(3));
	}
	//test if preference return right relation ship between visitor type and location
	//True:like, False:Dislike
	//choose Example 1. Student (0)-"The Cathedral of Learning" (0): False
	//				 2. Student (0)-Other locations(1-3):True
	//				 3. Professor (1)- All locations(0-3):True
	//				 4. Businessman(2)-"The Cathedral of Learning"/"The Point"(0,2)：False
	//				 5. Businessman(2)-"Squirrel Hill"/"Downtown"(1,3)：Ture
	//				 6. Blogger(3)-All locations(0-3):False
	@Test
	public void testPreference(){
		Preference p=new Preference();
		assertFalse(p.Preference(0, 0));
		for(int i=1;i<4;i++){
			assertTrue(p.Preference(0, i));
		}
		for(int i=0;i<4;i++){
			assertTrue(p.Preference(1, i));
		}
		assertFalse(p.Preference(2, 0));
		assertFalse(p.Preference(2, 2));
		assertTrue(p.Preference(2, 1));
		assertTrue(p.Preference(2, 3));
		for(int i=0;i<4;i++){
			assertFalse(p.Preference(3, i));
		}
	}
	//test visit a place
	//Stub test
	//set number to 1, Location to "Wonderland",generated number to 1,type to 1
	// the returned value should be "Visitor 1 is going to Wonderland."
	@Test
	public void testVisitaplace(){
		Preference p=new Preference();
		Generator gen=Mockito.mock(Generator.class);
		Mockito.when(gen.Generator(5)).thenReturn(1);
		Location l=Mockito.mock(Location.class);
		Mockito.when(l.Location(1)).thenReturn("Wonderland");
		Visitor v=new Visitor();
		v.n=1;
		v.type=1;
		assertEquals("Visitor 1 is going to Wonderland.",v.visitaplace(l, p, gen));
	}
}
