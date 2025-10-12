package com.dsaproblems.DSAProblems.files;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileOperations {

	public void createFileWithContent(String fileName, String content) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(content);
			writer.close();
		} catch (IOException ex) {
			log.error("Exception while writing to a file :: {}", ex);
		}
	}

	public void appendContentToFile(String fileName, String content) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(fileName, true));
			writer.append(" ");
			writer.append(content);
			writer.close();
		} catch (IOException ex) {
			log.error("Exception while appending to a file :: {}", ex);
		}
	}

	public void writeToPositionInFile(String fileName, Integer data, int position) {
		RandomAccessFile writer = null;
		try {
			writer = new RandomAccessFile(fileName, "rw");
			writer.seek(position);
			writer.writeInt(data);
			writer.close();
		} catch (FileNotFoundException ex) {
			log.error("Exception while finding a file :: {}", ex);
		} catch (IOException ex) {
			log.error("Exception while writing to a file :: {}", ex);
		}

	}

}
