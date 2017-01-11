package classes;

public class Car 
{
	//declare fields 
	private String make;
	private String model;
	private double time;
	
	//create constructor
	public Car(String make, String model)
	{
		super();
		this.make = make;
		this.model = model;
		getTime();
	}
	
	//getters and setters
	public String getMake()
	{
		return make;
	}
	public String getModel()
	{
		return model;
	}
	public double getTime()
	{
		time =( Math.random()*10) + 1;
		return time;
	}

	@Override
	public String toString()
	{
		return "Car Make: " + make + 
				"\nCar Model: " + model + 
				"\nTime: " + time;
	}
	
	
	
	

}
