package problems.codeforces.CF485;

import problems.codeforces.MyScanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {
    public void solve(int testNumber, MyScanner in, PrintWriter out) {
        int N = in.nextInt();
        Ftree ft = new Ftree(N);
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < N; i ++){
            map.put(in.nextInt(), i);
        }
        for(int i = 1; i <= N; i ++){
            int p = map.get(i);
            ft.update(p, 1);
        }
        long count = 0L;
        for(int i = 1; i <= N; i ++){
            int p = map.get(i);
            count += ft.getRangeSum(p);
            ft.update(p, -1);
        }
        boolean isEven = ((count % 2) == 0);
        if((((3 * N) % 2 == 0) && isEven) ||  (((3 * N) % 2 != 0) && !isEven)){
            out.println("Petr");
        } else out.println("Um_nik");
    }

    private class Ftree{
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
            for(int i = p; i > 0; i -= (i & (-i))){
                sum += a[i];
            }
            return sum;
        }
    }
}
