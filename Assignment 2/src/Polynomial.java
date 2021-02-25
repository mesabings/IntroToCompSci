package a2template;

import java.awt.HeadlessException;
import java.math.BigInteger;

public class Polynomial 
{
	private SLinkedList<Term> polynomial;
	public int size()
	{	
		return polynomial.size();
	}
	private Polynomial(SLinkedList<Term> p)
	{
		polynomial = p;
	}
	
	public Polynomial()
	{
		polynomial = new SLinkedList<Term>();
	}
	
	// Returns a deep copy of the object.
	public Polynomial deepClone()
	{	
		return new Polynomial(polynomial.deepClone());
	}
	
	/* 
	 * TODO: Add new term to the polynomial. Also ensure the polynomial is
	 * in decreasing order of exponent.
	 */
	public void addTerm(Term t)
	{	
		
		// Hint: Notice that the function SLinkedList.get(index) method is O(n), 
		// so if this method were to call the get(index) 
		// method n times then the method would be O(n^2).
		// Instead, use a Java enhanced for loop to iterate through 
		// the terms of an SLinkedList.
		
		int i = 0;
		for (Term currentTerm: polynomial)
		{
			// The for loop iterates over each term in the polynomial!!
			if(currentTerm.getExponent() == t.getExponent()) {
				if (currentTerm.getCoefficient().compareTo(t.getCoefficient().negate())==0) {
					polynomial.remove(i);
				} else {
					currentTerm.setCoefficient(currentTerm.getCoefficient().add(t.getCoefficient()));
				}
				return;
			}
			if (currentTerm.getExponent() < t.getExponent()) {
				polynomial.add(i, t);
				return;
			}
			i++;
		}
		addTermLast(t);
		
		
	}
	
	public Term getTerm(int index)
	{
		return polynomial.get(index);
	}
	
	//TODO: Add two polynomial without modifying either
	public static Polynomial add(Polynomial p1, Polynomial p2)
	{
		Polynomial r = new Polynomial();
		Polynomial c1 = p1.deepClone();
		Polynomial c2 = p2.deepClone();


		while (c1.size()>0 && c2.size()>0) {
			Term t1 = c1.getTerm(0);
			Term t2 = c2.getTerm(0);
			if (t1.getExponent() == t2.getExponent()) {
				t1.setCoefficient(t1.getCoefficient().add(t2.getCoefficient()));
				r.polynomial.addLast(t1);
				c1.polynomial.remove(0);
				c2.polynomial.remove(0);
			} else if (t1.getExponent() > t2.getExponent()) {
				r.polynomial.addLast(t1);
				c1.polynomial.remove(0);

			} else {
				r.polynomial.addLast(t2);
				c2.polynomial.remove(0);
			}
		}

		if (c1.size() == 0) {
			while (c2.size() != 0) {
				r.polynomial.addLast(c2.getTerm(0));
				c2.polynomial.remove(0);
			}
		}
		if (c2.size() == 0) {
			while (c1.size() != 0) {
				r.polynomial.addLast(c1.getTerm(0));
				c1.polynomial.remove(0);
			}
		}
		return r;
	}
	
	//TODO: multiply this polynomial by a given term.
	private void multiplyTerm(Term t)
	{	
		for (Term term: polynomial)
		{
			term.setCoefficient(t.getCoefficient().multiply(term.getCoefficient()));
			term.setExponent(t.getExponent()+term.getExponent());
		}
		
	}
	
	//TODO: multiply two polynomials
	public static Polynomial multiply(Polynomial p1, Polynomial p2)
	{
		Polynomial r = new Polynomial();
		for (Term term: p2.polynomial)
		{
			Polynomial m = p1.deepClone();
			m.multiplyTerm(term);
			r = add(m, r);
		} 
		
		return r;
	}
	
	//TODO: evaluate this polynomial.
	// Hint:  The time complexity of eval() must be order O(m), 
	// where m is the largest degree of the polynomial. Notice 
	// that the function SLinkedList.get(index) method is O(m), 
	// so if your eval() method were to call the get(index) 
	// method m times then your eval method would be O(m^2).
	// Instead, use a Java enhanced for loop to iterate through 
	// the terms of an SLinkedList.

	public BigInteger eval(BigInteger x)
	{
		BigInteger r = new BigInteger("0");
		int ei = 0;
		int i = 0;
		for (Term t: polynomial)
		{
			if(i==0) {
				r = polynomial.get(0).getCoefficient();
				ei = polynomial.get(0).getExponent();
			} else {
				for (int j=ei; j> t.getExponent(); j--) {
					r = r.multiply(x);
				}
				r = r.add(t.getCoefficient());
				ei = t.getExponent();
			}
			i++;
		}
		for (int j=ei; j> 0; j--) {
			r = r.multiply(x);
		}
		return r;
	}
	
	// Checks if this polynomial is same as the polynomial in the argument
	public boolean checkEqual(Polynomial p)
	{	
		if (polynomial == null || p.polynomial == null || size() != p.size())
			return false;
		
		int index = 0;
		for (Term term0 : polynomial)
		{
			Term term1 = p.getTerm(index);
			
			if (term0.getExponent() != term1.getExponent() ||
				term0.getCoefficient().compareTo(term1.getCoefficient()) != 0 || term1 == term0)
					return false;
			
			index++;
		}
		return true;
	}
	
	// This method blindly adds a term to the end of LinkedList polynomial. 
	// Avoid using this method in your implementation as it is only used for testing.
	public void addTermLast(Term t)
	{	
		polynomial.addLast(t);
	}
	
	// This is used for testing multiplyTerm
	public void multiplyTermTest(Term t)
	{
		multiplyTerm(t);
	}
	
	@Override
	public String toString()
	{	
		if (polynomial.size() == 0) return "0";
		return polynomial.toString();
	}
}
