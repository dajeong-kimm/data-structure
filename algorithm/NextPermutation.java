import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NextPermutation {
	static int[] input;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		input = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		nextPermutation();

	}
	/**
	 * 1. 뒤쪽부터 탐색하여 교환위치(i-1) 찾기 (i : 꼭대기)
	 * 2. 뒤쪽부터 탐색하여 교환위치(i-1)와 교환할 큰 값 찾기(j)
	 * 3. 두 위치 교환 (i-1, j)
	 * 4. 꼭대기 위치(i)부터 맨 뒤까지 오름차순 정렬
	 * @author KOREA
	 *
	 */
	private static void nextPermutation() {
		//주어진 순열의 뒤부터 탐색하며, 증가하는 부분 찾기
		int idx = N-1;
		while (idx>0 && input[idx-1]>input[idx]) idx--;
		
		//증가하는 부분이 존재하지 않는다면, 다음 순열은 존재하지 않음
		if (idx == 0) {
			return;
		}
		
		//해당 인덱스롤 기준으로, 좌/우 지점을 나눔
		// 좌측의 제일 오른쪽 숫자에 대하여, 우측의 제일 오른쪽 지점부터 탐색하면서 큰 수를 찾음
		int big_idx = N-1;
		while (big_idx > idx && input[idx-1] > input[big_idx]) {
			big_idx--;
		}
		System.out.println("idx: "+idx);
		System.out.println("big_idx: "+big_idx);
		
		int tmp = input[idx-1];
		input[idx-1] = input[big_idx];
		input[big_idx] = tmp;
		
		Arrays.sort(input,idx,N);
		
		System.out.println(Arrays.toString(input));
	}

}
