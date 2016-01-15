package programs.thoughtworks.ctm;

//recieves a Conference variable
public abstract class Writer {
	private Writer() {

	}

	public static void writeToConsole(Conference conf) {
		conf.print();
	}
}
