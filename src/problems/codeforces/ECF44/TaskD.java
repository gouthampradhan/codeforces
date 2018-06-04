package problems.codeforces.ECF44;

import problems.codeforces.MyScanner;
import java.io.PrintWriter;

public class TaskD {
    public void solve(int testNumber, MyScanner in, PrintWriter out) {
        long N = in.nextLong();
        long H = in.nextLong();
        long val = (long)5e18;
        long nSq = new Double(Math.sqrt(val)).longValue();
        long value = binarySearch(nSq, N, H);
        out.println(getLength(value, N, H));
        out.flush();
        out.close();
    }

    private static long getLength(long value, long N, long H){
        long n = Math.min(value, H);
        long rem = N - ((value * value) - (n * (n - 1) / 2));
        long len = ((2 * value) - 1) - (n - 1);
        len += ((rem / value) + ((rem % value) > 0 ? 1 : 0));
        return len;
    }

    private static long binarySearch(long max, long N, long H){
        long l = 0, h = max;
        long ans = -1;
        while(l <= h){
            long m = l + (h - l) / 2;
            if(!check(m, N, H)){
                h = m - 1;
            } else{
                ans = m;
                l = m + 1;
            }
        }
        return ans;
    }


    private static boolean check(long value, long N, long H){
        long n = Math.min(value, H);
        long result = (value * value) - (n * (n - 1) / 2);
        return result <= N;
    }

}
