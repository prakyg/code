package programs.singetonTests;

public class Driver {
	public Driver() {
		super();
	}

	public static void main(String[] args) {
		Driver driver = new Driver();
		ExtensibleSingleton e = new ExtensibleSingleton();
	}
}
