package programs.mathAlgos;

public class LCM {
	public LCM() {
		super();
	}

	public static void main(String[] args) {
		// LCM lCM = new LCM();
		System.out.println("LCM of 10 , 15 " + getLCM(10, 15));
		System.out.println("LCM of 100, 15 " + getLCM(100, 15));
		System.out.println("LCM of 2 , 15 " + getLCM(2, 15));
		System.out.println("LCM of 63, 18 " + getLCM(63, 18));
	}

	public static int getLCM(int a, int b) {
		if (a < b) {
			int temp = b;
			b = a;
			a = temp;
		}
		// a is bigger bumber
		int lcmCandidate = a;
		for (int i = 2;; i++) {
			if (lcmCandidate % b == 0) { // the LCM candidate is always a
											// multiple of a, so no need to
											// check multiplicity by itself
				return lcmCandidate;
			} else {
				lcmCandidate = a * i;
			}
		}
	}
}
