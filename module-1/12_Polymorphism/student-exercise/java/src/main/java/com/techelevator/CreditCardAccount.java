package com.techelevator;

/**
 * CreditCardAccount
 */
public class CreditCardAccount implements Accountable {
	
/*******************************************************************
 * Place the code for  your CreditCard class here
 *******************************************************************/

	
	private String accountHolder;
	private String accountNumber;
	private int debt;
	
	public CreditCardAccount(String accountHolder,String accountNumber) {
		this.accountHolder=accountHolder;
		this.accountNumber=accountNumber;
		
	}
	public int pay(int amountToPay) {
		debt-=amountToPay;
		return debt;
	}
	public int charge(int amountToCharge) {
		debt+=amountToCharge;
		return debt;
	}
	
	
	
	public String getAccountHolder() {
	return accountHolder;
}



public String getAccountNumber() {
	return accountNumber;
}



public int getDebt() {
	return debt;
}


    public int getBalance() {
    	//***************************************************************
    	// This should return the negative of the debt data member
    	//***************************************************************
    	
        return -debt;
    }

 

}