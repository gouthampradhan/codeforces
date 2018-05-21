package problems.codeforces.CF478;

import problems.codeforces.MyScanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {
    public void solve(int testNumber, MyScanner in, PrintWriter out) {
        Set<String> mainSet = new HashSet<>();
        int N = in.nextInt();
        for(int i = 0; i < N; i ++){
            String s = in.next();
            Set<Character> set = new HashSet<>();
            for(char c : s.toCharArray()){
                set.add(c);
            }
            List<Character> list = new ArrayList<>();
            list.addAll(set);
            Collections.sort(list);
            StringBuilder sb = new StringBuilder();
            for(char c : list){
                sb.append(c);
            }
            mainSet.add(sb.toString());
        }
        out.println(mainSet.size());
    }
}
