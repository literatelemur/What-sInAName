import java.io.*;
import java.util.Scanner;
public class CensusReport {

	public static void main(String[] args) {
		System.out.println("Enter a name to be searched:");
		Scanner input = new Scanner(System.in);
		String name = input.nextLine();
		int index = -1;
		boolean found = false;
		String[][] census = read();
		while(!found) {
			for(int i = 0; i < census.length; i++) {
				if(census[i][0].equalsIgnoreCase(name)) {
					found = true;
					index = i;
				}
			}
			if(!found) {
				System.out.println("That name was not found in the records. Enter another name to be searched:");
				name = input.nextLine();
			}
		}
		System.out.println("Enter a maximum height for the resulting bar graph:");
		int height = input.nextInt();
		while(!(height > 9 && height < 31)) {
			System.out.println("The bar graph's maximum height must be between 10 and 30. Enter another maximum height for the resulting bar graph:");
			height = input.nextInt();
		}
		graph(read(), index, name, height);
		System.out.println("Enter a year to display that year's most popular names:");
		int year = input.nextInt();
		while(!(year == 1900 || year == 1910 || year == 1920 || year == 1930 || year == 1940 || year == 1950 || year == 1960 || year == 1970 || year == 1980 || year == 1990 || year == 2000)) {
			System.out.println("The year must be 1900, 1910, 1920, 1930, 1940, 1950, 1960, 1970, 1980, 1990, or 2000");
			System.out.println("Enter another year to display that year's most popular names:");
			year = input.nextInt();
		}
		System.out.println("Enter the number of names you wish to see:");
		int range = input.nextInt();
		while(!(range > 0 && range < 1001)) {
			System.out.println("The number must be between 1 and 1000. Enter another number of names you wish to see:");
			range = input.nextInt();
		}
		topNames(read(), year, range);
	}
	
	public static String[][] read() {
		java.util.Scanner s = null;
		try {
			s = new Scanner(new FileReader("ProjectData.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("NO FILE IS FOUND");
			System.exit(1);
		}
		
		String[][] census = new String[4429][12];
		int i = 0;
		while (s.hasNext()) {
			census[i][0] = s.next();
			for (int j = 1; j < census[i].length; j++) {
				census[i][j] = Integer.toString(s.nextInt());
			}
			i++;
			
		}
		return census;
	}
	
	public static void graph(String[][] census, int index, String name, int height) {	
		for(int h = height + 2; h > 0; h--) {
			for(int j = 1; j < census[index].length; j++) {
				int bar;
				if(Integer.parseInt(census[index][j]) == 0)
					bar = -5;
				else
					bar = (int)((1013.0 - Integer.parseInt(census[index][j])) / 1013 * height + 1);
				if(h - 2 == bar) {
					if(Integer.parseInt(census[index][j]) < 10)
							System.out.print("   " + Integer.parseInt(census[index][j]) + " ");
					else if(Integer.parseInt(census[index][j]) < 100)
							System.out.print("  " + Integer.parseInt(census[index][j]) + " ");
					else if(Integer.parseInt(census[index][j]) < 1000)
							System.out.print("  " + Integer.parseInt(census[index][j]));
					else
						System.out.print(" " + Integer.parseInt(census[index][j]));
				}
				else if(h - 1 == bar) 
					System.out.print("  __ ");
				else if(h == 1 && Integer.parseInt(census[index][j]) == 0)
					System.out.print("   " + Integer.parseInt(census[index][j]) + " ");
				else if(h > bar)
					System.out.print("     ");
				else if(h <= bar)
					System.out.print(" |  |");
				
				
					
			}
			System.out.println("");
		}
		
		
		System.out.println("+----+----+----+----+----+----+----+----+----+----+----+");
		System.out.println(" 1900 1910 1920 1930 1940 1950 1960 1970 1980 1990 2000" + "\n");
	}
	
	public static void topNames(String[][] census, int year, int range) {
		String[][] top = new String[range][2];
		int j = -1;
		if(year == 1900)
			j = 1;
		else if(year == 1910)
			j = 2;
		else if(year == 1920)
			j = 3;
		else if(year == 1930)
			j = 4;
		else if(year == 1940)
			j = 5;
		else if(year == 1950)
			j = 6;
		else if(year == 1960)
			j = 7;
		else if(year == 1970)
			j = 8;
		else if(year == 1980)
			j = 9;
		else if(year == 1990)
			j = 10;
		else if(year == 2000)
			j = 11;
		for(int i = 0; i < census.length; i++) {
			if(Integer.parseInt(census[i][j]) <= range && Integer.parseInt(census[i][j]) != 0){
				if(top[Integer.parseInt(census[i][j]) - 1][0] == null)
					top[Integer.parseInt(census[i][j]) - 1][0] = census[i][0];
				else
					top[Integer.parseInt(census[i][j]) - 1][1] = census[i][0];
			}
		}
		for(int l = 0; l < top.length; l++) {
			
			System.out.print((l + 1) + "\t");
			for(int w = 0; w < top[l].length; w++) {
				if(top[l][w] == null)
					System.out.print("\t\t");
				else if(top[l][w].length() > 7)
					System.out.print(top[l][w] + "\t" );
				else
					System.out.print(top[l][w] + "\t\t" );
			}
		System.out.println("");
		}
	}
}
