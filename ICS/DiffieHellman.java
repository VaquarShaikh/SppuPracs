import java.util.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

class Solution{
	long ka , kb;
	static long calc(long a , long b , long p){
		if(b == 1){
			return a;
		}else{
			return(((long)Math.pow(a,b))%p);
		}
	}

	void diffieHellman(long P , long G , long a , long b){
		long x , y , ka , kb;
		System.out.println("Private key for alice : " + a);
		System.out.println("Private key for bob : " + b);
		x = calc(G , a , P);
		y = calc(G , b , P);

		// System.out.println("Private");
		System.out.println();
		ka = calc(y , a , P);
		kb = calc(x , b , P);

		System.out.println("Secrect key for alice : " + ka);
		System.out.println("Secret key for bob : " + kb);
	}
}

class DiffieHellman{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws NumberFormatException , IOException{
		System.out.println("Enter the values of P and G");
		long P , G , a , b;

		P = Integer.parseInt(br.readLine());
		G = Integer.parseInt(br.readLine());

		System.out.println("Enter private key for alice");
		a = Integer.parseInt(br.readLine());
		System.out.println("Enter private key for bob");
		b = Integer.parseInt(br.readLine());

		Solution obj = new Solution();
		obj.diffieHellman(P,G,a,b);
		// System.out.println(((Object)P).getClass().getSimpleName());
	}
}