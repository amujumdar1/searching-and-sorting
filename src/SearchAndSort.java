import java.util.Arrays;
import java.util.Scanner;

class Test<T, U> 
{
	void linear (T array) {
		
	}
}

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
		
		foo.validateInput(algorithms, whichAlgorithm);
		
		
	}
	
	public void validateInput(String[] array, String question) {
		do {
			System.out.println(question);
			if (!Arrays.stream(array).anyMatch(in.nextLine()::equals)) {
				System.out.println("Please enter a valid input.");
			}
		} while (!Arrays.stream(array).anyMatch(in.nextLine()::equals));
		
		
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
}