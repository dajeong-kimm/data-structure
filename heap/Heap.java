import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * 우선순위큐 : Heap 자료구조를 이용하여 구현
 * 
 * 힙 : 최솟값 또는 최댓값을 빠르게 찾아내기 위한 완전이진트리 형태
 * 
 * 완전이진트리 : '마지막 레벨'을 제외한 모든 노드가 채워져있으면서 모든 노드가 왼쪽부터 채워져야함
 * 
 * 부모 노드는 자식 노드보다 항상 우선순위가 앞선다는 조건 --> 완전이진트리 형식으로 채워나감
 * 
 * [성질]
 * 1. 왼쪽 자식 노드 인덱스 = 부모 노드 인덱스 * 2
 * 2. 오른쪽 자식 노드 인덱스 = 부모 노드 인덱스 * 2 + 1
 * 3. 부모 노드 인덱스 : 자식 노드 인덱스 / 2
 * 
 * 
 * @author SSAFY
 *
 */
public class Heap<E> {
	private final Comparator<? super E> comparator;
	private static final int DEFAULT_CAPACITY = 10; //최소 (기본) 용적 크기
	
	private int size; //요소 개수
	
	private Object[] array;
	
	//생성자 Type 1 (초기 공간 할당 X)
	public Heap() {
		this(null);
	}
	
	public Heap(Comparator<? super E> comparator) {
		this.array = new Object[DEFAULT_CAPACITY];
		this.size = 0;
		this.comparator = comparator;
	}
	
	// 생성자 Type 2 (초기 공간 할당 O)
	public Heap(int capacity) {
		this(capacity, null);
	}
	
	public Heap(int capacity, Comparator<? super E> comparator) {
		this.array = new Object[capacity];
		this.size = 0;
		this.comparator = comparator;
	}
	
	// 받은 인덱스의 부모 노드 인덱스를 반환
	private int getParent(int index) {
		return index / 2;
	}
	
	// 받은 인덱스의 왼쪽 자식 노드 인덱스를 반환
	private int getLeftChild(int index) {
		return index * 2;
	}
	
	// 받은 인덱스의 오른쪽 자식 노드 인덱스를 반환
	private int getRightChild(int index) {
		return index * 2 + 1;
	}
	
	/**
	 * 
	 * @param newCapacity 새로운 용적 크기
	 */
	private void resize(int newCapacity) {
		// 새로 만들 배열
		Object[] newArray = new Object[newCapacity];
		
		// 새 배열에 기존에 있던 배열의 요소들을 모두 복사
		for (int i=1;i<=size;i++) {
			newArray[i] = array[i];
		}
		
		/**
		 * 현재 배열은 GC 처리를 위해 null로 처리한 뒤,  (기존 배열에 대한 참조 제거)
		 * 새 배열을 연결해준다.
		 * 
		 * GC : 참조되지 않는 객체를 자동으로 메모리에서 해제
		 */
		this.array = null;
		this.array = newArray;
	}
	
	public void add (E value) {
		// 배열 용적이 꽉 차있을 경우 용적을 두 배로 늘려준다.
		if (size + 1 == array.length) {
			resize(array.length * 2);
		}
		
		//가장 마지막에 추가 되는 위치와 넣을 값(타겟)을 넘겨줌
		siftUp(size+1, value);
		// 정상적으로 재배치가 끝나면 사이즈를 증가
		size++;
	}
	
	/**
	 * 상향 선별
	 * @param idx 추가할 노드의 인덱스
	 * @param target 재배치 할 노드
	 */
	private void siftUp(int idx, E target) {
		// comparator가 존재할 경우 comparator를 인자로 넘겨줌
		if (comparator != null) {
			siftUpComparator(idx, target, comparator);
		}
		else {
			siftUpComparable(idx, target);
		}
	}
	
	/**
	 * Comparator를 이용한 sift-up
	 * @param idx
	 * @param target
	 * @param comp
	 */
	@SuppressWarnings("unchecked")
	private void siftUpComparator(int idx, E target, Comparator<? super E> comp) {
		// root 노드보다 클 때까지만 탐색
		while (idx > 1) {
			// 삽입 노드의 부모노드 인덱스 구하기
			int parent = getParent(idx);
			
			// 부모노드의 값
			Object parentVal = array[parent];
			
			// 타겟 노드 값이 부모노드보다 크면 반복문 종료
			if (comp.compare(target, (E) parentVal) >= 0 ) {
				break;
			}
			
			/**
			 * 부모 노드가 타겟 노드보다 크므로
			 * 현재 삽입될 위치에 부모노드 값으로 교체해주고
			 * 타겟 노드의 위치를 부모노드의 위치로 변경
			 */
			array[idx] = parentVal;
			idx = parent;
		}
		
		// 최종적으로 삽입될 위치에 타겟 노드 값을 저장
		array[idx] = target;
		
	}
	
	/**
	 * 삽입할 객체의 Comparable을 이용한 sift-up
	 * @param idx
	 * @param target
	 */
	private void siftUpComparable(int idx, E target) {
		// 타겟 노드가 비교될 수 있도록 한 변수를 만든다.
		Comparable<? super E> comp = (Comparable<? super E>) target;
		
		while (idx > 1) {
			int parent = getParent(idx);
			Object parentVal = array[parent];
			
			if (comp.compareTo((E)parentVal) >= 0) {
				break;
			}
			
			array[idx] = parentVal;
			idx = parent;
		}
		
		array[idx] = comp;
	}
	
	public E remove() {
		// root가 비어있을경우
		if (array[1] == null) {
			throw new NoSuchElementException();
		}
		
		E result = (E) array[1]; // 삭제된 요소를 반환하기 위한 임시 변수
		
		E target; //타겟이 될 요소
		
		if (size == 1) {
			target = null;
		}
		else {
			target = (E) array[size];
		}
		
		array[size] = null; //타겟 노드를 비움
		
		//삭제할 노드의 인덱스와 이후 재배치 할 타겟 노드를 넘겨줌
		siftDown(1, target); //루트 노드가 삭제되므로 1을 넘겨준다.
		
		return result;
		
	}
	
	/**
	 * 
	 * @param idx 삭제할 노드의 인덱스
	 * @param target 재배치 할 노드
	 */
	private void siftDown(int idx, E target) {
		// comparator가 존재할 경우 comparator를 인자로 넘겨줌
		if (comparator != null) {
			siftDownComparator(idx, target, comparator);
		}
		else {
			siftDownComparable(idx, target);
		}
	}
	
	private void siftDownComparator(int idx, E target, Comparator<? super E> comp) {
		array[idx] = null; // 삭제할 인덱스의 노드를 삭제
		size--;
		
		int parent = idx; //삭제노드부터 시작할 부모를 가리키는 변수
		int child; //교환될 자식을 가리키는 변수
		
		//왼쪽 자식 노드의 인덱스가 요소의 개수보다 작을때까지 반복
		while ((child = getLeftChild(parent)) <= size) {
			int right = getRightChild(parent);
			
			Object childVal = array[child]; //왼쪽 자식의 값 (교환될 값)
			
			/**
			 * 오른쪽 자식 인덱스가 size를 넘지 않으면서
			 * 왼쪽 자식이 오른쪽 자식보다 큰 경우
			 * 재배치할 노드는 작은 자식과 비교해야 하므로 child와 childVal을 
			 * 오른쪽 자식으로 바꿔줌
			 */
			if (right <= size && comp.compare((E) childVal, (E) array[right]) > 0) {
				child = right;
				childVal = array[child];
			}
			
			//재배치 할 노드가 자식 노드보다 작을 경우 반복문을 종료
			if (comp.compare(target, (E) childVal) <= 0) {
				break;
			}
			
			/**
			 * 현재 부모 인덱스에 자식 노드 값을 대체해주고
			 * 부모 인덱스를 자식 인덱스로 교체
			 */
			array[parent] = childVal;
			parent = child;
		}
		
		//최종적으로 재배치되는 위치에 타겟이 된 값을 넣어줌
		array[parent] = target;
		
		/**
		 * 용적의 사이즈가 최소 용적보다는 크면서 요소의 개수가 전체 용적의 1/4일 경우
		 * 용적을 반으로 줄임 (단, 최소용적보단 커야함)
		 */
		if (array.length > DEFAULT_CAPACITY && size < array.length / 4) {
			resize(Math.max(DEFAULT_CAPACITY, array.length/2));
		}
	}
	
	private void siftDownComparable(int idx, E target) {
		Comparable<? super E> comp = (Comparable<? super E>) target;
		
		array[idx] = null;
		size--;
		
		int parent = idx;
		int child;
		
		while ((child = getLeftChild(parent)) <= size) {
			int right = getRightChild(parent);
			
			Object childVal = array[child];
			
			if (right <= size && ((Comparable<? super E>)childVal).compareTo((E)array[right]) > 0) {
				child = right;
				childVal = array[child];
			}
			
			if (comp.compareTo( (E) childVal) <= 0 ) {
				break;
			}
			
			array[parent] = childVal;
			parent = child;
		}
		
		array[parent] = comp;
		
		if (array.length > DEFAULT_CAPACITY && size < array.length / 4) {
			resize(Math.max(DEFAULT_CAPACITY, array.length/2));
		}
	}
	
	public int size() {
		return this.size;
	}
	
	public E peek() {
		if (array[1] == null) throw new NoSuchElementException();
		
		return (E)array[1];
	}
	
	public boolean isEmpty() {
		return size==0;
	}
	
	public Object[] toArray() {
		return Arrays.copyOf(array, size+1);
	}

}
