
/*
 ===================================================================================
 Name: Melissa Hawley
 Student ID: 260730658
 ===================================================================================
 */

public class Customer {
	String name;
	int balance;
	Basket basket;
	
	public Customer (String name, int balance) {
		this.name = name;
		this.balance = balance;
		this.basket = new Basket();
	}
	
	public int getBalance() {
		return this.balance;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Basket getBasket() {
		return this.basket;
	}
	
	public void addFunds(int fund) {
		if(fund < 0) {
			throw new IllegalArgumentException();
		}
		this.balance += fund;
	}
	
	public void addToBasket(MarketProduct p) {
		basket.add(p);
	}
	
	public boolean removeFromBasket(MarketProduct p) {
		return basket.remove(p);
	}
	
	public String checkOut() {
		int value = basket.getSubTotal() + basket.getTotalTax();
		if (this.balance < value) {
			throw new IllegalStateException();
		}
		this.balance -= value;
		String s = basket.toString();
		basket.clear();
		return s;
	}
}