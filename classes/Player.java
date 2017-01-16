package classes;

public class Player
{
	private String name;		//Name of the player
	private double cash = 100;	//Cash the player has (Always starts at 100)
	private Car vehicle;		//Vehicle the player has
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getCash()
	{
		return cash;
	}
	
	public double setCash(double cash)
	{
		this.cash += cash;
		return this.cash;
	}
	
	public void setVehicle(Car car)
	{
		vehicle = new Car(car.getMake(), car.getModel());
	}
	
	public Car getVehicle()
	{
		return vehicle;
	}
	
	public String toString()
	{
		return vehicle.toString() + "\nCash: " + cash;
	}
}
