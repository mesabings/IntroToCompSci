/*
 ===================================================================================
 Name: Melissa Hawley
 Student ID: 260730658
 ===================================================================================
 */

public class Egg extends MarketProduct{

	private int numberOfEggs;
	private int pricePerDozen; 
	
	public Egg(String name, int numberOfEggs, int pricePerDozen) {
		super(name);
		this.numberOfEggs = numberOfEggs;
		this.pricePerDozen = pricePerDozen;
	}
	
	public int getCost() {
		return ((numberOfEggs*pricePerDozen)/12);
	}
	
	public boolean equals(Object o) {
		if (o == null || o.getClass() != this.getClass()) {
			return false;
		}
		Egg e = (Egg)o;
		return e.getName().equals(this.getName()) && e.numberOfEggs == this.numberOfEggs && e.pricePerDozen == this.pricePerDozen;
	}
}
