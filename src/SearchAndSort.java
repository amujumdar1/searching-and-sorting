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
	
	enum Algorithms {
		bubble, selection, insertion, merge, linear, binary, quit
	}
	
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
					intArray[i] = Integer.valueOf(stringArray[i]);
				}
			}
			
		}
		catch (NumberFormatException e) {
			System.out.println("Data Type Error.");
			System.exit(-1);
		}
		List<String> stringList;
		List<Integer> intList;
		
		String stringSearch = null;
		int intSearch = 0;
		int value = 0;
		
		switch (alg) {
			case "bubble":
				if (data.equals("strings")) stringArray = foo.bubble(stringArray);
				else intArray = foo.bubble(intArray);
				break;
				
			case "selection":
				if (data.equals("strings")) stringArray = foo.selection(stringArray);
				else intArray = foo.selection(intArray);
				break;
				
			case "insertion":
				if (data.equals("strings")) stringArray = foo.insertion(stringArray);
				else intArray = foo.insertion(intArray);
				break;
				
			case "merge":
				if (data.equals("strings")) stringArray = foo.merge(stringArray);
				else intArray = foo.merge(intArray);
				break;
				
			case "linear":
				System.out.println("Enter the value to be searched.");
				if (data.equals("strings")) {
					stringSearch = in.nextLine();
					value = foo.linear(stringArray, stringSearch);
					System.out.println(value);
					System.exit(0);
				}
				else {
					try {
						intSearch = in.nextInt();
						in.nextLine();
						value = foo.linear(intArray, intSearch);
						System.out.println(value);
						System.exit(0);
					}
					catch (NumberFormatException e) {
						System.out.println("Invalid Parameter");
						System.exit(-1);
					}
					
				}
				break;
			case "binary":
				System.out.println("Enter the value to be searched.");
				if (data.equals("strings")) {
					stringSearch = in.nextLine();
					value = foo.binary(stringArray, stringSearch);
					System.out.println(value);
					System.exit(0);
				}
				else {
					try {
						intSearch = in.nextInt();
						in.nextLine();
						value = foo.binary(intArray, intSearch);
						System.out.println(value);
						System.exit(0);
					}
					catch (NumberFormatException e) {
						System.out.println("Invalid Parameter");
						System.exit(-1);
					}
				}
				break;
				
			default:
				System.out.println("Error");
		}
		
		switch(store) {
			case "list":
				if (data.equals("strings")) {
					stringList = Arrays.asList(stringArray);
					for (String i : stringList) {
						System.out.print(i + ", ");
					}
				}
				else {
					intList = Arrays.asList(intArray);
					for (int i : intList) {
						System.out.print(i + ", ");
					}
				}
				break;
			case "array":
				if (data.equals("strings")) {
					for (String i : stringArray) {
						System.out.print(i + ", ");
					}
				}
				else {
					for (int i : intArray) {
						System.out.print(i + ", ");
					}
				}
				break;
			default:
				System.out.println("Error");
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
		return input.toLowerCase();
	
	}
	// generic swap algorithm
	public <E> void swap(E[] array, int i, int j) {
		E temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}
	
	public <E extends Comparable<E>> E[] bubble(E[] array) {
		if (array.length < 1) return array;
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length - i - 1; j++) {
				if ((array[j].compareTo(array[j + 1]) > 0)) {
					swap(array, j, j + 1);
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
			swap(array, i, index);
		}
		
		return array;
	}
	public <E extends Comparable<E>> E[] insertion(E[] array) {
		for (int i = 1; i < array.length; i++) {
			for (int j = i; j > 0; j--) {
				if (array[j].compareTo(array[j - 1]) < 0) {
					swap(array, j, j - 1);
				}
			}
		}
		return array;
	}
	
	/* TODO: 
	 * implement @merge 
	 * implement @binary
	 */
	public <E extends Comparable<E>> E[] merge(E[] array) {
		sort(array, 0, array.length - 1);
		return array;
	}
	    
	private <E extends Comparable<E>> void sort(E[] array, int i, int j) {
		if (j - i < 1) return;
		int middle = (i + j) / 2;
		
		sort(array, i, middle);
		sort(array, middle + 1, j);
		
		merging(array, i, middle, j);
	}

	@SuppressWarnings("unchecked") 
	private <E extends Comparable<E>> void merging(E[] array, int p, int mid, int q) {

		Object[] tempArray = new Object[q-p+1]; 
		
		int i = p;
		int j = mid + 1;
		int k = 0;
		while (i <= mid && j <= q) {
		    if (array[i].compareTo(array[j])<=0)
			tempArray[k] = array[i++];
		    else
			tempArray[k] = array[j++];
		    k++;
		}
		if (i <= mid && j > q) {
		    while (i <= mid) 
			tempArray[k++] = array[i++];
		} 
		else {
		    while (j <= q)
			tempArray[k++] = array[j++];
		}
		for (k = 0; k < tempArray.length; k++) {
		    array[k + p] = (E)(tempArray[k]);
		}
	}
	
	public <E extends Comparable<E>> int linear(E[] array, E value) {
		if (array.length < 1) return -1;
		for (int i = 0; i < array.length; i++) {
			if (array[i].compareTo(value) == 0) {
				return i;
			}
		}
		return -1;
	}
	public <E extends Comparable<E>> int binary(E[] array, E value) {
		if (array.length < 1) return -1;
		
		merge(array);
		
		int l = 0;
		int n = array.length;
		int middle;
		
		while (l <= n) {
			middle = (l + n) / 2;
			
			if (value.compareTo(array[middle]) > 0) {
				l = middle + 1;
			}
			else if (value.compareTo(array[middle]) < 0) {
				n = middle - 1;
			}
			
			else {
				return middle;
			}
		}
		return -1;
	}
}