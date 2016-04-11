import java.lang.Integer;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UnitTest 
{
	private MyListUnSorted<Integer> Unsorted1;
	private MyListUnSorted<Integer> Unsorted2;
	
	private MyListSorted<String> Sorted1;
	private MyListSorted<String> Sorted2;
	
	private MyListSorted2<String> Sorted3;
	private MyListSorted2<String> Sorted4;
	
	private MyDate DateManager, DateManager2, DateReference;
	@Before
    public void Initialize() 
    {
		Unsorted1 = new MyListUnSorted<Integer>();
		Unsorted2 = new MyListUnSorted<Integer>();
		Sorted1 = new MyListSorted<String>();
		Sorted2 = new MyListSorted<String>();
		Sorted3 = new MyListSorted2<String>();
		Sorted4 = new MyListSorted2<String>();
		
		Unsorted1.add(7);Unsorted1.add(4);Unsorted1.add(5);Unsorted1.add(2);Unsorted1.add(3);
		Unsorted2.add(7);Unsorted2.add(4);Unsorted2.add(2);Unsorted2.add(3);
		Sorted1.add("Ca");Sorted1.add("As");Sorted1.add("At");Sorted1.add("Tl");
		Sorted2.add("As");Sorted2.add("At");Sorted2.add("Tl");Sorted2.add("Ca");
		Sorted3.add("Ca");Sorted3.add("As");Sorted3.add("At");Sorted3.add("Tl");
		Sorted4.add("As");Sorted4.add("At");Sorted4.add("Tl");Sorted4.add("Ca");
		
		DateManager = new MyDate(2012, 1, 1);
		DateManager2 = new MyDate(2012, 1, 31);
		DateReference = new MyDate(new Date());
    }

    @Test
    public void SortingTest()
    {
		assertTrue("Sorting function is malfunctioning", Sorted1.equals(Sorted2));
    }

    @Test
    public void SortingTest2()
    {
    	assertTrue("Sorting function is malfunctioning", Sorted3.equals(Sorted4));
    }
    
    @Test
    public void clearTest1()
    {
    	Unsorted1.clear();
    	MyListUnSorted<Integer> Unsorted2 = new MyListUnSorted<Integer>();
    	assertTrue("Clearing function(unsorted) is malfunctioning", Unsorted1.equals(Unsorted2));
    }
    
    @Test
    public void clearTest2()
    {	
    	Sorted1.clear();
    	MyListSorted<String> Sorted2 = new MyListSorted<String>();
    	assertTrue("Clearing function(sorted1) is malfunctioning", Sorted1.equals(Sorted2));
    }
    
    @Test
    public void clearTest3()
    {
    	Sorted3.clear();
    	MyListSorted2<String> Sorted2 = new MyListSorted2<String>();
    	assertTrue("Clearing function(sorted2) is malfunctioning", Sorted3.equals(Sorted2));
    }

    @Test
    public void removeTest1()
    {
    	Unsorted1.remove(5);
    	assertTrue("Remove function(unsorted) is malfunctioning", Unsorted1.equals(Unsorted2));
    }
    
    @Test
    public void removeTest2()
    {
    	Sorted1.remove("Ca");
    	Sorted2.remove("Ca");
    	assertTrue("Remove function(sorted1) is malfunctioning", Sorted1.equals(Sorted2));
    }
    
    @Test
    public void removeTest3()
    {
    	Sorted3.remove("Ca");
    	Sorted4.remove("Ca");
    	assertTrue("Remove function(sorted1) is malfunctioning", Sorted3.equals(Sorted4));
    }

    @Test
    public void contains()
    {
    	Object[] a = Sorted1.toArray();
		Object[] b = Sorted2.toArray();
		for (int i = 0;i < Sorted1.size();i++)
		{
			assertTrue("Contains function is malfunctioning", Sorted1.contains((String)a[i]) == Sorted2.contains((String)b[i]));
		}
    }
    
    @Test
    public void DayupdatingTest1()
    {
    	DateManager.plusDays(30);
    	assertTrue("plusDays function is malfunctioning", DateManager.equals(DateManager2));
    }
    
    @Test
    public void DayupdatingTest2()
    {
    	DateManager2.minusDays(30);
    	assertTrue("plusDays function is malfunctioning", DateManager.equals(DateManager2));
    }
    
    @Test
    public void dayofWeekTest()
    {
    	String res1 = DateManager.getDayofWeek();
    	DateManager.minusDays(7);
    	String res2 = DateManager.getDayofWeek();
    	assertTrue("dayofWeek function is malfunctioning", res1.equals(res2));
    }
    
    @Test
    public void currentTime()
    {
    	System.out.println(DateReference.getDate());
    }
}