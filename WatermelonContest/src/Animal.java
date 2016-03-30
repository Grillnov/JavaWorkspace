public abstract class Animal
{
	private double weight;//its weight, relevant with its maximum capacity for melon eating.
	private double capacity;//its maximum capacity for melon. stops eating when capacity is reached
	private double velocity;//its speed to eat melons
	private String name;//its name
	private double melonEatenSofar;//melon amount it has eaten so far
	
	static final double landBasedCapacityFactor = 0.5;//some constant factors for calculating its velocity and capacity.
	static final double airBasedCapacityFactor = 0.4;
	
	static final double landBasedVelocityFactor = 0.03;
	static final double airBasedVelocityFactor = 0.05;
	static final double marineBasedVelocityFactor = 0.005;
	
	
	abstract void eatMelon(double givenTime);
	//set the melon it can eat given the time limited. Different kinds of animals are supposed to implement it respectively.
	abstract void deriveCapacity();
	//derive its capacity. Different kinds of animals are supposed to implement it respectively.
	abstract void deriveVelocity();
	//derive its velocity for melon eating. Different kinds of animals implement it respectively.
	
	public String getName()
	{
		return name;
	}
	//getter member function for name getting just in case
	
	protected void setName(String name)
	{
		this.name = name;
	}
	//setter member function for name setting
	
	protected double getWeight() 
	{
		return weight;
	}
	//as above
	
	protected void setWeight(double weight) 
	{
		this.weight = weight;
	}
	
	protected double getCapacity()
	{
		return capacity;
	}
	
	protected void setCapacity(double capacity)
	{
		this.capacity = capacity;
	}
	
	protected void setVelocity(double velocity)
	{
		this.velocity = velocity;
	}
	
	protected double getVelocity()
	{
		return velocity;
	}
	
	protected void setMelonEatenSofar(double melon)
	{
		this.melonEatenSofar = melon;
	}
	
	protected void addMelonEatenSofar(double melon)
	{
		this.melonEatenSofar += melon;
	}//in case there's any need to add this amount
	
	protected double getMelonEatenSofar()
	{
		return this.melonEatenSofar;
	}
	
	Animal(String Name, double Weight)
	{
		setName(Name);
		setWeight(Weight);
		deriveCapacity();
		deriveVelocity();
		setMelonEatenSofar(0.0);
	}
}

class LandbasedAnimal extends Animal
{	
	LandbasedAnimal(String Name, double Weight) 
	{
		super(Name, Weight);
	}
	
	void deriveCapacity()
	{
		this.setCapacity(landBasedCapacityFactor * this.getWeight());//land based animals can take in 0.3 times its weight.
	}
	
	void deriveVelocity()
	{
		this.setVelocity(landBasedVelocityFactor * this.getWeight());//land based animals have a factor of 0.05 in its velocity of eating melons
	}
	
	void eatMelon(double givenTime)
	{
		double estimatedMelon = this.getVelocity() * givenTime;
		if(estimatedMelon > this.getCapacity())
			this.addMelonEatenSofar(this.getCapacity());
		else
			this.addMelonEatenSofar(estimatedMelon);
	}
}

class AirbasedAnimal extends Animal
{
	AirbasedAnimal(String Name, double Weight)
	{
		super(Name, Weight);
	}
	
	void deriveCapacity()
	{
		this.setCapacity(airBasedCapacityFactor * this.getWeight());//flying animal has less capacity than a land based one
	}
	
	void deriveVelocity()
	{
		this.setVelocity(airBasedVelocityFactor * this.getWeight());//...but they are capable of eating faster
	}
	
	void eatMelon(double givenTime)
	{
		double estimatedMelon = this.getVelocity() * givenTime;
		if(estimatedMelon > this.getCapacity())//birds tend to defecate faster so this function is implemented differently
		{
			double timeTofull = this.getCapacity()/this.getVelocity();
			double timeLeft = givenTime - timeTofull - 0.03;//0.03: time for it to defecate
			if(timeLeft > 0)
			{
				this.addMelonEatenSofar(this.getCapacity());
				eatMelon(timeLeft);
			}
			else
			{
				this.addMelonEatenSofar(this.getCapacity());
			}
		}
	}
}

class MarinebasedAnimal extends Animal
{
	MarinebasedAnimal(String Name, double Weight)
	{
		super(Name, Weight);
	}
	
	@Override void deriveCapacity()
	{
		//it's an empty function
		//marine animals can contain infinite melon
		//so no capacity derivation is needed
	}
	
	void deriveVelocity()
	{
		this.setVelocity(marineBasedVelocityFactor * this.getWeight());//for balancing issues it eat much slower
	}
	
	@Override void eatMelon(double givenTime)
	{
		this.addMelonEatenSofar(givenTime * this.getVelocity());//no need to check if the capacity is reached or exceeded for it can contain infinite melon, so stronk
	}
}