package datastructure;

public class LMap<K, V> {

	private int size = 0;
	
	private int capacity = 16;
	
	private Node[] nodes;
	
	public LMap() {
//		nodes = new Node[capacity];
	}

	public LMap(int capacity) {
		this.capacity = capacity;
	}
	
	public static void main(String[] args) {
		
	}
	
	class Node {
		public Node next;
		public K k;
		public V v;
	}
	
}
