package atm;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

public class OptionMenu extends Account {
		Scanner menuInput = new Scanner(System.in);
		DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");
		
		// Data structures for customers
		HashMap<Integer, Integer> data = new HashMap<Integer, Integer>(); // CustomerNumber -> PIN
		HashMap<Integer, String> names = new HashMap<Integer, String>(); // CustomerNumber -> Name

		// --- Main menu ---
		public void startATM() throws IOException {
			while(true) {
				 System.out.println("==================================");
		         System.out.println("   Welcome to the ATM Project");
		         System.out.println("==================================");
		         System.out.println("1 - Login");
		         System.out.println("2 - Register New Account");
		         System.out.println("3 - Exit");
		         System.out.print("Choice: ");
		         
		         int choice = menuInput.nextInt();
		         
		         switch (choice) {
		         case 1:
		        	 getLogin();
		        	 break;
		        	 
		         case 2:
		        	 registerAccount();
		        	 break;
		        	 
		         case 3:
		        	 System.out.println("Thank you for using ATM. Goodbye!");
		        	 System.exit(0);
		        	 
		        default:
		        	System.out.println("Invalid choice.\n");
		         }
			}
		}
		
		// --- Register new account ---
		public void registerAccount() {
			System.out.println("\n===== Register New Account =====");
			
			System.out.println("Enter a Customer Number (unique): ");
			int customerNo = menuInput.nextInt();
			
			if (data.containsKey(customerNo)) {
				System.out.println("❌ This Customer Number already exists. Try another.\n");
	            return;
			}
			
			System.out.println("Enter a 4-6 digit PIN");
			int pin = menuInput.nextInt();
			
			menuInput.nextLine();
			System.out.println("Enter Your Full Name");
			String name = menuInput.nextLine();
			
			data.put(customerNo, pin);
			names.put(customerNo, name);
			
			System.out.println("✅ Account Created Successfully!");
	        System.out.println("Customer No: " + customerNo);
	        System.out.println("Name: " + name + "\n");
		}
		
		// --- Login account ---
		public void getLogin() throws IOException{
			//demo accounts
			int x = 1;
			do {
				try {
					data.put(952141, 191904); //roman
					data.put(989947, 71976); //shabaz
					data.put(757525, 2525); //waqas
					data.put(656532, 8965); //huzaifa
					names.put(952141, "Roman Khan");
					names.put(989947, "Shabaz Quraishi");
					names.put(757525, "Waqas Khan");
					names.put(656532, "Huzaifa Ansari");
					
					 //System.out.println("==================================");
			         //System.out.println("   Welcome to the ATM Project");
			         //System.out.println("==================================");			
					 
					System.out.println("Enter your customer Number");
					setCustomerNumber(menuInput.nextInt());
					
					System.out.println("Enter your PIN Number: ");
					setPinNumber(menuInput.nextInt());
				}
				catch(Exception e) {
					System.out.println("\n" + "Invald Character(s).Only Numbers." + "\n");
					menuInput.nextLine(); // clear buffer
					x = 2;
				}	
				
				int cn = getCustomerNumber();
				int pn = getPinNumber();
				
				if (data.containsKey(cn) && data.get(cn) == pn) {
					setAccountHolderName(names.get(cn));
					System.out.println("\n" + "Login Succesfully! Welcome, " + getAccountHolderName() + "\n");
					getAccountType();
				} else
					System.out.println("\n" + "Wrong Customer or Pin Number" + "\n");
				} while (x == 1);
			}
		    
		    // --- Account Type Menu ---
			public void getAccountType() {
				System.out.println("Select the Account you want to Access: ");
				System.out.println("Type 1 - Current Account");
				System.out.println("Type 2 - Saving Account");
				System.out.println("Type 3 - Exit");
				
				int selection = menuInput.nextInt();
				
				switch (selection){
				case 1:
					getCurrent();
					break;
					
				case 2:
					getSaving();
					break;
					
				case 3:
					System.out.println("Thank You for Using this ATM, bye.  \n");
					break;
					
				default:
					System.out.println("\n" + "Invalid choice" + "\n");
				}
			}
			
			 // --- Current Account Menu ---
			public void getCurrent() {
				System.out.println("Current Acccount: ");
				System.out.println("type 1 - View Balance");
				System.out.println("type 2 - Withdraw Amount");
				System.out.println("type 3 - Deposit Ammount");
				System.out.println("type 4 - Exit");
				System.out.println("Choice: ");
				
				int selection = menuInput.nextInt();
				
				switch (selection) {
				case 1:
					System.out.println("Current Account Balance: " + moneyFormat.format(getCurrentBalance()));
					getAccountType();
					break;
					
				case 2:
					getCurrentWithdrawInput();
					getAccountType();
					break;
					
				case 3:
					getCurrentDepositInput();
					getAccountType();
					break;
					
				case 4:
					System.out.println("Thank You for using this ATM, bye.");
					break;
					
				default:
					System.out.println("\n" + "Inavlid Choice." + "\n");
					getCurrent();
				 }
			}
			
			// --- Saving Account Menu ---
			public void getSaving() {
				System.out.println("Current Acccount: ");
				System.out.println("type 1 - View Balance");
				System.out.println("type 2 - Withdraw Amount");
				System.out.println("type 3 - Deposit Ammount");
				System.out.println("type 4 - Exit");
				System.out.println("Choice: ");
				
				int selection = menuInput.nextInt();
				
				switch (selection) {
				case 1:
					System.out.println("Saving Account Balance: " + moneyFormat.format(getSavingBalance()));
					getAccountType();
					break;
					
				case 2:
					getSavingWithdrawInput();
					getAccountType();
					break;
					
				case 3:
					getSavingDepositInput();
					getAccountType();
					break;
					
				case 4:
					System.out.println("Thank You for using this ATM, bye.");
					break;
					
				default:
					System.out.println("\n" + "Inavlid Choice." + "\n");
					getSaving();
				 }
			}
		}
	


