package atm;

import java.text.DecimalFormat;
import java.util.Scanner;


public class Account {

	 private int customerNumber;
	 private int pinNumber;
	 private double currentBalance = 0;
	 private double savingBalance = 0;
	 private String accountHolderName;
	
	Scanner input = new Scanner(System.in);
	DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");
	
	//getter and setter 
	public int setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
		return customerNumber;
	}
	
	public int getCustomerNumber() {
		return customerNumber;
	}
	
	public int setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
		return pinNumber;
	}
	
	public int getPinNumber() {
		return pinNumber;
	}

	public String setAccountHolderName(String name) {
		this.accountHolderName = name;
		return name;
	}
	
	public String getAccountHolderName() {
		return accountHolderName;
	}
	
	public double getCurrentBalance() {
		return currentBalance;
	}
	
	public double getSavingBalance() {
		return savingBalance;
	}
	
	//transaction
	public double calcCurrentWithdraw(double amount) {
		currentBalance = (currentBalance - amount);
		return currentBalance;
	}
	
	public double calcSavingWithdraw(double amount) {
		savingBalance = (savingBalance - amount);
		return savingBalance;
	}
	
	public double calcCurrentDeposit(double amount) {
		currentBalance = (currentBalance + amount);
		return currentBalance;
	}
	
	public double calcSavingDeposit(double amount) {
		savingBalance = (savingBalance + amount);
		return savingBalance;
	}
	
	//input operations
	public void getCurrentWithdrawInput() {
		System.out.println("Current Account Balance: " + moneyFormat.format(currentBalance));
		System.out.println("Amount you want to withdraw from current Account: ");
		double amount = input.nextDouble();
		
		if ((currentBalance - amount) >=0) {
			calcCurrentWithdraw(amount);
			System.out.println("New Current Account Balance: " + moneyFormat.format(currentBalance));
		} else {
			System.out.println("Balance cannot be Negative." + "\n");
		}
	}
	
	public void getSavingWithdrawInput() {
		System.out.println("Saving Account Balance: " + moneyFormat.format(savingBalance));
		System.out.println("Amount you want to withdraw from Saving Account: ");
		double amount = input.nextDouble();
		
		if ((savingBalance - amount) >=0) {
			calcSavingWithdraw(amount);
			System.out.println("New Saving Account Balance: " + moneyFormat.format(savingBalance));
		} else {
			System.out.println("Balance cannot be Negative." + "\n");
		}
	}
	
	public void getCurrentDepositInput() {
		System.out.println("Current Account Balance: " + moneyFormat.format(currentBalance));
		System.out.println("Amount you want to deposit from current Account: ");
		double amount = input.nextDouble();
		
		if ((currentBalance + amount) >=0) {
			calcCurrentDeposit(amount);
			System.out.println("New Current Account Balance: " + moneyFormat.format(currentBalance));
		} else {
			System.out.println("Balance cannot be Negative." + "\n");
		}
	}
	
	public void getSavingDepositInput() {
		System.out.println("Saving Account Balance: " + moneyFormat.format(savingBalance));
		System.out.println("Amount you want to deposit from current Account: ");
		double amount = input.nextDouble();
		
		if ((savingBalance + amount) >=0) {
			calcSavingDeposit(amount);
			System.out.println("New Saving Account Balance: " + moneyFormat.format(savingBalance));
		} else {
			System.out.println("Balance cannot be Negative." + "\n");
		}
	}	
}
