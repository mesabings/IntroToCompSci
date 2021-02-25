/*
 ===================================================================================
 Name: Melissa Hawley
 Student ID: 260730658
 ===================================================================================
 */
public class Fruit extends MarketProduct{
	private double weight;
	private int price;
	
	public Fruit(String name, double weight, int price) {
		super(name);
		this.setWeight(weight);
		this.setPrice(price);
	}
	
	public int getCost() {
		return(int)(this.weight*this.price);
	}
	
	public boolean equals(Object o) {
		if (o == null || o.getClass() != this.getClass()) {
			return false;
		}
		Fruit f = (Fruit)o;
		return f.getName().equals(this.getName()) && f.getWeight() == this.getWeight() && f.getPrice() == this.getPrice();
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
