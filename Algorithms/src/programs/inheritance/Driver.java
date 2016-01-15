package programs.inheritance;

import programs.singetonTests.ExtensibleSingleton;

public class Driver {
	public Driver() {
		super();
	}

	public static void main(String[] args) {
		Driver driver = new Driver();
		Parent p;
		p = new Child();
		// p.noPrivateOverride();
		p.noStaticOverride();
		p = new Parent();
		p.noStaticOverride();
		Child c = new Child();
		c.noStaticOverride();
		// ExtensibleSingleton e = new ExtensibleSingleton();
	}
}
