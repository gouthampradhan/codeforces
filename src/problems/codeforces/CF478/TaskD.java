package problems.codeforces.CF478;

import problems.codeforces.MyScanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskD {
    public void solve(int testNumber, MyScanner in, PrintWriter out) {
        int N = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        Map<Long, Long> count = new HashMap<>();
        Map<String, Long> cantMeet = new HashMap<>();
        long result = 0L;
        for (int i = 0; i < N; i++) {
            int x = in.nextInt();
            long Vx = in.nextLong();
            long Vy = in.nextLong();
            count.putIfAbsent((Vy - (a * Vx)), 0L);
            String vStr = Vy + " " + Vx;
            cantMeet.putIfAbsent(vStr, 0L);
            result += (count.get((Vy - (a * Vx))) - cantMeet.get(vStr));
            count.put((Vy - (a * Vx)), count.get((Vy - (a * Vx))) + 1);
            cantMeet.put(vStr, cantMeet.get(vStr) + 1);
        }
        out.println(result * 2);
        out.flush();
        out.close();
    }
}
