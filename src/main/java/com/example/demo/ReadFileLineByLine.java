package com.example.demo;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.opencsv.CSVWriter;

@Service
public class ReadFileLineByLine {

	// Function to read file
	public static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	//Remove empty string from array
	public static String[] cleanArray(String[] array) {
		return Arrays.stream(array).filter(x -> !StringUtils.isEmpty(x)).toArray(String[]::new);
	}
        
	public static void main(String[] args) {
                
		// File path of input file
		String filePath = "src/main/resources/small.txt";
		List<String[]> data = new ArrayList<>();

		String[] array = null;
		try {
			String content = readFile(filePath, StandardCharsets.UTF_8);
		        // Spliting the sentences in the file by dot
			array = content.split("\\.");
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (String str : array) {

			String[] words = str.split("\\s");
			words = cleanArray(words);

			// Sorting words from sentence by ascending order using Comparator interface
			Arrays.sort(words, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o1.toLowerCase().compareTo(o2.toLowerCase()); // sort in ascending order
				}
			});

			data.add(words);

		}
		generateCSV(data);
	}

	// Function to generate output CSV file and Store words for each sentence as per requirement
	static void generateCSV(List<String[]> data) {

		CSVWriter csvWrite = null;
		try {
			csvWrite = new CSVWriter(new FileWriter("src/main/resources/result.csv"));
			String[] entries = { "Word1", "Word2", "Word3", "Word4", "Word5", "Word6", "Word7", "Word8", "Word9",
					"Word10", "Word11", "Word12", "Word13", "Word14", "Word15", "Word16", "Word17", "Word18", "Word19",
					"Word20", "Word21", "Word22", "Word23", "Word24", "Word25" };
			csvWrite.writeNext(entries);
			csvWrite.writeAll(data);
			csvWrite.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
