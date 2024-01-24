/**
 * @author jonathanlin
 * Bank Class is Main Class for VP Bank. 
 * 
 * It contains the main method and all the banking activities
 * such as adding/removing customers, viewing customers, and making transactions.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
//import java.util.List;
import java.util.Scanner;
//import java.util.*;
import java.io.*;
import java.time.*;

public class Bank {
	
	public static ArrayList<Customer> customerList = new ArrayList<Customer>();
	public static ArrayList<Customer> sortedCustomerList = new ArrayList<Customer>();
	
	public static final int MAXCUSTOMERS = 50;
	public static Scanner sc = new Scanner(System.in);
	public static Scanner scan = new Scanner(System.in);
	public static String option;
	public static int optionInt, option2 = 0;
	public static int customerIndex;
	public static boolean run = true;
	public static boolean sorted = false;
	public static boolean inputAccepted = false;
	public static boolean custFound = false;
	public static String fName, lName;
	public static double savingsBalance, chequingBalance, creditBalance;
	
	public static String TTYPE_DEPOSIT = "2";
	public static String TTYPE_WITHDRAW = "3";
	public static String TTYPE_PROCESSCHEQUE = "4";
	public static String TTYPE_PROCESSPURCHASE = "5";
	public static String TTYPE_PROCESSPAYMENT = "6"; 
	public static String TTYPE_TRANSFER = "7";		
	
	public static void main(String[] args) {
		//System.out.println("Local date = " + LocalDate.now());
		loadCustomers();
		loadTransactions();
		while (run) {
			menuText();
			while (inputAccepted != true) {
				try {
					System.out.print("Select an option (enter #): ");
					option = scan.nextLine();
					optionInt = Integer.valueOf(option);
					inputAccepted = true;
				}
				
				catch(Exception e) {
					System.out.print("Please enter a valid input (# from 1-8): ");
					inputAccepted = false;
				}
			}
			menuSelection();
			saveCustomerInfo();
		}	
	}  
	
	/**
	 * Loads customers into arrayList from Users.txt file
	 * 
	 * @param none
	 * @return none
	 */
	public static void loadCustomers() {
		String fileName = "Users.txt"; 
		String tFileName = "Transactions.txt";
		int i;
		Customer customer = null;
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String line = in.readLine();
			
			i = 0;
			while (line != null) { // reading customer info from the file
				if (i%9 == 0) {
					customer = new Customer();
					
					customer.setLastName(line);
					line = in.readLine();
					i++;
					customer.setFirstName(line);
					line = in.readLine();
					i++;
					customer.setSIN(line);
					line = in.readLine();
					i++;
					customer.setBirthYear(Integer.parseInt(line));
					line = in.readLine();
					i++;
					customer.setBirthMonth(Integer.parseInt(line));
					line = in.readLine();
					i++;
					customer.setBirthDay(Integer.parseInt(line));
					line = in.readLine();
					i++;
					
					if (line.equals("none")) {
						customer.setSavingAccount(null);
					}
					else {
						customer.setSavingAccount(new SavingAccount(Double.parseDouble(line)));
					}
					line = in.readLine();
					i++;
					
					if (line.equals("none")) {
						customer.setChequingAccount(null);
					}
					else {
						customer.setChequingAccount(new ChequingAccount(Double.parseDouble(line)));
					}
					line = in.readLine();
					i++;
					
					if (line.equals("none")) {
						customer.setCreditCard(null);
					}
					else {
						customer.setCreditCard(new CreditCard(Double.parseDouble(line)));
					}
					line = in.readLine();
					i++;
					
					
				}
				customerList.add(customer);
			}
		}
		
		catch (IOException e) {
			 System.out.println("There has been an error in reading the file" + fileName);
		}
	}
	
	/**
	 * Displays the main menu text of VP bank
	 * 
	 * @param none
	 * @return none
	 */
	public static void menuText() {
		System.out.print("--------------------------------------------------\r\n"
				+ "MAIN MENU\r\n"
				+ "--------------------------------------------------\r\n" 
				+ "Please choose an action from the following:\r\n"
				+ "	1. Add Customer\r\n" 
				+ "	2. Delete Customer\r\n" 
				+ "	3. Sort customers by last name, first name\r\n"
				+ "	4. Sort customers by SIN\r\n"
				+ "	5. Display customer summary (name, SIN)\r\n"
				+ "	6. Find profile by last name, first name\r\n"
				+ "	7. Find profile by SIN\r\n"
				+ "	8. Quit\r\n"
				+ "--------------------------------------------------\n");	
	}
	
	/**
	 * Allows each of the selections in the menu to work.
	 * Different case for each option in the menu (leads into more methods)
	 * 
	 * @param none
	 * @return none
	 */
	public static void menuSelection() {
		switch(optionInt) {
		case 1: //1. Add a customer
			if (customerList.size() == MAXCUSTOMERS) { //checking to see if number of customers exceeds the maximum (50)
				System.out.println("You have reached the maximum number of customers allowed");
				break;
			}
			
			createCustomer();
			break;
			
		case 2: //2. Delete a customer
			String SINinput;
			System.out.print("Enter SIN number to remove: ");
			SINinput = scan.nextLine();
			
			removeCustomer(SINinput);
			break;
		
		case 3: //3. Sort customers by last name, first name
			sortCustomersByName();
			sorted = true;
			break;
			
		case 4: //4. Sort customers by SIN
			sortCustomersBySIN();
			sorted = true;
			break;
			
		case 5: //5. Display customer summary (name, SIN)
			displaySummary();
			break;
			
		case 6: //6. Find profile by last name, first name
			String nameInput;
			String fName;
			String lName;
			System.out.println("Enter Name to Search");
			System.out.print("Last name: ");
			lName = scan.nextLine();
			System.out.print("First name: ");
			fName = scan.nextLine();
			nameInput = (lName+fName).toUpperCase();
			
			findProfileByName(nameInput);
			
			if (custFound == true) {
				profileSelection();
			}
			else {
				System.out.println("No customer with that Name");
			}
				
			break;
			
		case 7: //7. Find profile by SIN
			String SINInput;
			System.out.print("Enter SIN to Search: ");
			SINInput = scan.nextLine();
			while (SINInput.length() != 9 || !isNumeric(SINInput)) {
				System.out.println("SIN must be 9 digits!");
				System.out.print("\nEnter 9 Digit SIN number: ");
				SINInput = scan.nextLine();
			}
			
			findProfileBySIN(SINInput);
		
			if (custFound == true) {
				profileSelection();
			}
			else {
				System.out.println("No customer with that SIN");
			}
			break;
			
		case 8: //8. Quit
			System.out.println("Thank you for using the VP bank"); 
			run = false;
			break;
		}
		
		inputAccepted = false;
		
		if (optionInt != 8) {
			System.out.print("\nPress enter to conitnue...");
			try {
				System.in.read();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	
	/**
	 * Checks if the String entered is numeric
	 * 
	 * @param str		the string that is tested to see if its a number or not
	 * @return none
	 */
	public static boolean isNumeric(String str) { 
		try {  
			Double.parseDouble(str);  
		    return true;
		} 
		
		catch(NumberFormatException e){  
		    return false;  
		}  
	}
	
	/**
	 * Creates a new customer to be added into the arrayList
	 * Also checks if user is over or under 18
	 * and if each input is of correct value
	 * 
	 * @param none
	 * @return none
	 */
	public static void createCustomer() {
		String fileName = "Users.txt"; //user txt file
		
		//variables
		String lastName;
		String firstName;
		String SIN = "";
		String birthYear = null;
		String birthMonth = null;
		String birthDay = null;
		String savings = "none";
		String chequing = "none";
		String credit = "none";
		int age;
		
		//assigning user input to these variables
		System.out.print("Enter last name: ");
		lastName = scan.nextLine().toUpperCase();
		System.out.print("Enter first name: ");
		firstName = scan.nextLine().toUpperCase();
		System.out.print("Enter 9 Digit SIN number: ");
		SIN = scan.nextLine();
		checkDuplicate(SIN);
		while (SIN.length() != 9 || !isNumeric(SIN)) {
			System.out.println("SIN must be 9 digits!");
			System.out.print("\nEnter 9 Digit SIN number: ");
			SIN = scan.nextLine();
			while (checkDuplicate(SIN) == true) {
				System.out.println("SIN already exists");
				System.out.print("\nEnter 9 Digit SIN number: ");
				SIN = scan.nextLine();
			}
		}
		while (checkDuplicate(SIN) == true) {
			System.out.println("SIN already exists");
			System.out.print("\nEnter 9 Digit SIN number: ");
			SIN = scan.nextLine();
			while (SIN.length() != 9) {
				System.out.println("SIN must be 9 digits!");
				System.out.print("\nEnter 9 Digit SIN number: ");
				SIN = scan.nextLine();
			}
		}
		
		age = -1;
		while(age == -1) {
			System.out.print("Enter birth year: ");
			birthYear = scan.nextLine();
			System.out.print("Enter birth month: ");
			birthMonth = scan.nextLine();
			System.out.print("Enter birth day: ");
			birthDay = scan.nextLine();
			
			age = calcAge(birthYear, birthMonth, birthDay);
		}
			
		
		if (age >= 18) {
		
			System.out.print("Enter savings account balance: ");
			savings = scan.nextLine();
			System.out.print("Enter chequing account balance: ");
			chequing = scan.nextLine();
			System.out.print("Enter credit card balance: ");
			credit = scan.nextLine();
		}
		
		else if (age < 18) {
			System.out.print("Enter savings account balance: ");
			savings = scan.nextLine();
		}
		
		if ((savings.equals("none") && chequing.equals("none") && credit.equals("none")) || (savings.equals("") && chequing.equals("") && credit.equals(""))) {
			System.out.println("\nYou must have at least one account!");
		}
		
		else {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
				writer.write(lastName);
				writer.newLine();
				writer.write(firstName);
				writer.newLine();
				writer.write(SIN);
				writer.newLine();
				writer.write(birthYear);
				writer.newLine();
				writer.write(birthMonth);
				writer.newLine();
				writer.write(birthDay);
				writer.newLine();
				writer.write(savings);
				writer.newLine();
				writer.write(chequing);
				writer.newLine();
				writer.write(credit);
				writer.newLine();
				writer.write("");
				
				writer.close();
			}
			
			catch (IOException e) {
				 System.out.println("There has been an error in writing the file" + fileName);
			}
			
			
			SavingAccount sAccount;
			ChequingAccount cAccount;
			CreditCard cCard;
			
			Customer customer;
			
			//checking if any account doesn't exist
			if (savings.equals("none") || savings.equals("")) {
				sAccount = null;
			}
			else {
				sAccount = new SavingAccount(Double.parseDouble(savings));
			}
			
			if (chequing.equals("none") || chequing.equals("")) {
				cAccount = null;
			}
			else {
				cAccount = new ChequingAccount(Double.parseDouble(chequing));
			}
			
			if (credit.equals("none") || credit.equals("")) {
				cCard = null;
			}
			else {
				cCard = new CreditCard(Double.parseDouble(credit));
			}
			
			//customer class constructor
			customer = new Customer(lastName, firstName, SIN, Integer.parseInt(birthYear), Integer.parseInt(birthMonth), Integer.parseInt(birthDay), sAccount, cAccount, cCard, age);
			//adding each customer into an arrayList
			customerList.add(customer);
		}
	}
	
	/**
	 * Checking if the user entered any duplicate values (based on SIN)
	 * 
	 * @param SIN 				the SIN number that is referenced and checked against all other SIN numbers in the Database
	 * @return isDuplicate		if it is a duplicate or not (true or false)
	 */
	public static boolean checkDuplicate(String SIN) {
		boolean isDuplicate = false;
		
		for (int i = 0; i < customerList.size(); i ++) { //looping through all the elements in customerList
			if (SIN.equals(customerList.get(i).getSIN())) { //comparing the reference SIN with all the other SIN
				isDuplicate = true;
			}
			else {
				isDuplicate = false;
			}
		}
		return isDuplicate;
	}

	
	/**
	 * Calculates how old a user is
	 * Also makes sure the birth date is a date before the current date
	 * 
	 * @param year, month, day		the value for the user's birthday (year, month, and day)
	 * @return age					the calculated age
	 */
	public static int calcAge(String year, String month, String day) {
		int age = -1;
		int y, m, d;
		LocalDate dob, curDate;
		
		try {
			y = Integer.parseInt(year); //parsing the Strings into ints
			m = Integer.parseInt(month);
			d = Integer.parseInt(day);
			
			dob = LocalDate.of(y, m, d); //creating a date using the info
	        curDate = LocalDate.now(); // creating a date using today's date
	        Period period = Period.between(dob, curDate); //comparing the time between both dates
	
	        age = period.getYears(); //changing that time into years and setting age to that
	        
			if (dob.compareTo(curDate) > 0) { //if user's dob is smaller than today's date
				System.out.println("Birthdate is greater than today's Date");
				age = -1;
			}
		}
		
		catch(Exception e) {
			System.out.println("Please enter Valid Date of Birth");
		}
		
        return age;
    }
	
	/**
	 * Removes customer from the arrayList
	 * finds SIN and removes that customer
	 * 
	 * @param SINnumber		the SIN number to search for and delete
	 * @return none
	 */
	public static void removeCustomer(String SINnumber) {
		String SINholder; //temp holder for the SIN found
		for (int i = 0; i < customerList.size(); i++) { //looping through arrayList
			SINholder = customerList.get(i).getSIN( ); //getting SIN
			if (SINholder.equals(SINnumber)) { //checking if the SIN found is equal to the SIN we're looking for
				System.out.println("\nCustomer " + customerList.get(i).getName() + " removed"); //displaying who is being removed
				customerList.remove(i); //removing that customer
			}
		}
	}
	
	/**
	 * Saving customer info and transactions into Users.txt and Transactions.txt for next time running the program
	 * 
	 * @param none
	 * @return none
	 */
	public static void saveCustomerInfo() {
		File file = new File("Users.txt"); //txt files
		File transactionFile = new File("Transactions.txt");
		file.delete(); //clearing the old info
		transactionFile.delete();
		
		try { //rewriting the information in the arrayLists
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true)); //writer for the customer info
			BufferedWriter transactionWriter = new BufferedWriter(new FileWriter(transactionFile, true)); //writer for transaction info
			for (int i = 0; i < customerList.size(); i++) {
				writer.write(customerList.get(i).getFirstName());
				writer.newLine();
				writer.write(customerList.get(i).getLastName());
				writer.newLine();
				writer.write(customerList.get(i).getSIN());
				writer.newLine();
				writer.write(String.valueOf(customerList.get(i).getBirthYear()));
				writer.newLine();
				writer.write(String.valueOf(customerList.get(i).getBirthMonth()));
				writer.newLine();
				writer.write(String.valueOf(customerList.get(i).getBirthDay()));
				writer.newLine();
				
				if (customerList.get(i).getSavingAccount() != null) {
					writer.write(customerList.get(i).getSavingAccount().toString());
					writer.newLine();
				}
				else {
					writer.write("none");
					writer.newLine();
				}
				
				if (customerList.get(i).getChequingAccount() != null) {
					writer.write(customerList.get(i).getChequingAccount().toString());
					writer.newLine();
				}
				else {
					writer.write("none");
					writer.newLine();
				}
				
				if (customerList.get(i).getCreditCard() != null) {
					writer.write(customerList.get(i).getCreditCard().toString());
					writer.newLine();
				}
				else {
					writer.write("none");
					writer.newLine();
				}
				
				transactionWriter.write(customerList.get(i).saveTransactions());
			}
			writer.close();
			transactionWriter.close();
		}
		catch (IOException e) {
			 System.out.println("There has been an error in writing the file" + file);
		}
	}
	
	/**
	 * Sorting the customerList array by last name, first name
	 * 
	 * @param none
	 * @return none
	 */
	public static void sortCustomersByName() {
		ArrayList<String> names = new ArrayList<String>(); //arrayList of the names
		sortedCustomerList = new ArrayList<Customer>(); //sorted customer arrayList
		
		for (int i = 0; i < customerList.size(); i++) {
			names.add(customerList.get(i).getName()); //getting all the names and populating the names arrayList
		}
		
		Collections.sort(names); //sorting the names
		
		System.out.println("Customers Sorted by Last Name, First Name");
		
		for (int i = 0; i < names.size(); i++) { //modified selection sort to sort names to populate the sorted customer arrayList
			for (int j = 0; j < customerList.size(); j++) { 
				customerList.get(i).getName();
				if (names.get(i).equals(customerList.get(j).getName())) {
					sortedCustomerList.add(customerList.get(j));
				}
			}
		}
	}
		
	
	/**
	 * Sorting the customerList array by SIN
	 * 
	 * @param none
	 * @return none
	 */
	public static void sortCustomersBySIN() {
		ArrayList<String> sinNumbers = new ArrayList<String>(); //arrayList of the SIN numbers
		sortedCustomerList = new ArrayList<Customer>(); //sorted customer arrayList
		
		for (int i = 0; i < customerList.size(); i++) { 
			sinNumbers.add(customerList.get(i).getSIN()); //getting all the SIN numbers and populating the names arrayList
		}
		
		Collections.sort(sinNumbers);
		
		System.out.println("Customers Sorted by SIN number");
		
		for (int i = 0; i < sinNumbers.size(); i++) { //modified selection sort to sort SIN to populate the sorted customer arrayList
			for (int j = 0; j < customerList.size(); j++) { 
				customerList.get(i).getSIN();
				if (sinNumbers.get(i).equals(customerList.get(j).getSIN())) {
					sortedCustomerList.add(customerList.get(j));
				}
			}
		}
	}
	
	
	/**
	 * Displaying all the user info and account balances (summary)
	 * 
	 * @param none
	 * @return none
	 */
	public static void displaySummary() {
		if (sorted == true) {
			System.out.println("\nHere is the Customer Summary After Sort: ");
			//printing out sorted customers
			for (int i = 0; i < sortedCustomerList.size(); i++) {
				System.out.println(sortedCustomerList.get(i));
			}
		}
		else {
			System.out.println("\nHere is the Customer Summary Without Sort: ");
			for (int i = 0; i < customerList.size(); i++) {
				System.out.println(customerList.get(i));
			}
		}
	}

	
	/**
	 * Finding profile by name (last name and first name)
	 * 
	 * @param name		the name that needs to be found	
	 * @return none
	 */
	public static void findProfileByName(String name) {
		int index = -1;
		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getName().equals(name)) { //finding the customer that matches the name
				index = i;
				customerIndex = i; //setting customerIndex to the customer found 
			}
		}
		
		if (index != -1) {
			custFound = true;
		}
 		
		fName = customerList.get(customerIndex).getFirstName(); //setting first name
		lName = customerList.get(customerIndex).getLastName(); //setting last name
	}
	
	/**
	 * Finding profile by SIN
	 * 
	 * @param SIN			the SIN that needs to be found	
	 * @return none
	 */

	public static void findProfileBySIN(String SIN) { 
		int index = -1;
		
		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getSIN().equals(SIN)) {
				index = i;
				customerIndex = i; //setting customerIndex to the customer found 
			}
		}
		
		if (index != -1) {
			custFound = true;
		}

		fName = customerList.get(customerIndex).getFirstName(); //setting first name
		lName = customerList.get(customerIndex).getLastName(); //setting last name
	}

	
	
	/**
	 * Displays the profile menu text of VP bank
	 * After user goes into case 6 or 7 (find user by...)
	 * 
	 * @param none
	 * @return none
	 */
	public static void profileMenu() {
		String sBal, cBal, ccBal;
		
		if (customerList.get(customerIndex).getSavingAccount() == null) {
			sBal = "None";
		}
		else {
			sBal = customerList.get(customerIndex).getSavingAccount().toString();
		}
		
		if (customerList.get(customerIndex).getChequingAccount() == null) {
			cBal = "None";
		}
		else {
			cBal = customerList.get(customerIndex).getChequingAccount().toString();
		}
		
		if (customerList.get(customerIndex).getCreditCard() == null) {
			ccBal = "None";
		}
		else {
			ccBal = customerList.get(customerIndex).getCreditCard().toString();
		}
		
		
		System.out.print("\n--------------------------------------------------\n"
				+ fName + " " + lName + "'S "
				+ "PROFILE MENU\n"
				+ "--------------------------------------------------\n"
				+ "Savings Account Balance: " + sBal + "\n"
				+ "Chequing Account Balance: " + cBal + "\n"
				+ "Credit Card Balance: " + ccBal + "\n"
				+ "--------------------------------------------------\n"
				+ "	1. View account activity\n" 
				+ "	2. Deposit\n" 
				+ "	3. Withdraw\n"
				+ "	4. Process cheque\n"
				+ "	5. Process purchase\n"
				+ "	6. Process payment for credit card\n"
				+ "	7. Transfer funds\n"
				+ "	8. Open account or issue card\n"
				+ "	9. Cancel account or card\n"
				+ "	10. Return to main menu\n"
				+ "--------------------------------------------------\n");	
	}
		
	
	
	/**
	 * Allows each of the selections in the profile menu to work.
	 * Different if or else if for each option in the menu (leads into more methods)
	 * 
	 * @param none
	 * @return none
	 */
	public static void profileSelection() {
		int choice;
		double amount = 0;
		Customer tempCust = customerList.get(customerIndex);
		String tempBalance = "";
		
		while (option2 != 10) {
			profileMenu();
			System.out.print("Select an option (enter #): ");
			option2 = sc.nextInt();
		
			if (option2 == 1) {
				System.out.println("Viewing last 5 transactions");
				System.out.println(tempCust.displayTransactions());
			}
			
			else if (option2 == 2) {
				System.out.println("Deposit into which account? (Enter 1 or 2)\n(1) Savings Account\n(2) Chequing Account");
				choice = sc.nextInt();
				
				if (choice == 1) {
					System.out.println("Savings Account Balance: " + tempCust.getSavingAccount());
					System.out.print("How much money would you like to deposit: ");
					amount = sc.nextDouble();
					tempCust.getSavingAccount().updateBalance(amount);
					System.out.println("New Savings Account Balance: " + tempCust.getSavingAccount());
					tempBalance = String.valueOf(tempCust.getSavingAccount());
				}
				
				else if (choice == 2) {
					if (customerList.get(customerIndex).getAge() >= 18) {
						if (customerList.get(customerIndex).getChequingAccount() != null) {
							System.out.println("Chequing Account Balance: " + tempCust.getChequingAccount());
							System.out.print("How much money would you like to deposit: ");
							amount = sc.nextDouble();
							tempCust.getChequingAccount().updateBalance(amount);
							System.out.println("New Chequing Account Balance: " + tempCust.getChequingAccount());
							tempBalance = String.valueOf(tempCust.getChequingAccount());
						}
						else {
							System.out.println("You don't have a chequing account");
						}
					}
					else {
						System.out.println("You don't have a chequing account because you are under 18");
					}
				}
				
				else {
					System.out.println("Please enter valid option!");
				}
				
				Transaction transaction = new Transaction(tempCust.getSIN(), LocalDate.now().toString(), TTYPE_DEPOSIT, String.valueOf(amount), tempBalance);
				tempCust.updateTransaction(transaction);
			}
			
			else if (option2 == 3) {
				System.out.println("Withdraw from which account? (Enter 1, 2, or 3)\n(1) Savings Account\n(2) Chequing Account\n(3) Credit Card");
				choice = sc.nextInt();
				if (choice == 1) {
					System.out.println("Savings Account Balance: " + tempCust.getSavingAccount());
					System.out.print("How much money would you like to withdraw: ");
					amount = 0 - (sc.nextDouble());
					tempCust.getSavingAccount().updateBalance(amount);
					System.out.println("New Savings Account Balance: " + tempCust.getSavingAccount());
					tempBalance = String.valueOf(tempCust.getSavingAccount());
				}
				
				else if (choice == 2) {
					if (customerList.get(customerIndex).getAge() >= 18) {
						if (customerList.get(customerIndex).getChequingAccount() != null) {
							System.out.println("Chequing Account Balance: " + tempCust.getChequingAccount());
							System.out.print("How much money would you like to withdraw: ");
							amount = 0 - (sc.nextDouble());
							tempCust.getChequingAccount().updateBalance(amount);
							System.out.println("New Chequing Account Balance: " + tempCust.getChequingAccount());
							tempBalance = String.valueOf(tempCust.getChequingAccount());
						}
						else {
							System.out.println("You don't have a chequing account");
						}
					}
					else {
						System.out.println("You don't have a chequing account because you are under 18");
					}
				}
				
				else if (choice == 3) {
					if (customerList.get(customerIndex).getAge() >= 18) {
						if (customerList.get(customerIndex).getCreditCard() != null) {
							System.out.println("Credit Card Balance: " + tempCust.getCreditCard());
							System.out.print("How much money would you like to withdraw: ");
							amount = 0 - (sc.nextDouble());
							tempCust.getCreditCard().updateBalance(amount);
							System.out.println("New Credit Card Balance: " + tempCust.getCreditCard());
							tempBalance = String.valueOf(tempCust.getCreditCard());
						}
						else {
							System.out.println("You don't have a credit card");
						}
					}
					else {
						System.out.println("You don't have a credit card because you are under 18");
					}
				}
				
				else {
					System.out.println("Please enter valid option!");
				}
				
				Transaction transaction = new Transaction(tempCust.getSIN(), LocalDate.now().toString(), TTYPE_WITHDRAW, String.valueOf(amount), tempBalance);
				tempCust.updateTransaction(transaction);
			}
			
			else if (option2 == 4) {
				System.out.println("Chequing Account Balance: " + tempCust.getChequingAccount());
				System.out.print("How much is the cheque that is being processed: ");
				amount = 0 - (sc.nextDouble());
				if (amount >= 1000) {
					tempCust.getChequingAccount().updateBalance(amount);
				}
				else {
					tempCust.getChequingAccount().updateBalance(amount - 0.15);
				}
				System.out.println("New Chequing Account Balance: " + tempCust.getChequingAccount());
				tempBalance = String.valueOf(tempCust.getChequingAccount());
				
				Transaction transaction = new Transaction(tempCust.getSIN(), LocalDate.now().toString(), TTYPE_PROCESSCHEQUE, String.valueOf(amount), tempBalance);
				tempCust.updateTransaction(transaction);
			}
			
			else if (option2 == 5) {
				System.out.println("Credit Card Balance: " + tempCust.getCreditCard());
				System.out.print("Credit Card purchase amount: ");
				amount = 0 - (sc.nextDouble());
				tempCust.getCreditCard().updateBalance(amount);
				System.out.println("New Credit Card Balance: " + tempCust.getCreditCard());
				tempBalance = String.valueOf(tempCust.getCreditCard());
				
				Transaction transaction = new Transaction(tempCust.getSIN(), LocalDate.now().toString(), TTYPE_PROCESSPURCHASE, String.valueOf(amount), tempBalance);
				tempCust.updateTransaction(transaction);
			}
			
			else if (option2 == 6) {
				System.out.print("How much is the Credit Card bill: ");
				amount = 0 - (sc.nextDouble());
				System.out.println("Make Credit Card payment with which account? (Enter 1 or 2)\n(1) Savings Account\n(2) Chequing Account");
				choice = sc.nextInt();
				
				if (choice == 1) {
					tempCust.getSavingAccount().updateBalance(amount);
					System.out.println("New Savings Account Balance: " + tempCust.getSavingAccount());
					tempCust.getCreditCard().updateBalance(-amount);
					System.out.println("New Credit Card Balance: " + tempCust.getCreditCard());
					tempBalance = String.valueOf(tempCust.getCreditCard());
				}
				
				else if (choice == 2) {
					tempCust.getChequingAccount().updateBalance(amount);
					System.out.println("New Chequing Account Balance: " + tempCust.getChequingAccount());
					tempCust.getCreditCard().updateBalance(-amount);
					System.out.println("New Credit Card Balance: " + tempCust.getCreditCard());
					tempBalance = String.valueOf(tempCust.getCreditCard());
				}
				
				else {
					System.out.println("Please enter valid option!");
				}
				
				Transaction transaction = new Transaction(tempCust.getSIN(), LocalDate.now().toString(), TTYPE_PROCESSPAYMENT, String.valueOf(amount), tempBalance);
				tempCust.updateTransaction(transaction);
			}
			
			else if (option2 == 7) {
				System.out.print("How much would you like to transfer: ");
				amount = 0 - (sc.nextDouble());
				System.out.println("From which account to which account? (Enter 1 or 2)\n(1) Savings Account --> Chequing Account\n(2) Chequing Account --> Savings Account");
				choice = sc.nextInt();
				
				if (choice == 1) {
					tempCust.getSavingAccount().updateBalance(amount);
					System.out.println("New Savings Account Balance: " + tempCust.getSavingAccount());
					tempCust.getChequingAccount().updateBalance(-amount);
					System.out.println("New Chequing Account Balance: " + tempCust.getChequingAccount());
					tempBalance = String.valueOf(tempCust.getChequingAccount());
				}
				
				else if (choice == 2) {
					tempCust.getChequingAccount().updateBalance(amount);
					System.out.println("New Chequing Account Balance: " + tempCust.getChequingAccount());
					tempCust.getSavingAccount().updateBalance(-amount);
					System.out.println("New Savings Account Balance: " + tempCust.getSavingAccount());
					tempBalance = String.valueOf(tempCust.getSavingAccount());
				}
				
				else {
					System.out.println("Please enter valid option!");
				}
				
				Transaction transaction = new Transaction(tempCust.getSIN(), LocalDate.now().toString(), TTYPE_TRANSFER, String.valueOf(amount), tempBalance);
				tempCust.updateTransaction(transaction);
			}
			
			else if (option2 == 8) {
				System.out.println("Which account/card would you like to open: (Enter 1, 2, or 3)\n(1) Savings Account\n(2) Chequing Account\n(3) Credit Card");
				choice = sc.nextInt();
				
				if (choice == 1) {
					if (tempCust.getSavingAccount() != null) {
						System.out.println("You already have a savings account!");
					}
					else {
						System.out.print("How much money would you like to add: ");
						amount = sc.nextDouble();
						SavingAccount sAccount = new SavingAccount(amount);
						tempCust.setSavingAccount(sAccount);
						System.out.println("New Savings Account Balance: " + tempCust.getSavingAccount());
					}
				}
				else if (choice == 2) {
					if (tempCust.getChequingAccount() != null) {
						System.out.println("You already have a chequing account!");
					}
					else {
						if (customerList.get(customerIndex).getAge() >= 18) {
							System.out.print("How much money would you like to add: ");
							amount = sc.nextDouble();
							ChequingAccount cAccount = new ChequingAccount(amount);
							tempCust.setChequingAccount(cAccount);
							System.out.println("New Chequing Account Balance: " + tempCust.getChequingAccount());
						}
						else {
							System.out.println("You are not old enough to have a chequing account");
						}
					}
				}
				else if (choice == 3) {
					if (tempCust.getCreditCard() != null) {
						System.out.println("You already have a Credit Card!");
					}
					else {
						if (customerList.get(customerIndex).getAge() >= 18) {
							System.out.print("How much money would you like to add: ");
							amount = sc.nextDouble();
							CreditCard cCard = new CreditCard(amount);
							tempCust.setCreditCard(cCard);
							System.out.println("New Credit Card Balance: " + tempCust.getCreditCard());
						}
						else {
							System.out.println("You are not old enough to have a chequing account");
						}
					}
				}
			}
			
			else if (option2 == 9) {
				System.out.println("Which account/card would you like to cancel: (Enter 1, 2, or 3)\n(1) Savings Account\n(2) Chequing Account\n(3) Credit Card");
				choice = sc.nextInt();
				
				if (choice == 1) {
					if (tempCust.getSavingAccount() == null) {
						System.out.println("You don't have a savings account to cancel!");
					}
					else {
						System.out.println("Set Saving to null" + " SIN NUMBER: " + tempCust.getSIN());
						tempCust.setSavingAccount(null);
					}
				}
				else if (choice == 2) {
					if (tempCust.getChequingAccount() == null) {
						System.out.println("You don't have a chequing account to cancel!");
					}
					else {
						System.out.println("Set Chequing to null." + " SIN NUMBER: " + tempCust.getSIN());
						tempCust.setChequingAccount(null);
					}
				}
				else if (choice == 3) {
					if (tempCust.getCreditCard() == null) {
						System.out.println("You don't have a credit card to cancel!");
					}
					else {
						if (tempCust.getCreditCard().getBalance() < 0) {
							System.out.println("You have an outstanding Credit Card Balance! Credit Card cannot be cancelled");
						}
						else {
							System.out.println("Set Credit to null" + " SIN NUMBER: " + tempCust.getSIN());
							tempCust.setCreditCard(null);
						}
					}
				}
				else {
					System.out.println("Enter a valid option!");
				}
				
				if ((tempCust.getSavingAccount() == null) && (tempCust.getChequingAccount() == null) && (tempCust.getCreditCard() == null)) {
					customerList.remove(customerIndex);
					System.out.println("Customer " + tempCust.getName() + " removed due to no active accounts.");
					break;
				}
			}
			
			
			if (option2 != 10) {
				System.out.print("\nPress enter to continue...");
				try {
					System.in.read();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		} //closing while loop
		if (option2 == 10) {
			option2 = 0;
			inputAccepted = false;
		}
		
	}

	
	
	/**
	 * Loads transactions into arrayList from Transactions.txt file
	 * 
	 * @param none
	 * @return none
	 */
	public static void loadTransactions() {
		String transactionFile = "Transactions.txt";
		ArrayList<String> transactionDetails;
		Transaction tempTransaction;
		String prevSIN, tempSIN, tempDate, tempType, tempAmt, tempResultingBal;
		
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(transactionFile));
			String line = in.readLine();
			
			while (line != null) {

				transactionDetails = new ArrayList<>(Arrays.asList(line.split(",")));
				
				tempSIN = transactionDetails.get(0).toString();
				prevSIN = tempSIN;
				tempDate = transactionDetails.get(1).toString();
				tempType = transactionDetails.get(2).toString();
				tempAmt = transactionDetails.get(3).toString();
				tempResultingBal = transactionDetails.get(4).toString();
				
				tempTransaction = new Transaction(tempSIN, tempDate, tempType, tempAmt, tempResultingBal);
				
				findProfileBySIN(tempSIN);
				
				customerList.get(customerIndex).updateTransaction(tempTransaction);				
				
				line = in.readLine();
			}
		}
		
		catch (IOException e) {
			 System.out.println("There has been an error in reading the file" + transactionFile);
		}
		
		
	}
}

