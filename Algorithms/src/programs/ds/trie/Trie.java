package programs.ds.trie;

public class Trie {
	int charSetCount;
	TrieNode root;

	public Trie() {
		this(26);
	}

	public Trie(int charSetCount) {
		this.charSetCount = charSetCount;
		this.root = new TrieNode(charSetCount);
	}

	public void addWord(String s) {
		if (s != null)
			root.addWord((s.toLowerCase()).toCharArray(), 0);
	}

	public void print() {
		root.print2();
	}
}