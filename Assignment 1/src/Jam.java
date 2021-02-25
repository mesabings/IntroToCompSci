/*
 ===================================================================================
 Name: Melissa Hawley
 Student ID: 260730658
 ===================================================================================
 */
public class Jam extends MarketProduct {
	private int numOfJars;
	private int price;
	
	public Jam(String name, int price, int numOfJars) {
		super(name);
		this.price = price;
		this.numOfJars = numOfJars;
	}
	
	public int getCost() {
		return (price*numOfJars);
	}
	
	public boolean equals(Object o) {
		if (o == null || o.getClass() != this.getClass()) {
			return false;
		}
		Jam j = (Jam)o;
		return j.getName().equals(this.getName()) && j.numOfJars == this.numOfJars && j.price == this.price;
	}
}
