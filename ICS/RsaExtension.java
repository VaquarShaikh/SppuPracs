import java.util.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
import java.math.*;


class RetPair{
	double enc;
	BigInteger dec;
	RetPair(double enc ,BigInteger dec){
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
		StringBuffer res = new StringBuffer();
		int n = p*q;
		double c;
		BigInteger msgBack;
		int phi = (p - 1)*(q - 1);
		int e;
		int d = 0;

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
		System.out.println("ciphertext : " + c);
		BigInteger N = BigInteger.valueOf(n);
		BigInteger C = BigDecimal.valueOf(c).toBigInteger();

		msgBack = (C.pow(d)).mod(N);
		System.out.println("decrypted : " + msgBack);
		// res.append('a');
		RetPair ans = new RetPair(c , msgBack);
		return ans;
	}
}


class RsaExtension{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws NumberFormatException , IOException{
		System.out.println("Enter the message to be encrypted.");
		String[] message = br.readLine().trim().split("");
		System.out.println("Enter the values of 'p' and 'q' respectively.");
		int p = Integer.parseInt(br.readLine());
		int q = Integer.parseInt(br.readLine());

		ArrayList<Integer> inputArray = new ArrayList<Integer>();

		for(int i = 0;i < message.length;i++){
			inputArray.add((int)(message[i].charAt(0))%97);
		}

		System.out.println("The input array in mapping 0-25 (ends included)");
		for(int i = 0;i < inputArray.size();i++){
			System.out.println(inputArray.get(i));
		}

		StringBuffer encryptedValue = new StringBuffer();
		StringBuffer decryptedValue = new StringBuffer();

		Solution obj = new Solution();

		for(int i = 0;i < inputArray.size();i++){
			RetPair answer = obj.rsaAlgorithm(inputArray.get(i) , p , q);
			char ch = (char)((int)(answer.enc + 97));
			char ch1 = (char)(answer.dec.intValue() + 97);
			encryptedValue.append(ch);
			decryptedValue.append(ch1);
		}

		System.out.println("ciphertext is : " + encryptedValue.toString());
		System.out.println("decrypted text is : " + decryptedValue.toString());

	}
}