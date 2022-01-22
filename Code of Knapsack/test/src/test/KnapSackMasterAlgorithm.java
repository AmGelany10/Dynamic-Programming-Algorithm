package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class KnapSackMasterAlgorithm {
	static ArrayList<String> items = new ArrayList<>();

	public static int max(int x, int y) {
		if (x > y) {
			return x;
		} else {
			return y;
		}
	}

	public static void KnapSackMethod(int W, ArrayList<Integer> wk, ArrayList<Integer> bk, int length) {
		int towD[][] = new int[length + 1][W + 1];

		for (int i = 0; i <= length; i++) {
			for (int j = 0; j <= W; j++) {
				if (i == 0 || j == 0) {
					towD[i][j] = 0;
				} else if (wk.get(i - 1) > j) {
					towD[i][j] = towD[i - 1][j];
				}

				else {
					towD[i][j] = max(towD[i - 1][j],bk.get(i - 1) + towD[i - 1][j - wk.get(i - 1)]);
				}
			}
		}

		int res = towD[length][W];
		System.out.println("\n" + "The result = " + res + "\n");

		int w = W;
		for (int i = length; i > 0 && res > 0; i--) {

			if (res == towD[i - 1][w])
				continue;
			else {

				int index = wk.indexOf(wk.get(i - 1)) + 1;
				System.out.print("item " + "i" + index + ", W = " + wk.get(i - 1) +"."+ "\n");

				res = res - bk.get(i - 1);
				w = w - wk.get(i - 1);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the pack Weight (W) !!");
		int W = Integer.parseInt(br.readLine());
		System.out.println("\n" + "Enter the Weight of the items (wk) !!");
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Integer> wk = new ArrayList<>();
		while (st.hasMoreTokens()) {
			int x = Integer.parseInt(st.nextToken());
			wk.add(x);
		}
		System.out.println("\n" + "Enter the benefit of the items (bk) !!");
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		ArrayList<Integer> bk = new ArrayList<>();
		while (st2.hasMoreTokens()) {
			int x = Integer.parseInt(st2.nextToken());
			bk.add(x);
		}
		int length = bk.size();
		KnapSackMethod(W, wk, bk, length);

	}
}
