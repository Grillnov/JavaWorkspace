import java.util.Calendar;

public class MyDate implements Comparable<MyDate>
{
	private Calendar timer;
	MyDate()
	{
		this.timer = Calendar.getInstance();
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
		String[] weekDays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
		Integer i = this.timer.get(Calendar.DAY_OF_WEEK) - 1;
		return weekDays[i];
	}
	public String getDate()
	{
		Integer year =  this.timer.get(Calendar.YEAR);
		Integer month = this.timer.get(Calendar.MONTH);
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
}
