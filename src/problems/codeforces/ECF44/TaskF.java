package problems.codeforces.ECF44;

import problems.codeforces.MyScanner;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskF {
    private static boolean[][] A = new boolean[26][200001];
    private static RollingHash[] R = new RollingHash[26];
    private static final int mod = (int)(1e9 + 7);
    public void solve(int testNumber, MyScanner in, PrintWriter out) {
        int N = in.nextInt();
        int Q = in.nextInt();
        String S = in.next();
        for(int i = 0; i < N; i ++){
            A[S.charAt(i) - 'a'][i] = true;
        }
        for(int i = 0; i < 26; i ++){
            R[i] = new RollingHash(A[i], 3, mod);
        }
        for(int i = 0; i < Q; i ++){
            long[] H1 = new long[26];
            long[] H2 = new long[26];
            int a = in.nextInt();
            int b = in.nextInt();
            int l = in.nextInt();
            int c = 0;
            for(RollingHash r : R){
                H1[c] = r.hash(a - 1, a - 1 + l - 1);
                H2[c++] = r.hash(b - 1, b - 1 + l - 1);
            }
            Arrays.sort(H1);
            Arrays.sort(H2);
            out.println(Arrays.equals(H1, H2) ? "YES" : "NO");
        }
    }

    private static class RollingHash{
        boolean[] B;
        char[] C;
        int constant, mod;
        long[] H, P;
        RollingHash(boolean[] B, int constant, int mod){
            this.B = B;
            this.constant = constant;
            this.mod = mod;
            H = new long[B.length];
            P = new long[B.length];
            init();
        }

        RollingHash(char[] C, int constant, int mod){
            this.C = C;
            this.constant = constant;
            this.mod = mod;
            H = new long[C.length];
            P = new long[C.length];
        }

        private void init(){
            H[0] = B[0] ? 1 : 0;
            P[0] = 1L;
            for(int i = 1; i < H.length; i ++){
                long mul = ((H[i - 1] * constant) % mod);
                H[i] = ((mul + (B[i] ? 1 : 0)) % mod);
                P[i] = ((P[i - 1] * constant) % mod);
            }
        }

        long hash(int i, int j){
            if((i - 1) < 0) return H[j];
            return (((H[j] + mod) - ((H[i - 1] * P[j - i + 1]) % mod)) % mod);
        }
    }
}
