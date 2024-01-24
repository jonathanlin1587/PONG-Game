/**
 * @author jonathanlin
 * ChequingAccount Class is one of the account Classes for VP Bank. 
 * 
 */
public class ChequingAccount extends Account{
	private double balance;
	
	public ChequingAccount(double balance) {
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
