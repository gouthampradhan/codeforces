package problems.codeforces.ECF44;

import problems.codeforces.MyScanner;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskE {

    private static class Node{
        int i, v;
        Node(int i, int v){
            this.i = i;
            this.v = v;
        }
    }

    public void solve(int testNumber, MyScanner in, PrintWriter out) {
        int N = in.nextInt();
        int K = in.nextInt();
        int D = in.nextInt();
        if(K == 1 || N == 1) out.println("YES");
        else{
            List<Integer> A = new ArrayList<>();
            List<Node> search = new ArrayList<>();
            for(int i = 0; i < N; i ++){
                A.add(in.nextInt());
            }
            Collections.sort(A);
            boolean[] dp = new boolean[N];
            search.add(new Node(0, A.get(0)));
            for(int i = 1, l = A.size(); i < l; i ++){
                int e = A.get(i);
                int d = D >= e ? 0 : e - D;
                int index = binarySearch(search, d);
                if(index != -1){
                    if((i - index + 1) >= K){
                        dp[i] = true;
                    }
                }
                if(dp[i - 1]){
                    search.add(new Node(i, e));
                }
            }
            if(dp[N - 1]){
                out.println("YES");
            }
            else out.println("NO");
        }
        out.flush();
        out.close();
    }

    private static int binarySearch(List<Node> A, int V) {
        int l = 0, h = A.size() - 1;
        int ans = -1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            Node node = A.get(m);
            if (node.v >= V) {
                ans = node.i;
                h = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }
}
