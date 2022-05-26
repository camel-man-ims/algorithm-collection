package 문제집.backjoon.인구이동;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * date : 22.01.14
 * 틀렸던 이유 :
 *
 * union - find 식으로 연결하려 했을 때, 위에서 설정한 값이 아래에서 덮어씌워 질 때 같은 union 으로 합쳐져야 되지만, 다르게 인식 된다.
 * ex)
 * map)
 * 0 16 0
 * 16 16 16
 * 16 16 40
 *
 * parent)
 * 0 0 0
 * 0 4 7
 * 6 7 7
 *
 * parent 에서 0과 7은 같지만, 다르게 합쳐진다.
 *
 * tc 참조 : https://minhamina.tistory.com/115
 * tc 참조2 : https://codecollector.tistory.com/911
 *
 * union - find 이용
 *
 */

public class Main {
    static int N,L,R;
    static int[][] map;
    static int[] parents;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int[][] parentMap;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/인구이동/input.txt"));

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();
        map = new int[N][N];
        parentMap = new int[N][N];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j]= sc.nextInt();
            }
        }
        int result = 0;
        while(!isStop()){
            int cnt = 0;
            setParentMap(cnt);
            setParents();
            result++;
            // union - find 설정
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    for(int d=0;d<4;d++){
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if(isOut(nx,ny)) continue;
                        int diff = Math.abs(map[i][j] - map[nx][ny]);
                        if( diff >= L && diff <= R){
                            union(parentMap[i][j],parentMap[nx][ny]);
                            parentMap[nx][ny] = find(parentMap[i][j]);
                        }
                    }
                }
            }

            // union - find 로 동기화 안 돼있는 애들 있을 수 있기에, 동기화 시켜줌
            // ex)
            // 0 4 0
            // 0 5 5
            // 5 5 8
            // 4-5 같은 놈
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    parentMap[i][j] = find(parentMap[i][j]);
                }
            }

            // 더해주고 나눠주고 갱신
            int[] sumArr = new int[N*N];
            int[] countArr = new int[N*N];

            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    countArr[parentMap[i][j]]++;
                    sumArr[parentMap[i][j]] += map[i][j];
                }
            }
            for(int i=0;i<N*N;i++){
                if(countArr[i] == 0) sumArr[i] = 0;
                else sumArr[i] /= countArr[i];
            }
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(sumArr[parentMap[i][j]] !=0) map[i][j] = sumArr[parentMap[i][j]];
                }
            }
        }


        System.out.println(result);
    }

    static void setParents(){
        parents = new int[N*N];
        for(int i=0;i<N*N;i++){
            parents[i] = i;
        }
    }

    static int find(int a){
        if(parents[a]==a) return a;
        else return parents[a] = find(parents[a]);
    }
    static void union(int a,int b){
        int aRoot = find(a);
        int bRoot = find(b);
        parents[aRoot] = bRoot;
    }

    private static void setParentMap(int cnt) {
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                parentMap[i][j] = cnt++;
            }
        }
    }

    static boolean isStop(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                for(int d=0;d<4;d++){
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if(isOut(nx,ny)) continue;
                    if(Math.abs(map[i][j] - map[nx][ny]) >= L && Math.abs(map[i][j] - map[nx][ny]) <= R){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static void print(int[][] printMap){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(printMap[i][j] + " ");
            }
            System.out.println();
        }
    }
    static boolean isOut(int nx, int ny){
        return nx<0 || ny<0 || nx>=N || ny>=N;
    }
}
