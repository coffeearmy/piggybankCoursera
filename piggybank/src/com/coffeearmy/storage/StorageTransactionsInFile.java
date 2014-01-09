package com.coffeearmy.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;


public class StorageTransactionsInFile implements StorageTransactions {
	public static String STORAGE_FILE_NAME = "lasttentransactions.txt";
	
	public StorageTransactionsInFile() {}

	public void writeTransaction(String transaction) throws IOException {

		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(STORAGE_FILE_NAME));
			out.write(transaction);
			out.newLine();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	public ArrayList<String> readTransactions()
			throws IOException {
		ArrayList<String> transactions = new ArrayList<String>();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(STORAGE_FILE_NAME));
			String lineFromFile = in.readLine();
			int numberOfLineReaded=1;
			do {
				transactions.add(lineFromFile);
				lineFromFile = in.readLine();
				numberOfLineReaded=+1;
			} while (lineFromFile != null);

		} finally {
			if (in != null) {
				in.close();
			}
		}

		return transactions;
	}
}
