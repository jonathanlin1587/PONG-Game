/**
 * @author jonathanlin
 * CreditCard Class is one of the account Classes for VP Bank. 
 * 
 */
public class CreditCard extends Account{
	double balance;
	
	public CreditCard(double balance) {
		this.balance = balance;
	}
	
	public String toString() {
		return String.valueOf(this.balance);
	}

	public double getBalance() {
		return balance;
	}
	
	public void updateBalance(double balance) {
		this.balance += balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
