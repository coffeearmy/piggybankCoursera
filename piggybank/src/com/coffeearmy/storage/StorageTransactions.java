package com.coffeearmy.storage;

import java.io.IOException;
import java.util.ArrayList;

public interface StorageTransactions {

	public void writeTransaction(String transaction) throws IOException;
	public ArrayList<String> readTransactions() throws IOException;
}
