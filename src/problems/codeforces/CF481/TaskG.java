package problems.codeforces.CF481;

import java.io.*;
import java.util.*;

public class TaskG {
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

    private static class Task{
        int d; int c; int i;
        Task(int d, int c, int i){
            this.d = d;
            this.c = c;
            this.i = i;
        }
    }

    public static void main(String[] args) throws Exception{
        PriorityQueue<Task> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.d, o2.d));
        Map<Integer, List<Task>> map = new HashMap<>();
        int N = MyScanner.readInt();
        int M = MyScanner.readInt();
        int[] result = new int[N + 1];
        for(int i = 1; i <= M; i ++){
            int s = MyScanner.readInt();
            int d = MyScanner.readInt();
            int c = MyScanner.readInt();
            map.putIfAbsent(s, new ArrayList<>());
            map.get(s).add(new Task(d, c, i));
        }
        for(int day = 1; day <= N; ){
            List<Task> tasks = map.remove(day);
            if(tasks != null){
                pq.addAll(tasks);
            }
            if(!pq.isEmpty()){
                Task top = pq.peek();
                if(top.c == 0){
                    if(day == top.d){
                        result[top.d] = M + 1;
                        pq.poll();
                    } else if(day < top.d){
                        result[top.d] = M + 1;
                        pq.poll();
                        continue;
                    } else break;
                } else{
                    if(result[day] != M + 1){
                        top = pq.poll();
                        top.c--;
                        pq.offer(top);
                        result[day] = top.i;
                    }
                }
            }
            day++;
        }
        if(!pq.isEmpty()){
            pw.println(-1);
        } else{
            for(int i = 1; i < result.length; i ++){
                pw.print(result[i] + " ");
            }
            pw.println();
        }
        pw.flush();
        pw.close();
        MyScanner.close();
    }
}
