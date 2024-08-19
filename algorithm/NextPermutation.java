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
	 * 1. ���ʺ��� Ž���Ͽ� ��ȯ��ġ(i-1) ã�� (i : �����)
	 * 2. ���ʺ��� Ž���Ͽ� ��ȯ��ġ(i-1)�� ��ȯ�� ū �� ã��(j)
	 * 3. �� ��ġ ��ȯ (i-1, j)
	 * 4. ����� ��ġ(i)���� �� �ڱ��� �������� ����
	 * @author KOREA
	 *
	 */
	private static void nextPermutation() {
		//�־��� ������ �ں��� Ž���ϸ�, �����ϴ� �κ� ã��
		int idx = N-1;
		while (idx>0 && input[idx-1]>input[idx]) idx--;
		
		//�����ϴ� �κ��� �������� �ʴ´ٸ�, ���� ������ �������� ����
		if (idx == 0) {
			return;
		}
		
		//�ش� �ε����� ��������, ��/�� ������ ����
		// ������ ���� ������ ���ڿ� ���Ͽ�, ������ ���� ������ �������� Ž���ϸ鼭 ū ���� ã��
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
