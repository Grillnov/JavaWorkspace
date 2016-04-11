import java.lang.Integer;

public class JUnit 
{
	public static void main(String[] args)
	{
		MyListSorted<Integer> SortedList = new MyListSorted<Integer>();
		SortedList.add(2);
		SortedList.add(5);
		SortedList.add(4);
		SortedList.add(6);
		SortedList.add(1);
		SortedList.add(0);
		SortedList.add(9);
		
		for(int i = 0; i != SortedList.size(); ++i)
		{
			System.out.printf("%d, ", SortedList.toArray(Integer.class)[i]);
		}
		System.out.println();
		
		SortedList.remove(1);
		SortedList.remove(5);
		SortedList.remove(1);
		
		for(int i = 0; i != SortedList.size(); ++i)
		{
			System.out.printf("%d, ", SortedList.toArray(Integer.class)[i]);
		}
		System.out.println();
		
		MyListUnSorted<Integer> UnSortedList = new MyListUnSorted<Integer>();
		UnSortedList.add(2);
		UnSortedList.add(5);
		UnSortedList.add(13);
		
		for(int i = 0; i != UnSortedList.size(); ++i)
		{
			System.out.printf("%d, ", UnSortedList.toArray(Integer.class)[i]);
		}
		System.out.println();
		
		UnSortedList.remove(5);
		UnSortedList.remove(13);
		
		for(int i = 0; i != UnSortedList.size(); ++i)
		{
			System.out.printf("%d, ", UnSortedList.toArray(Integer.class)[i]);
		}
		System.out.println();
		
		MyDate Date1 = new MyDate();
		MyDate Date2 = new MyDate();
		
		if(Date1.compareTo(Date2) == 1)
			System.out.println("Date 1 > Date 2");
		else if(Date1.compareTo(Date2) == 0)
			System.out.println("Date 1 == Date 2");
		else
			System.out.println("Date 1 < Date 2");
		
		System.out.println(Date1.getDate());
		System.out.println(Date1.getDayofWeek());
		Date1.plusDays(30);
		System.out.println(Date1.getDate());
		System.out.println(Date1.getDayofWeek());
		
		if(Date1.compareTo(Date2) == 1)
			System.out.println("Date 1 > Date 2");
		else if(Date1.compareTo(Date2) == 0)
			System.out.println("Date 1 == Date 2");
		else
			System.out.println("Date 1 < Date 2");
	}
}
