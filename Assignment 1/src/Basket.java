
/*
 ===================================================================================
 Name: Melissa Hawley
 Student ID: 260730658
 ===================================================================================
 */

public class Basket {
	private MarketProduct[] marketProducts;
	
	public Basket() {
		this.marketProducts = new MarketProduct[0];
	}
	
	public MarketProduct[] getProducts() {
		int length = this.marketProducts.length;
		MarketProduct[] shallowCopy = new MarketProduct[length];
		for (int i = 0; i < length; i++) {
			shallowCopy[i] = this.marketProducts[i];
		}
		
		return shallowCopy;
	}
	
	public void add(MarketProduct p) {
		int length = this.marketProducts.length;
		MarketProduct[] clone = new MarketProduct[length+1];
		for (int i = 0; i < length; i++) {
			clone[i] = 	marketProducts[i];
		}
		clone[length] = p;
		this.marketProducts = clone;
	}
	
	public boolean remove(MarketProduct p) {
		boolean removed = false;
		int length = marketProducts.length;
		for (int i = 0; i < length; i++) {
			if (this.marketProducts[i].equals(p)) {
				MarketProduct[] clone = new MarketProduct[length-1];
				int j = 0;
				for (; j < i; j++) {
					clone[j] = this.marketProducts[j];
				}
				for (; j < length-1; j++) {
					clone[j] = this.marketProducts[j+1];
				}
				this.marketProducts = clone;
				removed = true;
				break;
			}
		}
		return removed;
	}
	
	public void clear() {
		this.marketProducts = new MarketProduct[0];
	}
	
	public int getNumOfProducts() {
		return this.marketProducts.length;
	}
	
	public int getSubTotal() {
		int r = 0;
		for (MarketProduct p:marketProducts) {
			r += p.getCost();
		}
		return r;
	}
	
	public int getTotalCost() {
		return this.getSubTotal() + this.getTotalTax();
	}
	
	public int getTotalTax() {
		int t = 0;
		double tax = 0;
		for (MarketProduct p:marketProducts) {
			if(p instanceof Jam) {
				tax = p.getCost()*0.15;
				t += tax;
			}
		}
		return t;
	}
	
	public String toString() {
		String s = "";

		for(MarketProduct p:marketProducts) {
			if (p.getCost() <= 0) {
				s += p.getName()+ "\t" + "-" + "\n";
			} else {
				double cost = (double)p.getCost()/100;
				s += p.getName()+ "\t" + Double.toString(cost)+"\n";
			}
		}
		s += "\n";
		
		double total = (double)getSubTotal()/100;
		
		double tax = (double)getTotalTax()/100;
		
		double subTotal = ((double)getTotalCost())/100;
		
		s += "Subtotal\t"+Double.toString(total)+"\n";
		s += "Total Tax\t"+Double.toString(tax)+"\n\n";
		s += "Total Cost\t"+Double.toString(subTotal)+"\n";
		return s;
	}
}

