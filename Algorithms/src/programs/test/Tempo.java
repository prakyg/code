package programs.test;

import java.util.Arrays;
import java.util.HashMap;

public class Tempo {
	public Tempo() {
		super();
	}

	public static void main(String[] args) {
		String x = "q1,q2,q3,q4";
		String[] s = x.split(",");
		System.out.println(Arrays.toString(s));
	}
}
