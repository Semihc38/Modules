package com.techelevator;

public class CheckingAccount extends BankAccount {

	public CheckingAccount(String accountHolderName, String accountNumber, int balance) {
		super(accountHolderName, accountNumber, balance);
		
	}
	public CheckingAccount(String accountHolderName, String accountNumber) {
		super(accountHolderName, accountNumber);
		
	}
	public int withdraw(int amountToWithdraw) {
		
		if(getBalance()<=-100) {
			return getBalance();
		}
		int newBalance=getBalance()-amountToWithdraw;
		
		
		if(newBalance>0) {
			super.withdraw(amountToWithdraw);
		}if(newBalance<0 &&newBalance>=-100) {
			super.withdraw(amountToWithdraw+10);
		}
		return getBalance();
		
	}

}
