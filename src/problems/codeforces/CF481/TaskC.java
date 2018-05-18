package problems.codeforces.CF481;

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

    private static class Pair{
      int i; long j;
        Pair(int i, long j){
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws Exception{
        int N = MyScanner.readInt();
        int M = MyScanner.readInt();
        long[] R = new long[N];
        long[] L = new long[M];
        for(int i = 0; i < N; i ++){
            R[i] = MyScanner.readLong();
        }
        long prev = 0L;
        long curr = 0L;
        int index = 0;
        for(int i = 0; i < M; i ++){
            L[i] = MyScanner.readLong();
        }
        List<Pair> result = new ArrayList<>();
        for(int i = 0; i < M; ){
            if(L[i] <= curr){
                result.add(new Pair(index, (L[i] - prev)));
                i++;
            } else{
                prev = curr;
                curr += R[index];
                index++;
            }
        }
        for(Pair p : result){
            pw.print(p.i);
            pw.print(" ");
            pw.println(p.j);
        }
        pw.flush();
        pw.close();
        MyScanner.close();
    }
}
