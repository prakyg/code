package programs.ds.trie;

public class Driver {
	public Driver() {
		super();
	}

	public static void main(String[] args) {
		Trie t = new Trie();
		t.addWord("papa");
		t.addWord("ppaa");
		t.addWord("appa");
		t.print();
	}
}
