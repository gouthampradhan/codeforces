package problems.codeforces.CF484;

/**
 * Created by gouthamvidyapradhan on 18/05/2018.
 */
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

    /**
     *
     * @author gouthamvidyapradhan
     * Class to represent UnionFind Disjoint Set
     *
     */
    private static class UnionFind
    {
        static int[] p;
        static int[] rank;
        static int[] segment;
        /**
         * Initialize with its same index as its parent
         */
        public static void init()
        {
            for(int i=0; i<p.length; i++)
                p[i] = i;
        }
        /**
         * Find the representative vertex
         * @param i
         * @return
         */
        private static int findSet(int i)
        {
            if(p[i] != i)
                p[i] = findSet(p[i]);
            return p[i];
        }
        /**
         * Perform union of two vertex
         * @param i
         * @param j
         * @return true if union is performed successfully, false otherwise
         */
        public static boolean union(int i, int j) {
            int x = findSet(i);
            int y = findSet(j);
            if(x != y) {
                if(rank[x] > rank[y]){
                    p[y] = p[x];
                    if(segment[y] == 0){
                        segment[x]++;
                    } else{
                        segment[x] += segment[y];
                    }
                }
                else {
                    p[x] = p[y];
                    if(rank[x] == rank[y]){
                        rank[y]++; //increment the rank
                    }
                    if(segment[x] == 0){
                        segment[y]++;
                    } else{
                        segment[y] += segment[x];
                    }
                }
                return true;
            }
            return false;
        }
    }

    private static class Edge{
        int x, y, w;
        Edge(int x, int y, int w){
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }

    public static void main(String[] args) throws Exception{
        int N = MyScanner.readInt();
        List<Edge> edges = new ArrayList<>();
        for(int i = 0; i < N; i ++){
            Edge e = new Edge(i, i + 1, MyScanner.readInt());
            edges.add(e);
        }
        edges.sort((o1, o2) -> Integer.compare(o1.w, o2.w));
        UnionFind.p = new int[N + 1];
        UnionFind.rank = new int[N + 1];
        UnionFind.segment = new int[N + 1];
        UnionFind.init();
        int curr = 0;
        int max = Integer.MIN_VALUE;
        long ans = 0L;
        int count = 0;
        for(Edge e : edges){
            UnionFind.union(e.x, e.y);
            int rep = UnionFind.findSet(e.x);
            int segLen = UnionFind.segment[rep];
            if(segLen == curr){
                count++;
                if(count > max){
                    max = count;
                    ans = e.w + 1;
                }
            } else if(segLen > curr){
                count = 1;
                if(count > max){
                    max = count;
                    ans = e.w + 1;
                }
                curr = segLen;
            }
        }
        pw.println(ans);
        pw.println();
        pw.flush();
        pw.close();
        MyScanner.close();
    }
}
