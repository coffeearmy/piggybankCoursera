package com.coffeearmy.storage;

import java.io.IOException;
import java.util.ArrayList;

public interface StorageTransactionsAndTotal {

	public void writeTransaction(String transaction) throws IOException;
	public ArrayList<String> readTransactions() throws IOException;
	public void writeTotal(String total) throws IOException;
	public String readTotal() throws IOException;
}
