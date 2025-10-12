package com.dsaproblems.DSAProblems.files;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

public class FileOperationsClient {

	public static void main(String[] args) {

		FileOperations operation = new FileOperations();
		String fileName = "ZKNode";
		String content = "192.168.45.32";
		operation.createFileWithContent(fileName, content);

		operation.appendContentToFile(fileName, content);

		int data1 = 2014;
		operation.writeToPositionInFile(fileName, data1, 4);

	}

}
