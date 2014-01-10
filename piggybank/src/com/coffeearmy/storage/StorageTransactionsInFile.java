package com.coffeearmy.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import org.apache.http.message.LineFormatter;

import android.content.Context;

public class StorageTransactionsInFile implements StorageTransactionsAndTotal {
	private static String STORAGE_FILE_NAME_TOTAL = "totalSaves.txt";
	public static String STORAGE_FILE_NAME = "transactions.txt";
	Context context;
	private File transactionStorage;
	private File totalStorage;

	public StorageTransactionsInFile(Context c) throws IOException {
		context=c;
		transactionStorage= new File(context.getFilesDir()+STORAGE_FILE_NAME);
		transactionStorage.createNewFile();
		totalStorage= new File(context.getFilesDir()+STORAGE_FILE_NAME_TOTAL);
		totalStorage.createNewFile();
	}

	public void writeTransaction(String transaction) throws IOException {

		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(transactionStorage,true));
			out.append(transaction);
			out.newLine();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	public ArrayList<String> readTransactions() throws IOException {
		ArrayList<String> transactions = new ArrayList<String>();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(transactionStorage));
			String lineFromFile = in.readLine();
			int numberOfLineReaded = 1;
			do {
				transactions.add(lineFromFile);
				lineFromFile = in.readLine();
				numberOfLineReaded = +1;
			} while (lineFromFile != null);

		} finally {
			if (in != null) {
				in.close();
			}
		}

		return transactions;
	}

	public void writeTotal(String total) throws IOException {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(totalStorage));
			out.write(total);
			out.newLine();
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

	public String readTotal() throws IOException {
		String total = "0";
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(totalStorage));
			String lineFromFile = in.readLine();
			if (lineFromFile != null) {
				total = lineFromFile;
			}

		} finally {
			if (in != null) {
				in.close();
			}
		}

		return total;
	}
}
