import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SearchAndSort {
	
	private static Scanner in;
	/**
	 * Program execution starts here.
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		SearchAndSort foo = new SearchAndSort();
		in = new Scanner(System.in);
		
		String whichAlgorithm = "What algorithm would you like to execute?";
		String[] algorithms = new String[] {"bubble", "selection", "insertion", "merge", 
				"linear", "binary", "quit"};
		
		String whatData = "What type of data? (integers, strings) ";
		String[] dataTypes = new String[] {"integers", "strings"};
		
		String howStored = "How is it stored? (array, list)";
		String[] storage = new String[] {"array", "list"};
		
		
		String alg = foo.validateInput(algorithms, whichAlgorithm);
		
		if (alg.equals("quit")) System.exit(0);
		
		
		String data = foo.validateInput(dataTypes, whatData);
		String store = foo.validateInput(storage, howStored);
		
		System.out.println("Enter the data. Format: arg1,arg2,arg3...argn");
		
		String arrayToSplit = in.nextLine();
		String[] stringArray = null;
		Integer[] intArray = null;
		try {
			stringArray = arrayToSplit.split(",");
			intArray = new Integer[stringArray.length];
			
			if (data.equals("integers")) {
				for (int i = 0; i < stringArray.length; i++) {
					intArray[i] = Integer.valueOf(i);
				}
			}
			
		}
		catch (NumberFormatException e) {
			System.out.println("Error");
			System.exit(-1);
		}
		
		List<String> stringList;
		List<Integer> intList;
		
		String stringSearch = null;
		int intSearch = 0;
		int value = 0;
		
		switch (alg) {
			case "bubble":
				if (data.equals("string")) stringArray = foo.bubble(stringArray);
				else intArray = foo.bubble(intArray);
				
			case "selection":
				if (data.equals("string")) stringArray = foo.selection(stringArray);
				else intArray = foo.selection(intArray);
				
			case "insertion":
				if (data.equals("string")) stringArray = foo.insertion(stringArray);
				else intArray = foo.insertion(intArray);
				
			case "merge":
				if (data.equals("string")) stringArray = foo.merge(stringArray);
				else intArray = foo.merge(intArray);
				
			case "linear":
				System.out.println("Enter the value to be searched.");
				if (data.equals("string")) {
					stringSearch = in.nextLine();
					value = foo.linear(stringArray, stringSearch);
					System.out.println(value);
					System.exit(0);
				}
				else {
					try {
						value = foo.linear(intArray, intSearch);
					}
					catch (NumberFormatException e) {
						System.out.println("Invalid Parameter");
						System.exit(-1);
					}
				}
			case "binary":
				if (data.equals("string")) value = foo.binary(stringArray, stringSearch);
				else value = foo.binary(intArray, intSearch);
		}
		
		if (store.equals("list")) {
			if (data.equals("string")) {
				stringList = Arrays.asList(stringArray);
				for (String i : stringList) {
					System.out.print(i + ", ");
				}
			}
			intList = Arrays.asList(intArray);
			for (int i : intList) {
				System.out.print(i + ", ");
			}
			
		}
		else {
			if (data.equals("string")) {
				for (String i : stringArray) {
					System.out.print(i + ", ");
				}
			}
			else {
				for (int i : intArray) {
					System.out.print(i + ", ");
				}
			}
		}
		in.close();
		
	}
	
	
	public String validateInput(String[] array, String question) {
		String input = null;
		do {
			System.out.println(question);
			input = in.nextLine();
			if (!Arrays.stream(array).anyMatch(input.toLowerCase()::equals)) {
				System.out.println("Please enter a valid input.");
			}
		} while (!Arrays.stream(array).anyMatch(input.toLowerCase()::equals));
		return input;
	
	}
	
	
	public <E extends Comparable<E>> E[] bubble(E[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length - i; j++) {
				if ((array[i].compareTo(array[i + 1]) > 0)) {
					E temp = array[i];
					array[i] = array[i + 1];
 					array[i + 1] = temp;
				}
			}
		}
		return array;
	}
	public <E extends Comparable<E>> E[] selection(E[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int index = i;
			E min = array[i];
			for (int j = i + 1; j < array.length; j++) {
				if (array[j].compareTo(min) < 0) {
					min = array[j];
					index = j;
				}
			}
			E temp = array[i];
			array[i] = array[index];
			array[index] = temp;
		}
		
		return array;
	}
	public <E extends Comparable<E>> E[] insertion(E[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j > 0; j--) {
				if (array[j].compareTo((array[i])) < 0) {
					E temp = array[j];
					array[i] = array[j];
					array[j] = temp;
				}
				else break;
			}
		}
		return array;
	}
	
	public <E extends Comparable<E>> E[] merge(E[] array) {
		return array;
	}
	
	public <E extends Comparable<E>> int linear(E[] array, E value) {
		if (array.length == 0) return -1;
		for (int i = 0; i < array.length; i++) {
			if (array[i].compareTo(value) == 0) {
				return i;
			}
		}
		return -1;
	}
	public <E extends Comparable<E>> int binary(E[] array, E value) {
		merge(array);
		return 0;
	}
}