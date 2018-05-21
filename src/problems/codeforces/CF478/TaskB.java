package problems.codeforces.CF478;

import problems.codeforces.MyScanner;

import java.io.*;
import java.util.StringTokenizer;

public class TaskB {
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
        int[] A = new int[14];
        for(int i = 0; i < 14; i ++){
            A[i] = MyScanner.readInt();
        }
        long max = 0;
        for(int i = 0; i < 14; i ++){
            long[] temp = new long[14];
            for(int c = 0; c < A.length; c++){
                temp[c] = A[c];
            }
            int n = A[i];
            if(n > 0){
                int q = n / 14;
                int r = n % 14;
                temp[i] -= A[i];
                for(int j = 0; j < temp.length; j++){
                    temp[j] += q;
                }
                for(int k = 0, l = ((i + 1) % 14); k < r; k++, l = ((l + 1) % 14)){
                    temp[l] += 1;
                }
                long count = 0;
                for(int l = 0; l < temp.length; l++){
                    if(temp[l] != 0 && temp[l] % 2 == 0){
                        count += temp[l];
                    }
                }
                max = Math.max(count, max);
            }
        }
        pw.print(max);
        pw.flush();
        pw.close();
        MyScanner.close();
    }

}
