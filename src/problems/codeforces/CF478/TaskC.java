package problems.codeforces.CF478;

import problems.codeforces.MyScanner;
import java.io.PrintWriter;
import java.io.*;
import java.util.*;


public class TaskC {
    private static PrintWriter pw = new PrintWriter(new BufferedOutputStream(System.out, 500000));
    static class MyScanner {
        /**
         * Buffered reader
         */
        private static BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        private static StringTokenizer st;

        /**
         * Read integer
         *
         * @return
         * @throws Exception
         */
        public static int readInt() throws Exception {
            try {
                if (st != null && st.hasMoreTokens()) {
                    return parseInt(st.nextToken());
                }
                String str = br.readLine();
                if (str != null && !str.trim().equals("")) {
                    st = new StringTokenizer(str);
                    return parseInt(st.nextToken());
                }
            } catch (IOException e) {
                close();
                return -1;
            }
            return -1;
        }

        /**
         * Read integer
         *
         * @return
         * @throws Exception
         */
        public static long readLong() throws Exception {
            try {
                if (st != null && st.hasMoreTokens()) {
                    return Long.parseLong(st.nextToken());
                }
                String str = br.readLine();
                if (str != null && !str.trim().equals("")) {
                    st = new StringTokenizer(str);
                    return Long.parseLong(st.nextToken());
                }
            } catch (IOException e) {
                close();
                return -1;
            }
            return -1;
        }

        /**
         * Read line
         * @return
         * @throws Exception
         */
        public static String readLine() throws Exception
        {
            return br.readLine();
        }

        /**
         * Parse to integer
         * @param in
         * @return integer value
         */
        public static int parseInt(String in)
        {
            // Check for a sign.
            int num  = 0, sign = -1, i = 0;
            final int len  = in.length( );
            final char ch  = in.charAt( 0 );
            if ( ch == '-' )
                sign = 1;
            else
                num = '0' - ch;

            // Build the number.
            i+=1;
            while ( i < len )
                num = num*10 + '0' - in.charAt( i++ );
            return sign * num;
        }

        /**
         * Close BufferedReader
         *
         * @throws Exception
         */
        public static void close() throws Exception {
            br.close();
        }
    }

    public static void main(String[] args) throws Exception{
        int N = MyScanner.readInt();
        int Q = MyScanner.readInt();
        int[] S = new int[N];
        for(int i = 0; i < N; i ++){
            S[i] = MyScanner.readInt();
        }
        long[] K = new long[Q];
        for(int i = 0; i < Q; i ++){
            K[i] = MyScanner.readLong();
        }
        long[] sum = new long[N];
        for(int i = 0; i < N; i ++){
            sum[i] = S[i];
        }
        for(int i = 1; i < N; i ++){
            sum[i] += sum[i - 1];
        }
        long aSum = 0L;
        for(int i = 0; i < K.length; i ++){
            aSum += K[i];
            int r = binarySearch(sum, aSum);
            if(r == -1){
                pw.println(N);
                aSum = 0L;
            } else{
                if(sum[r] == aSum){
                    if(r + 1 >= N){
                        pw.println(N);
                        aSum = 0L;
                    } else{
                        pw.println(N - r - 1);
                    }
                } else {
                    pw.println(N - r);
                }
            }
        }
        pw.flush();
        pw.close();
        MyScanner.close();
    }

    private static int binarySearch(long[] S, long i){
        int l = 0, h = S.length - 1;
        int ans = -1;
        while(l <= h){
            int m = l + (h - l) / 2;
            if(S[m] >= i){
                h = m - 1;
                ans = m;
            } else{
                l = m + 1;
            }
        }
        return ans;
    }
}
