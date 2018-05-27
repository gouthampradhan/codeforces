package problems.codeforces.ECF41;

import problems.codeforces.MyScanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskE {
    public void solve(int testNumber, MyScanner in, PrintWriter out) {
        int N = in.nextInt();
        int[] a = new int[N];
        Ftree ft = new Ftree(N);
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < N; i ++){
            int v = in.nextInt();
            a[i] = (v > N) ? N - 1: (v - 1);
            ft.update(i, 1);
            map.putIfAbsent(a[i], new ArrayList<>());
            map.get(a[i]).add(i);
        }
        long ans = 0L;
        for(int i = 0; i < N; i ++){
            ans += ft.getRangeSum(a[i]);
            List<Integer> list = map.get(i);
            if(list != null){
                for(int e : list){
                    ft.update(e, -1);
                }
            }
        }
        for(int i = 0; i < N; i++){
            if(a[i] >= i){
                ans--;
            }
        }
        out.print(ans / 2);
        out.flush();
        out.close();
    }

    class Ftree{
        private int[] a;
        Ftree(int n){
            a = new int[n + 1];
        }

        void update(int p, int v){
            for(int i = p + 1; i < a.length; i += (i & (-i))){
                a[i] += v;
            }
        }

        long getRangeSum(int p){
            long sum = 0L;
            for(int i = p + 1; i > 0; i -= (i & (-i))){
                sum += a[i];
            }
            return sum;
        }
    }
}
