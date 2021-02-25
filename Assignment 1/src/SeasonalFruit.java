/*
 ===================================================================================
 Name: Melissa Hawley
 Student ID: 260730658
 ===================================================================================
 */
public class SeasonalFruit extends Fruit {
	public SeasonalFruit(String name, double weight, int price) {
		super(name, weight, price);
	}
	
	public int getCost() {
		return (int) (0.85*super.getCost());
	}

}
