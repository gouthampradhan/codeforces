package problems.codeforces.CF481;

import java.io.PrintWriter;
import java.io.*;
import java.util.*;

public class TaskF {
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
        int K = MyScanner.readInt();
        int[] skills = new int[N];
        int[] quarrel = new int[N];
        for(int i = 0; i < N; i ++){
            int s =  MyScanner.readInt();
            skills[i] = s;
        }
        for(int i = 0; i < K; i ++){
            int u =  MyScanner.readInt();
            int v =  MyScanner.readInt();
            if(skills[u - 1] > skills[v - 1]){
                quarrel[u - 1]++;
            } else if(skills[v - 1] > skills[u - 1]){
                quarrel[v - 1]++;
            }
        }
        List<Integer> temp = new ArrayList<>();
        for(int s : skills){
            temp.add(s);
        }
        Collections.sort(temp);
        for(int i = 0; i < N; i ++){
            int s = skills[i];
            int index = binarySearch(temp, s);
            if(index > 0){
                pw.println(index - quarrel[i]);
            } else{
                pw.println(0);
            }
        }
        pw.flush();
        pw.close();
        MyScanner.close();
    }

    private static int binarySearch(List<Integer> S, int i){
        int l = 0, h = S.size() - 1;
        int ans = -1;
        while(l <= h){
            int m = l + (h - l) / 2;
            if(S.get(m) >= i){
                h = m - 1;
                ans = m;
            } else{
                l = m + 1;
            }
        }
        return ans;
    }
}
