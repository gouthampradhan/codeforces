package problems.codeforces;

import java.io.*;
import java.util.*;

/**
 * Created by gouthamvidyapradhan on 22/09/2017.
 */
public class AndryushaAndColoredBalloons {

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

    private static List<Integer>[] graph;
    private static int order = 0, D;
    private static int[] color;
    private static PrintWriter pw = new PrintWriter(new BufferedOutputStream(System.out));

    public static void main(String[] args) throws Exception{
        int N = MyScanner.readInt();
        color = new int[N + 1];
        graph = new List[N + 1];
        for(int i = 0; i < graph.length; i ++){
            graph[i] = new ArrayList<>();
        }
        while(N -- > 1){
            int u = MyScanner.readInt();
            int v = MyScanner.readInt();
            List<Integer> children1 = graph[u];
            List<Integer> children2 = graph[v];
            children1.add(v);
            children2.add(u);
        }
        for(List<Integer> list : graph){
            order = Math.max(order, list.size());
        }
        D = order + 1;
        color[1] = 1;
        dfs(1, 1, -1);
        pw.println(D);
        for(int i = 1; i < color.length; i ++){
            pw.println(color[i]);
        }
        pw.flush();
        pw.close();
        MyScanner.close();
    }

    private static void dfs(int u, int colorIndex, int parentColor){
        color[u] = colorIndex;
        List<Integer> children = graph[u];
        for(int c : children){
            if(color[c] == 0){
                colorIndex =  getNextColor(colorIndex);
                if(colorIndex == parentColor){
                    colorIndex =  getNextColor(colorIndex);
                }
                dfs(c, colorIndex, color[u]);
            }
        }
    }

    private static int getNextColor(int colorIndex){
        int newColorIndex = (colorIndex + 1) % (D + 1);
        if(newColorIndex == 0) {
            newColorIndex = 1;
        }
        return newColorIndex;
    }
}
