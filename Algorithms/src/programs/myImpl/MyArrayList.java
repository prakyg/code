package programs.myImpl;

public class MyArrayList<E> {
	private final E[] elem;
	private int size;
	private static final int DEFAULT_SIZE = 10;

	public MyArrayList() {
		this(10);
	}

	public MyArrayList(int n) {
		this.elem = (E[]) new Object[n]; // unchecked warning
		size = 0;
	}

	public void add(E element) {
		if (size < elem.length)
			elem[size++] = element;
		else
			throw new ArrayIndexOutOfBoundsException();
	}

	public E get() {
		if (size > 0) {
			E temp = elem[size - 1];
			elem[size - 1] = null;
			size--;
			return temp;
		} else
			return null;
	}

	public static void main(String[] args) {
		MyArrayList<String> my = new MyArrayList<String>();
		my.add("Hello");
		my.add("how");
		my.add("are");
		my.add("you");

		System.out.println(my.get());
		System.out.println(my.get());
		System.out.println(my.get());
		System.out.println(my.get());
	}
}
