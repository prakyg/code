package programs.random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ArrayListsEx {
	public ArrayListsEx() {
		super();
	}

	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		Collections.addAll(a, 1, 2, 3);
		Collections.addAll(a, 4, 5, 6);
		System.out.println("Printing all elements");
		for (Integer i : a) {
			System.out.print(i + "  ");
		}
		System.out.println("Printing elements by index");
		for (int i = 0; i < a.size(); i++) {
			System.out.println("Index: " + i + ", Element:" + a.get(i));
		}

		Iterator itr = a.iterator();
		while ((Integer) itr.next() != 4) {
		}
		itr.remove();

		System.out.println("Printing all elements");
		for (Integer i : a) {
			System.out.print(i + "  ");
		}
		System.out.println("Printing elements by index");
		for (int i = 0; i < a.size(); i++) {
			System.out.println("Index: " + i + ", Element:" + a.get(i));
		}

	}
}
