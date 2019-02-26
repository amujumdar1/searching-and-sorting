import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

import java.lang.reflect.Method;

class Test<T, U> 
{
	void linear (T array) {
		
	}
}

public class SearchAndSort {
	
	private static Scanner in;
	/**
	 * Program execution starts here.
	 * @param <E>
	 * 
	 * @param args
	 */
	
	public static <E> void main(String[] args) {
		SearchAndSort foo = new SearchAndSort();
		in = new Scanner(System.in);
		
		HashMap <String, Comparable<Comparable <E>>[]> map = new HashMap<>();
		
		
		String whichAlgorithm = "What algorithm would you like to execute?";
		String[] algorithms = new String[] {"bubble", "selection", "insertion", "merge", 
				"linear", "binary", "quit"};
		
		String whatData = "What type of data? (integers, strings) ";
		String[] dataTypes = new String[] {"integers", "strings"};
		
		
		
		String alg = foo.validateInput(algorithms, whichAlgorithm);
		
		String data = foo.validateInput(dataTypes, whatData);
		
		try {
			Method method = SearchAndSort.class.getMethod(alg, (data.equals("strings")) ? String.class : int.class);
			System.out.println(method);
		} catch (NoSuchMethodException | SecurityException e) {
			System.out.println("error");
			System.exit(-1);
		}
		
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
					array[i + 1] = array[i];
					array[i] = temp;
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
			E temp = array[index];
			array[i] = array[index];
			array[index] = temp;
		}
		
		return array;
	}
	
	public <E extends Comparable<E>> E[] insertion(E[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j > 0; j--) {
				if (array[j].compareTo(array[i]) > 0) {
					E temp = array[j];
					array[i] = array[j];
					array[j] = temp;
				}
				else break;
			}
		}
		return array;
	}
	
	public <E extends Comparable<E>> int linear(E[] array, E value) {
		if (array.length == 0) return -1;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}
	
	public <E extends Comparable<E>> int binary(E[] array, E value) {
		return 0;
	}
}