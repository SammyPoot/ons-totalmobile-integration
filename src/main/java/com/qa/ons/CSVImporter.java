package com.qa.ons;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVImporter {

	public CSVImporter() {

	}

	public ArrayList<String[]> ingest(String csvFile) {
		File file = new File(csvFile);
		BufferedReader bufferedReader = null;
		String line = "";
		ArrayList<String[]> allCSVRows = new ArrayList<String[]>(); 
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			while ((line = bufferedReader.readLine()) != null) {
				allCSVRows.add(line.split(",",-1));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return allCSVRows;
	}
}
