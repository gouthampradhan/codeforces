package problems.codeforces.CF481;

import problems.codeforces.MyScanner;
import java.io.PrintWriter;
import java.io.*;
import java.util.*;


public class TaskD {
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
        int[] A = new int[N];
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < N; i ++){
            A[i] = MyScanner.readInt();
        }
        if(N == 1 || N == 2) {
            pw.print(0);
        } else{
            int[] R = {-1, 0, 1};
            int diff = A[1] - A[0];
            int[] D = {diff, diff - 1, diff - 2, diff + 1, diff + 2};
            for(int i = 0; i < 3; i ++){
                for(int j = 0; j < 5; j ++){
                    int r = minOperations(A, D[j], A[0] + R[i]);
                    if(r != Integer.MAX_VALUE){
                        if(i == 0 || i == 2){
                            r++;
                        }
                    }
                    min = Math.min(min, r);
                }
            }
            if(min == Integer.MAX_VALUE){
                pw.print(-1);
            } else{
                pw.print(min);
            }
        }
        pw.println();
        pw.flush();
        pw.close();
        MyScanner.close();
    }

    private static int minOperations(int[] A, int d, int prev){
        int count = 0;
        for(int i = 1; i < A.length; i ++){
            if((A[i] - prev) != d){
                if((A[i] + 1 - prev) == d){
                    prev = A[i] + 1;
                    count++;
                } else if((A[i] - 1 - prev) == d){
                    count++;
                    prev = A[i] - 1;
                } else return Integer.MAX_VALUE;
            } else {
                prev = A[i];
            }
        }
        return count;
    }

}
