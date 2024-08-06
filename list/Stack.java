package cote;

class Node<T>{
	T data;
	Node<T> next;
	
	public Node(T data) {
		this.data = data;
		this.next = null;
	}
}

public class Stack<T>{
	private Node<T> top;
	
	public Stack() {
		this.top = null;
	}
	
	//스택에 요소 추가
	public void push(T data) {
		Node<T> newNode = new Node<>(data);
		newNode.next = top;
		top = newNode;
	}
	
	//스택에서 요소 제거 및 반환
	public T pop() {
		if (isEmpty()) {
			throw new RuntimeException("Stack is Empty");
		}
		T data = top.data;
		top = top.next;
		return data;
		
	}
	
	//스택의 맨 위 요소 반환 (제거하지 않음)
	public T peek() {
		if (isEmpty()) {
			throw new RuntimeException("Stack is Empty");
		}
		return top.data;
	}
	
	//스택이 비어 있는지 확인
	public boolean isEmpty() {
		return top==null;
	}
	
	
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(10);
		stack.push(20);
		stack.push(30);
		
		System.out.println(stack.pop()); // 30
        System.out.println(stack.peek()); // 20
        System.out.println(stack.pop()); // 20
        System.out.println(stack.isEmpty()); // false
        System.out.println(stack.pop()); // 10
        System.out.println(stack.isEmpty()); // true
	}
}