import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SearchAndSort {
	
	private static Scanner in;
	/**
	 * Program execution starts here.
	 * @param <E>
	 * 
	 * @param args
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	
	public static <E> void main(String[] args) throws NoSuchMethodException, SecurityException {
		SearchAndSort foo = new SearchAndSort();
		in = new Scanner(System.in);
		
		HashMap <String, Object> stringMap = new HashMap<String, Object>();
		HashMap <String, Object> intMap = new HashMap <String, Object>();
		
		/*try {
			System.out.println(SearchAndSort.class.getMethod("square", int.class).invoke(foo, 4));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		
		String whichAlgorithm = "What algorithm would you like to execute?";
		String[] algorithms = new String[] {"bubble", "selection", "insertion", "merge", 
				"linear", "binary", "quit"};

		
		
		
		String whatData = "What type of data? (integers, strings) ";
		String[] dataTypes = new String[] {"integers", "strings"};
		
		String howStored = "How is it stored? (array, list)";
		String[] storage = new String[] {"array", "list"};
		
		
		String alg = foo.validateInput(algorithms, whichAlgorithm);
		
		if (alg == "quit") System.exit(0);
		
		
		String data = foo.validateInput(dataTypes, whatData);
		
		String store = foo.validateInput(storage, howStored);
		
		System.out.println("Enter the data. Format: arg1,arg2,arg3...argn");
		
		String arrayToSplit = in.nextLine();
		String delimiter = ","; 
		String[] stringArray = null;
		int[] intArray = null;
		try {
			stringArray = arrayToSplit.split(delimiter);
			
			if (data.equals("integers")) {
				intArray = Arrays.stream(arrayToSplit.split(",")).mapToInt(Integer::parseInt).toArray();
				System.out.println(intArray[0] + intArray[1]);
			}
			
		}
		catch (NumberFormatException e) {
			System.out.println("Error");
		}
		
		ArrayList<String> stringList = new ArrayList<String>();
		for (String i : stringArray) {
			stringList.add(i);
		}

		
		ArrayList<Integer> intList = new ArrayList<Integer>();
		for (int i : intArray) {
			intList.add(i);
		}
		
		switch (alg) {
			case "bubble":
				if (data.equals("string")) foo.bubble(stringList);
				else foo.bubble(intList);
			case "selection":
				if (data.equals("string")) {
					if (store.equals("array")) foo.selection(stringArray);
					else foo.selection(Arrays.asList(stringArray));
				}
				else {
					if (store.equals("array")) foo.selection(intArray);
					else foo.selection(intList);
				}
			case "insertion":
				if (data.equals("string")) {
					if (store.equals("array")) foo.insertion(stringArray);
					else foo.insertion(Arrays.asList(stringArray));
				}
				else {
					if (store.equals("array")) foo.insertion(intArray);
					else foo.insertion(intList);
				}
			case "merge":
				if (data.equals("string")) {
					if (store.equals("array")) foo.mergesort(stringArray);
					else foo.mergesort(Arrays.asList(stringArray));
				}
				else {
					if (store.equals("array")) foo.mergesort(intArray);
					else foo.mergesort(intList);
				}
			case "linear":
				if (data.equals("string")) {
					if (store.equals("array")) foo.linear(stringArray);
					else foo.linear(Arrays.asList(stringArray));
				}
				else {
					if (store.equals("array")) foo.linear(intArray);
					else foo.linear(intList);
				}
			
		}
		
		try {
			for (int i = 0; i < algorithms.length - 1; i++) {
				if (i < 4) {
					stringMap.put(algorithms[i], SearchAndSort.class.getMethod(algorithms[i], 
							(store.equals("list")) ? List.class : String[].class).invoke(foo, (store.equals("list")) ?
									Arrays.asList(stringArray) : stringArray));

					
					intMap.put(algorithms[i], SearchAndSort.class.getMethod(algorithms[i],(store.equals("list")) 
							? ArrayList.class : int[].class).invoke(foo, (store.equals("list")) ?
									intList : intArray));
				}
				else {
					stringMap.put(algorithms[i], SearchAndSort.class.getMethod(algorithms[i], 
							(store.equals("list")) ? List.class : String[].class, String.class).invoke(foo, List.class, String.class));
					
					intMap.put(algorithms[i], SearchAndSort.class.getMethod(algorithms[i], (store.equals("list")) 
							? List.class : String[].class, int.class).invoke(foo, int.class, int.class));
				}
			}
		}
		catch(NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		try {
			Object method = (data.equals("strings")) ? stringMap.get(alg) : intMap.get(alg);
			System.out.println(method);
		} catch (SecurityException e) {
			System.out.println("error");
			System.exit(-1);
		}
		in.close();
		
	}
	
	/*public int square(int a) {
		return a * a;
	}*/
	
	
	
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
	
	// TODO: convert the E[] methods to String ArrayLists or int[] arrays
	
	/*public int[] bubble(int[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length - i; j++) {
				if ((array[i] > array[i + 1])) {
					int temp = array[i];
					array[i + 1] = array[i];
					array[i] = temp;
				}
			}
		}
		return array;
	}
	public ArrayList<Integer> bubble(ArrayList<Integer> array) {
		for (int i = 0; i < array.size(); i++) {
			for (int j = 0; j < array.size() - i; j++) {
				if ((array.get(i).compareTo(array.get(i + 1))) > 0) {
					int temp = array.get(i);
					array.set(i + 1, array.get(i));
					array.set(i , temp);
				}
			}
		}
		return array;
	}
	public String[] bubble(String[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length - i; j++) {
				if ((array[i].compareTo(array[i + 1])) < 0) {
					String temp = array[i];
					array[i + 1] = array[i];
					array[i] = temp;
				}
			}
		}
		return array;
	}
	
	public List<String> bubble(List<String> array) {
		for (int i = 0; i < array.size(); i++) {
			for (int j = 0; j < array.size() - i; j++) {
				if ((array.get(i).compareTo(array.get(i + 1))) > 0) {
					String temp = array.get(i);
					array.set(i + 1, array.get(i));
					array.set(i , temp);
				}
			}
		}
		return array;
	}*/
	public <E extends Comparable<E>> List<E> bubble(List<E> array) {
		for (int i = 0; i < array.size(); i++) {
			for (int j = 0; j < array.size() - i; j++) {
				if ((array.get(i).compareTo(array.get(i + 1)) > 0)) {
					E temp = array.get(i);
					array.set(i + 1 , array.get(i));
					array.set(i, temp);
				}
			}
		}
		return array;
	}
	
	public int[] selection(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int index = i;
			int min = array[i];
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] > min) {
					min = array[j];
					index = j;
				}
			}
			int temp = array[i];
			array[i] = array[index];
			array[index] = temp;
		}
		
		return array;
	}
	
	public ArrayList<Integer> selection(ArrayList<Integer> array) {
		for (int i = 0; i < array.size() - 1; i++) {
			int index = i;
			int min = array.get(i);
			for (int j = i + 1; j < array.size(); j++) {
				if (array.get(i).compareTo(min) < 0) {
					min = array.get(j);
					index = j;
				}
			}
			int temp = array.get(i);
			array.set(i, array.get(index));
			array.set(index, temp);
		}
		
		return array;
	}
	public String[] selection(String[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int index = i;
			String min = array[i];
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] > min) {
					min = array[j];
					index = j;
				}
			}
			String temp = array[i];
			array[i] = array[index];
			array[index] = temp;
		}
		
		return array;
	}
	
	public List<String> selection(List<String> array) {
		for (int i = 0; i < array.size() - 1; i++) {
			int index = i;
			String min = array.get(i);
			for (int j = i + 1; j < array.size(); j++) {
				if (array.get(i).compareTo(min) < 0) {
					min = array.get(j);
					index = j;
				}
			}
			String temp = array.get(i);
			array.set(i, array.get(index));
			array.set(index, temp);
		}
		
		return array;
	}

	/*public <E extends Comparable<E>> E[] selection(E[] array) {
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
	}*/
	public int[] insertion(int[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j > 0; j--) {
				if (array[j] > (array[i])) {
					int temp = array[j];
					array[i] = array[j];
					array[j] = temp;
				}
				else break;
			}
		}
		return array;
	}
	public List<String>insertion(List<String> array) {
		for (int i = 0; i < array.size(); i++) {
			for (int j = i + 1; j > 0; j--) {
				if (array.get(j).compareTo(array.get(i)) > 0) {
					String temp = array.get(j);
					array.set(i, array.get(j));
					array.set(j, temp);
				}
				else break;
			}
		}
		return array;
	}
	/*public <E extends Comparable<E>> E[] insertion(E[] array) {
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
	}*/
	
	public int[] mergesort(int[] array) {
		return array;
	}
	
	public List<String> mergesort(List<String> array){
		return array;
	}
	
	public int linear(int[] array, int value) {
		if (array.length == 0) return -1;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}
	
	public int linear(List<String> array, String value) {
		if (array.size() == 0) return -1;
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i).equals(value)) {
				return i;
			}
		}
		return -1;
	}
	
	public int[] binary(List<String> array, String value) {
		mergesort(array);
		return new int[] {0};
	}
	
	public  int binary(int[] array, int value) {
		mergesort(array);
		return 0;
	}
}