import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class MelonContest 
{
	static final double givenTime = 300.0;
	public static void main(String[] Arguments)
	{
		LandbasedAnimal doge = new LandbasedAnimal("doge", 50.0);
		LandbasedAnimal melonskinCat = new LandbasedAnimal("melonskinCat", 30.0);//seems a little bit too heavy...
		AirbasedAnimal baldEagle = new AirbasedAnimal("baldEagle", 37.0);
		AirbasedAnimal swallow = new AirbasedAnimal("swallow", 14.0);
		MarinebasedAnimal seal = new MarinebasedAnimal("seal", 140.0);
		
		List<Animal> candidateList = new ArrayList<Animal>();
		candidateList.add(doge);
		candidateList.add(melonskinCat);
		candidateList.add(baldEagle);
		candidateList.add(swallow);
		candidateList.add(seal);
		
		for(Animal i : candidateList)
		{
			i.eatMelon(givenTime);
		}
		
		String winner = new String();
		double largestMelon = 0.0;
		for(Animal i : candidateList)
		{
			double melonEaten = i.getMelonEatenSofar();
			if(melonEaten > largestMelon)
			{
				largestMelon = melonEaten;
				winner = i.getName();
			}
		}
		
		System.out.printf("The watermelon eating contest's got a winner!\n");
		System.out.printf("Within %f minutes, the winner %s won with %f melon eaten!\n", givenTime, winner, largestMelon);
	}
}