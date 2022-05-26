package 문제집.프로그래머스.카카오기출.카카오2018.압축;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * date: 22.03.02
 */

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution("TOBEORNOTTOBEORTOBEORNOT");
    }

    static class Solution {
        public int[] solution(String msg) {
            char[] alphas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
            List<String> alphaList = new ArrayList<>();
            Map<String,Integer> map = new HashMap<>();
            for(char c : alphas){
                alphaList.add(String.valueOf(c));
            }
            for(int i=0;i<alphaList.size();i++){
                map.put(alphaList.get(i),i+1);
            }
            List<Integer> resultList = new ArrayList<>();

            int p =0 ;

            while(p<msg.length()){
                StringBuilder sb = new StringBuilder();
                sb.append(msg.charAt(p));
                int dog = p+1;
                while(dog < msg.length()){
                    sb.append(msg.charAt(dog));
                    if(!map.containsKey(sb.toString())){
                        sb.setLength(sb.length()-1);
                        break;
                    }
                    dog++;
                }
                resultList.add(map.get(sb.toString()));

                if( dog <= msg.length()-1){
                    System.out.println(sb);
                    sb.append(msg.charAt(dog));
                    if(!map.containsKey(sb.toString())){
                        alphaList.add(sb.toString());
                        map.put(sb.toString(),map.size()+1);
                    }
                }
                p = dog;
            }

            int[] answer = new int[resultList.size()];
            for(int i=0;i<answer.length;i++){
                answer[i] = resultList.get(i);
            }
            return answer;
        }
    }
}
