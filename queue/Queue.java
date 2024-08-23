
public class Queue {
	private int maxSize;
	private int[] queueArray;
	private int front;
	private int rear;
	private int nItems;
	
	public Queue(int size) {
		this.maxSize = size;
		this.queueArray = new int[maxSize];
		this.front = 0;
		this.rear = -1;
		this.nItems = 0;
	}
	
	public void insert(int value) {
		if (isFull()) {
			System.out.println("Queue is full!");
		} else {
			if (rear == maxSize -1) rear = -1;
			queueArray[++rear] = value;
			nItems++;
		}
	}
	
	public int remove() {
		if (isEmpty()) {
			System.out.println("Queue is empty!");
			return -1;
		} else {
			int tmp = queueArray[front++];
			if (front == maxSize) front = 0;
			nItems--;
			return tmp;
		}
	}
	
	public boolean isEmpty() {
		return (nItems == 0);
	}
	
	public boolean isFull() {
		return (nItems == maxSize);
	}
}
