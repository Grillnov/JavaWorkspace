import java.util.Calendar;
import java.util.Date;

public class MyDate implements Comparable<MyDate>
{
	private static final String[] weekDays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
	private Calendar timer;
	MyDate()
	{
		this.timer = Calendar.getInstance();
	}
	MyDate(int year, int month, int day)
	{
		this.timer = Calendar.getInstance();
		timer.set(Calendar.YEAR, year);
		timer.set(Calendar.MONTH, month);
		timer.set(Calendar.DAY_OF_MONTH, day);
	}
	MyDate(Date date)
	{
		this.timer = Calendar.getInstance();
		timer.setTime(date);
	}
	public void plusDays(Integer amountofDays)
	{
		updateDays(amountofDays);
	}
	public void minusDays(Integer amountofDays)
	{
		updateDays(-amountofDays);
	}
	private void updateDays(Integer amountofDays)
	{
		this.timer.add(this.timer.DATE, amountofDays);
	}
	public String getDayofWeek()
	{
		Integer i = this.timer.get(Calendar.DAY_OF_WEEK) - 1;
		return weekDays[i];
	}
	public String getDate()
	{
		Integer year =  this.timer.get(Calendar.YEAR);
		Integer month = this.timer.get(Calendar.MONTH) + 1;
		Integer day = this.timer.get(Calendar.DAY_OF_MONTH);
		return year.toString() + "-" + month.toString() + "-" + day.toString();
	}
	@Override
	public int compareTo(MyDate arg0) 
	{
		int Index = this.timer.get(Calendar.YEAR) - arg0.timer.get(Calendar.YEAR);
		if(Index != 0)
		{
			if(Index > 0)
				return 1;
			else
				return -1;
		}
		Index = this.timer.get(Calendar.MONTH) - arg0.timer.get(Calendar.MONTH);
		if(Index != 0)
		{
			if(Index > 0)
				return 1;
			else
				return -1;
		}
		Index = this.timer.get(Calendar.DAY_OF_MONTH) - arg0.timer.get(Calendar.DAY_OF_MONTH);
		if(Index == 0)
			return 0;
		else if(Index > 0)
			return 1;
		else
			return -1;
	}
	@Override
	public int hashCode()
	{
		return 1000*this.timer.get(Calendar.YEAR) + timer.get(Calendar.DAY_OF_YEAR);//4 digits of year + 3 digits of dayofyear.
	}
	@Override
	public boolean equals(Object rhs)
	{
        if (rhs == null)
            throw new NullPointerException();
        else if (rhs.getClass() == this.getClass())
            return rhs.hashCode() == this.hashCode();
        else
            return false;
    }
}
