package programs.tricky;

import java.io.IOException;

public class OverridingChoices {
	public OverridingChoices() {
		super();
	}

	public static void main(String[] args) {
		Parent p = new Parent();
		p.method("abc");
		Parent p1 = new Child();
		p1.method("abc");
		// p1.method("abc","abc"); compilation error
	}

	private static class Parent {
		void method(String s) {
			System.out.println("Parent");
		}

		void exceptionTest(String s) throws IOException {
			System.out.println("Parent");
		}

		void exceptionTest2(String s) throws IOException {
			System.out.println("Parent");
		}
	}

	public static class Child extends Parent {
		// @Override -- compliation error
		void method(String... s) {
			System.out.println("Child");
		}

		@Override
		void exceptionTest(String s) {
			System.out.println("Parent");
		}
		/*
		 * @Override void exceptionTest2(String s) throws Exception{
		 * System.out.println("Parent"); }
		 */ // Compilation error
	}
}