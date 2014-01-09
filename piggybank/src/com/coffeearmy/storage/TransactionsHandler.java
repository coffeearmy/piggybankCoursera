package com.coffeearmy.storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import android.R;

public class TransactionsHandler {


	StorageTransactions storageTransactions;
	
	public TransactionsHandler() {
		
		storageTransactions=new StorageTransactionsInFile();
	}
	
	//For now we store the transactions in a file
	public void writeTransactions(String transaction){
		try {
			storageTransactions.writeTransaction(transaction);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<String> readTransactions() {
		ArrayList<String> transactions=null;
		try {
			 transactions = storageTransactions.readTransactions();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (ArrayList<String>) (transactions==null?Collections.emptyList():transactions);
	}
}
