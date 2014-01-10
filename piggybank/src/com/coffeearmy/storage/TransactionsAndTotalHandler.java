package com.coffeearmy.storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import android.R;
import android.content.Context;

public class TransactionsAndTotalHandler {


	StorageTransactionsAndTotal storageTransactions;
	
	
	public TransactionsAndTotalHandler(Context c) {
		
		try {
			storageTransactions=new StorageTransactionsInFile(c);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		return (ArrayList<String>) (transactions==null?new ArrayList<String>():transactions);
	}
	
	//For now we store the transactions in a file
		public void writeTotal(String total){
			try {
				storageTransactions.writeTotal(total);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		public String readTotal() {
			String total="0";
			try {
				total = storageTransactions.readTotal();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return total;
		}
}
