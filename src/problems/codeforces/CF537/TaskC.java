package problems.codeforces.CF537;

import problems.codeforces.MyScanner;

import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author gpradhan
 */
public class TaskC {
    TreeMap<Integer, Integer> treeMap = new TreeMap<>();
    Map<Integer, Integer> map = new HashMap<>();

    public void solve(int testNumber, MyScanner in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int A = in.nextInt();
        int B = in.nextInt();
        for (int i = 0; i < k; i++) {
            int next = in.nextInt() - 1;
            map.putIfAbsent(next, 0);
            map.put(next, map.get(next) + 1);
        }
        Set<Integer> set = map.keySet();
        List<Integer> list = new ArrayList<>(set);
        list.sort(Comparator.comparing(Integer::intValue));
        for (int i = 0; i < list.size(); i++) {
            int curr = list.get(i);
            if (i > 0) {
                int prev = treeMap.get(list.get(i - 1));
                int sum = prev + map.get(curr);
                treeMap.put(curr, sum);
            } else {
                treeMap.put(curr, map.get(curr));
            }
        }
        int high = 1;
        long ans = calculate(0, (high << n) - 1, A, B);
        out.println(ans);
    }

    private long calculate(int l, int h, long A, long B) {
        if (l < h) {
            Entry<Integer, Integer> upperB = treeMap.floorEntry(h);
            if (null == upperB || upperB.getKey() < l) {
                return A;
            } else {
                Entry<Integer, Integer> lowerB = treeMap.floorEntry(l - 1);
                long power = lowerB == null ? B * upperB.getValue() * (h - l + 1) :
                        B * (upperB.getValue() - lowerB.getValue()) * (h - l + 1);
                int m = l + (h - l) / 2;
                long left = calculate(l, m, A, B);
                long right = calculate(m + 1, h, A, B);
                return Math.min(power, left + right);
            }
        } else {
            if (map.containsKey(l)) {
                return B * map.get(l);
            } else {
                return A;
            }
        }
    }

}
