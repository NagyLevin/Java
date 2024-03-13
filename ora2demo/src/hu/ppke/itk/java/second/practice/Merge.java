package hu.ppke.itk.java.second.practice;

import java.io.*;
import java.util.Scanner;


public class Merge {



	public static void main(String[] args) throws IOException {
		try {
			BufferedReader aReader = new BufferedReader(new FileReader("a.txt"));
			BufferedReader bReader = new BufferedReader(new FileReader("b.txt"));
			PrintWriter writer = new PrintWriter("c.txt");

			while (aReader.ready() && bReader.ready()) {
				writer.println(aReader.readLine());
				writer.println(bReader.readLine());


			}
			while (aReader.ready()) {
				writer.println(aReader.readLine());


			}
			while (bReader.ready()) {
				writer.println(bReader.readLine());


			}
			aReader.close();
			bReader.close();
			writer.close();


		}catch (IOException e){
			System.out.println(e);
		}
	}
}
