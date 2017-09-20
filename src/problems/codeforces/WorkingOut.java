package problems.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by gouthamvidyapradhan on 20/09/2017.
 */
public class WorkingOut {
    /**
     * Scanner class
     *
     * @author gouthamvidyapradhan
     */
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
                if(str == null) return -2;
                if (!str.trim().equals("")) {
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

    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        int M = MyScanner.readInt();
        int N = MyScanner.readInt();
        int[][] G = new int[M][N];
        int[][] dp1 = new int[M][N];
        int[][] dp2 = new int[M][N];
        int[][] dp3 = new int[M][N];
        int[][] dp4 = new int[M][N];

        for(int i = 0; i < M; i ++){
            for(int j = 0; j < N; j ++){
                G[i][j] = MyScanner.readInt();
            }
        }
        for(int i = 0; i < M; i ++){
            for(int j = 0; j < N; j ++){
                if(j > 0) dp1[i][j] = Math.max(dp1[i][j], dp1[i][j - 1]);
                if(i > 0) dp1[i][j] = Math.max(dp1[i][j], dp1[i - 1][j]);
                dp1[i][j] += G[i][j];
            }
        }

        for(int i = M - 1; i >= 0; i --){
            for(int j = 0; j < N; j ++){
                if(j > 0) dp2[i][j] = Math.max(dp2[i][j], dp2[i][j - 1]);
                if(i + 1 < M) dp2[i][j] = Math.max(dp2[i][j], dp2[i + 1][j]);
                dp2[i][j] += G[i][j];
            }
        }

        for(int i = 0; i < M; i ++){
            for(int j = N - 1; j >= 0; j --){
                if(j + 1 < N) dp3[i][j] = Math.max(dp3[i][j], dp3[i][j + 1]);
                if(i > 0) dp3[i][j] = Math.max(dp3[i][j], dp3[i - 1][j]);
                dp3[i][j] += G[i][j];
            }
        }

        for(int i = M - 1; i >= 0; i --){
            for(int j = N - 1; j >= 0; j --){
                if(j + 1 < N) dp4[i][j] = Math.max(dp4[i][j], dp4[i][j + 1]);
                if(i + 1 < M) dp4[i][j] = Math.max(dp4[i][j], dp4[i + 1][j]);
                dp4[i][j] += G[i][j];
            }
        }

        int max = Integer.MIN_VALUE;

        for(int i = 1; i < M - 1; i ++){
            for(int j = 1; j < N - 1; j ++){
                max = Math.max(max, dp1[i - 1][j] + dp4[i + 1][j] + dp2[i][j - 1] + dp3[i][j + 1]);
                max = Math.max(max, dp1[i][j - 1] + dp4[i][j + 1] + dp2[i + 1][j] + dp3[i - 1][j]);
            }
        }

        System.out.println(max);
    }
}
