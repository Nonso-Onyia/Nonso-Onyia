package main;

import java.util.Scanner;

public class Converter {
	static Scanner scanner = new Scanner(System.in);
	static double results;
	static double userInput;
	
		public static void main(String[] args) {
			int menuSelection = 0; 
			
			while (menuSelection !=4) {
				System.out.println("welcome to the Conversion Menu!"+"\n" + "Please pick an option for conversion"+"\n"+ "1. Cups to Liters"+"\n"+ "2. Miles to Kilometers"+ "\n"+ "3. US gallons to Imperial gallons"+"\n"+ "4. Exit Menu" );
				menuSelection = scanner.nextInt();
				
				switch (menuSelection) {
				case 1 : 
					System.out.println("how many Cups are you converting to Liters?");
					userInput= scanner.nextInt();
					System.out.println(conversionCups(userInput) + " Liters" );
					
					break;
					
				 case 2 : 
					System.out.println("how many Miles are you converting to kilometers?");
					userInput= scanner.nextInt();
					System.out.println(conversionMiles(userInput) + " Kilometers" );
					
					break;
					
					
				case 3 : 
					System.out.println("how many US gallons are you converting to Imperial gallons?");
					userInput= scanner.nextInt();
					System.out.println(conversionUSgallons(userInput) + " Imperical gallons" );
					
					break;
					
				case 4 : 
					System.out.println("Have a great day!");
					
					break;
					
				default: 
					System.out.println("Invalid user input. Please try again only using numbers 1 through 4");
			}
			System.out.println();
				
			}
			scanner.close();
			
		}
public static double userInput() {
	userInput= scanner.nextDouble();
	scanner.nextLine();
	return userInput;
}
public static double conversionCups(double qty) {
	results = qty * 8;
	return results;
}
public static double conversionMiles(double qty){
	results = qty * 1.60934;
	return results;
}
public static double conversionUSgallons (double qty) {
	results = qty * 0.832674;
	return results;
}
}
