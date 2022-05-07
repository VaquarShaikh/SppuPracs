import java.util.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
import java.math.*;

class Solution{
	static long calc(long a , long b , long p){
		if(b == 1){
			return a;
		}else{
			return (((long)Math.pow(a,b))%p);
		}
	}
	void diffieHellman(long P , long G , long a , long b){
		long x , y , ka , kb;
		x = calc(G , a, P);
		y = calc(G , b, P);
		ka = calc(y ,a ,P);
		kb = calc(x ,b ,P);
		System.out.println("Secret key of Alice : " + ka);
		System.out.println("Secret key of Bob : " + kb);
	}
}

class DiffieHellman{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args)throws NumberFormatException , IOException{
		System.out.println("Enter P , G , a , b");
		long P , G , a , b;

		P = Integer.parseInt(br.readLine());
		G = Integer.parseInt(br.readLine());
		a = Integer.parseInt(br.readLine());
		b = Integer.parseInt(br.readLine());

		System.out.println("P : " + P);
		System.out.println("G : " + G);
		System.out.println();
		System.out.println("Alice's key : " + a);
		System.out.println("Bob's key : " + b);
		System.out.println();
		Solution obj = new Solution();
		obj.diffieHellman(P , G , a , b);

	}
}