package 문제집.backjoon.이중우선순위큐BJ7662;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sol {

    static Map<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/문제집/backjoon/이중우선순위큐BJ7662/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for(int test=0; test<t; test++) {
            int input = Integer.parseInt(br.readLine());

            Queue<Integer> min = new PriorityQueue<>();
            Queue<Integer> max = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순
            map = new HashMap<>();
            for(int i=0; i<input; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String op = st.nextToken();

                if(op.equals("I")) {
                    int num = Integer.parseInt(st.nextToken());
                    max.add(num);
                    min.add(num);
                    map.put(num, map.getOrDefault(num, 0)+1);
                }else {
                    int type = Integer.parseInt(st.nextToken());

                    if(map.size()==0) continue;
                    if(type == 1) { //최댓값 삭제
                        delete(max);
                    }else { // 최솟값 삭제
                        delete(min);
                    }
                }
            }

            if (map.size()==0) {
                sb.append("EMPTY\n");
            } else {
                int res = delete(max);
                sb.append(res).append(" "); // 최댓값
                if(map.size()>0) res = delete(min);
                sb.append(res).append("\n"); // 최솟값
            }
        }
        System.out.println(sb.toString());
    }

    static int delete(Queue<Integer> q) {
        int res = 0;
        while(true) {
            res = q.poll();

            int cnt = map.getOrDefault(res, 0);
            if(cnt ==0) continue;

            if(cnt ==1) map.remove(res);
            else map.put(res, cnt-1);
            break;
        }

        return res;
    }
}
