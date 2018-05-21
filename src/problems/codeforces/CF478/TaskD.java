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
            cantMeet.putIfAbsent(Vy + " " + Vx, 0L);
            result += (count.get((Vy - (a * Vx))) - cantMeet.get(Vy + " " + Vx));
            count.put((Vy - (a * Vx)), count.get((Vy - (a * Vx))) + 1);
            cantMeet.put(Vy + " " + Vx, cantMeet.get(Vy + " " + Vx) + 1);
        }
        out.println(result * 2);
        out.flush();
        out.close();
    }
}
