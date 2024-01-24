/**
 * @author jonathanlin
 * Customer Class is another Class for VP Bank. 
 * 
 * It contains all the information that a customer might have
 * and also the methods a customer needs to perform banking tasts
 */
import java.util.*;

public class Customer {
	private String firstName;
	private String lastName;
	private String SIN;
	private int birthYear;
	private int birthMonth;
	private int birthDay;
	private int age;

	private SavingAccount savingAccount;
	private ChequingAccount chequingAccount;
	private CreditCard creditCard;
	//private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	private Transaction[] transactions = new Transaction[5];

	//Default constructor
	public Customer() {
		
	}
	
	//Constructor
	public Customer(String lName, String fName, String sin, int bYear, int bMonth, int bDay, SavingAccount sAccount, ChequingAccount cAccount, CreditCard cCard, int age) {
		lastName = lName;
		firstName = fName;
		SIN = sin;
		birthYear = bYear;
		birthMonth = bMonth;
		birthDay = bDay;
		savingAccount = sAccount;
		chequingAccount = cAccount;
		creditCard = cCard;
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getSIN() {
		return SIN;
	}



	public void setSIN(String sIN) {
		SIN = sIN;
	}



	public int getBirthYear() {
		return birthYear;
	}



	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}



	public int getBirthMonth() {
		return birthMonth;
	}



	public void setBirthMonth(int birthMonth) {
		this.birthMonth = birthMonth;
	}



	public int getBirthDay() {
		return birthDay;
	}



	public void setBirthDay(int birthDay) {
		this.birthDay = birthDay;
	}


	public int getAge() {
		age = Bank.calcAge(String.valueOf(getBirthYear()), String.valueOf(getBirthMonth()), String.valueOf(getBirthDay()));
		return age;
	}

	
	
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	public SavingAccount getSavingAccount() {
		return savingAccount;
	}



	public void setSavingAccount(SavingAccount line) {
		this.savingAccount = line;
	}



	public ChequingAccount getChequingAccount() {
		return chequingAccount;
	}



	public void setChequingAccount(ChequingAccount chequingAccount) {
		this.chequingAccount = chequingAccount;
	}



	public CreditCard getCreditCard() {
		return creditCard;
	}



	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	
	
	public Transaction[] getTransactions() {
		return transactions;
	}

	
	
	public void setTransactions(Transaction[] transactions) {
		this.transactions = transactions;
	}
	
	
	/**
	 * When user performs a transaction, the customer's transaction list is updated
	 * 
	 * @param transaction		takes the transaction the user performed
	 * @return none
	 */
	public void updateTransaction(Transaction transaction) {
		if (transactions[4] != null) {
			for (int i = 0; i < transactions.length-1; i++) {
				transactions[i] = transactions[i+1];
			}
			transactions[4] = transaction;
		}
		else {
			for (int i = 0; i < transactions.length-1; i++) {
				if (transactions[i] == null) {
					transactions[i] = transaction;
					break;
				}
			}
		}
	}

	

	public String getName() {
		return (lastName.toUpperCase())+(firstName.toUpperCase());
	}
	
	
	/**
	 * Displays transactions when from the transaction list
	 * 
	 * @param none
	 * @return none
	 */
	public String displayTransactions() {
		boolean zeroTransactions = true;
		String str = "";
		
		for (int i = 0; i < transactions.length; i++) {
			if (transactions[i] != null) {
				zeroTransactions = false;
				str += transactions[i];
			}
			else {
				str += "";
			}
		}
		str += ", ";
		
		if (zeroTransactions == true) {
			str = "There are no transactions for this user.";
		}
		
		return str;
	}
	
	
	/**
	 * When program quits, it returns a string of all customer's transactions
	 * 
	 * @param none
	 * @return String		string that has all the transactions
	 */
	public String saveTransactions() {
		boolean zeroTransactions = true;
		String str = "";
		
		for (int i = 0; i < transactions.length; i++) {
			if (transactions[i] != null) {
				str += SIN + ", ";
				zeroTransactions = false;
				str += transactions[i].getDate();
				str += ", ";
				str += transactions[i].getType();
				str += ", ";
				str += transactions[i].getAmount();
				str += ", ";
				str += transactions[i].getResultingBal();
				str += "\n";
			}
		}
		if (zeroTransactions == true) {
			str = "";
		}
		return str;
	}
	
	
	
	public String toString() {
		String savingAcctBal, chequingAcctBal, creditCardBal;
		
		if (savingAccount == null) {
			savingAcctBal = "None";
		}
		else {
			savingAcctBal = savingAccount.toString();
		}
		
		if (chequingAccount == null) {
			chequingAcctBal = "None";
		}
		else {
			chequingAcctBal = chequingAccount.toString();
		}
		
		if (creditCard == null) {
			creditCardBal = "None";
		}
		else {
			creditCardBal = creditCard.toString();
		}
		
		return "\nLast name: " + lastName + ", First name: " + firstName + ", SIN#: " + SIN + ", Birth Year: " + birthYear + ", Birth Month: " + birthMonth + ", Birth Day: " + birthDay + ", Savings Account: " + savingAcctBal + ", Chequing Account: " + chequingAcctBal + ", Credit Card: " + creditCardBal;
	}
}
