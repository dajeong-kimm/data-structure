
public class Stack {
	private int maxSize;
	private int[] stackArray;
	private int top;
	
	public Stack(int size) {
		this.maxSize = size;
		this.stackArray = new int[maxSize];
		this.top = -1;
	}
	
	public void push(int value) {
		if (isFull()) {
			System.out.println("Stack is full!");
		} else {
			stackArray[++top] = value;
		}
	}
	
	public int pop() {
		if (isEmpty()) {
			System.out.println("Stack is empty!");
			return -1;
		} else {
			return stackArray[top--];
		}
	}
	
	public int peek() {
		return stackArray[top];
	}
	
	public boolean isEmpty() {
		return (top == -1);
	}
	
	public boolean isFull() {
		return (top == maxSize-1);
	}

}
