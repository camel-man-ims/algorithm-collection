package 문제집.backjoon.합이0인네정수BJ7453;

/*
 * date: 22.06.28
 */

 import java.util.*;
 import java.io.*;

public class Main {
    static int N;

    static int[] a,b,c,d;
    static int[] ab;
    static int[] cd;
    public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new FileInputStream("./src/문제집/backjoon/합이0인네정수BJ7453/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        a = new int[N];
        b = new int[N];
        c = new int[N];
        d = new int[N];

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a1 = Integer.parseInt(st.nextToken());
            int b1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int d1 = Integer.parseInt(st.nextToken());

            a[i] = a1;
            b[i] = b1;
            c[i] = c1;
            d[i] = d1;
        }

        ab = new int[N*N];
        cd = new int[N*N];
        int p = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                ab[p] = a[i] + b[j];
                cd[p] = c[i] + d[j];
                p++;
            }
        }

        Arrays.sort(ab);
        Arrays.sort(cd);

        int abp = 0;
        int cdp = N*N-1;

        long cnt = 0;
        while(abp < N*N && cdp >= 0){
            long abv = ab[abp];
            long cdv = cd[cdp];
            long res = ab[abp] + cd[cdp];

            if(res>0){
                cdp--;
            }else if(res<0){
                abp++;
            }else{
                long count1 = 0;
                long count2 = 0;

                while(abp < N*N && ab[abp] == abv){
                    abp++;
                    count1++;
                }
                while(cdp >= 0 && cd[cdp] == cdv){
                    cdp--;
                    count2++;
                }
                cnt += count1 * count2;
            }
        }
        System.out.println(cnt);
        
    }
}
