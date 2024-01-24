/**
 * @author jonathanlin
 * SavingAccount Class is one of the account Classes for VP Bank. 
 * 
 */
public class SavingAccount extends Account {
	private double balance;

	
	public SavingAccount(double balance) {
		this.balance = balance;
	}
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void updateBalance(double balance) {
		this.balance += balance;
	}

	public String toString() {
		return String.valueOf(this.balance);
	}
	
}
