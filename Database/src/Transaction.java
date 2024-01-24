/**
 * @author jonathanlin
 * Transaction Class is another Class for VP Bank. 
 * 
 * It contains the all the transaction information for each customer
 */
public class Transaction {
	private String SIN, date, type, amount, resultingBal;
	
	//Constructor
	public Transaction(String SIN, String date, String type, String amount, String resultingBal) {
		this.SIN = SIN;
		this.date = date;
		this.type = type;
		this.amount = amount;
		this.resultingBal = resultingBal;
	}

	public String getSIN() {
		return SIN;
	}
	public void setSIN(String sIN) {
		SIN = SIN;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getResultingBal() {
		return resultingBal;
	}
	public void setResultingBal(String resultingBal) {
		this.resultingBal = resultingBal;
	}
	public String toString() {
		String tType;
		if (type.equals("2")) {
			tType = "Deposit";
		}
		else if (type.equals("3")) {
			tType = "Withdraw";
		}
		else if (type.equals("4")) {
			tType = "Process Cheque";
		}
		else if (type.equals("5")) {
			tType = "Process Purchase";
		}
		else if (type.equals("6")) {
			tType = "Process Payment";
		}
		else {
			tType = "Transfer Funds";
		}
		
		return "\nDate: " + date + ", Transaction Type: " + tType + ", Amount: " + amount + ", Resulting Balance: " + resultingBal;
	}
}
