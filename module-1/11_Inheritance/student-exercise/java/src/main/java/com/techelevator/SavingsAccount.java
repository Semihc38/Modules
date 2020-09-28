package com.techelevator;

public class SavingsAccount extends BankAccount {

	public SavingsAccount(String accountHolderName, String accountNumber, int balance) {
		super(accountHolderName, accountNumber, balance);
		
	}
	public SavingsAccount(String accountHolderName, String accountNumber) {
		super(accountHolderName, accountNumber);
		
	}
	
	public int withdraw(int amountToWithdraw) {
		
		if(amountToWithdraw>getBalance()) {
			System.out.println("Insufficient amount.");
			return getBalance();
		}else if(amountToWithdraw<=getBalance()) {
			if(getBalance()-amountToWithdraw<150) {
				super.withdraw(amountToWithdraw+2);
				
				return getBalance();
			}
		}
		return super.withdraw(amountToWithdraw);
			
	}

}
