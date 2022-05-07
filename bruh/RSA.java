import java.util.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
import java.math.*;

class RetPair{
	double enc;
	BigInteger dec;
	RetPair(double enc  , BigInteger dec){
		this.enc = enc;
		this.dec = dec;
	}
}

class Solution{
	static int gcd(int e , int z){
		if(e == 0){
			return z;
		}else{
			return gcd(z%e , e);
		}
	}
	RetPair rsaAlgorithm(int message , int p , int q){
		int n = p*q;
		int phi = (p - 1)*(q - 1);
		int d = 0 , e;
		double c ;
		BigInteger msgBack;

		for(e = 2;e < phi;e++){
			if(gcd(e , phi) == 1){
				break;
			}
		}

		// e.d%phi == 1
		for(int i = 0;i <= 9;i++){
			int x = 1 + (phi * i);
			if(x%e == 0){
				d = x/e;
				break;
			}
		}

		c = Math.pow(message , e)%n;
		System.out.println("Encrypted value : " + c);
		BigInteger N = BigInteger.valueOf(n);
		BigInteger C = BigDecimal.valueOf(c).toBigInteger();

		msgBack = (C.pow(d)).mod(N);
		System.out.println("decrypted value : " + msgBack);
		RetPair result = new RetPair(c , msgBack);
		return result;
	}
}

class RSA{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws NumberFormatException , IOException{
		System.out.println("Enter the message to be encrypted .");
		String[] message = br.readLine().trim().split("");
		ArrayList<Integer> inputArray = new ArrayList<Integer>();
		for(int i = 0;i < message.length;i++){
			inputArray.add((int)(message[i].charAt(0)));
		}

		System.out.println("Enter the value of p :");
		int p = Integer.parseInt(br.readLine());

		System.out.println("Enter the value of q :");
		int q = Integer.parseInt(br.readLine());
		

		StringBuffer encryptedValue = new StringBuffer();
		StringBuffer decryptedValue = new StringBuffer();

		Solution obj = new Solution();

		for(int i = 0;i < inputArray.size();i++){
			RetPair answer = obj.rsaAlgorithm(inputArray.get(i) , p , q);
			char ch1 = (char)((int)(answer.enc + 0));
			char ch2 = (char)(answer.dec.intValue());
			encryptedValue.append(ch1);
			decryptedValue.append(ch2);
		}

		System.out.println("encrypted value : " + encryptedValue.toString());
		System.out.println("decrypted value : " + decryptedValue.toString());

	}
}