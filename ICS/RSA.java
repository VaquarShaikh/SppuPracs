import java.util.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
import java.math.*;


class RetValues{
	double encryptedValue;
	BigInteger decryptedValue;
	int publicKey;
	int privateKey;
	int phi;
	RetValues(double encryptedValue , BigInteger decryptedValue , int publicKey , int privateKey , int phi){
		this.encryptedValue = encryptedValue;
		this.decryptedValue = decryptedValue;
		this.publicKey = publicKey;
		this.privateKey = privateKey;
		this.phi = phi;
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
	RetValues rsaAlgorithm(int message , int p , int q){
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

		BigInteger N = BigInteger.valueOf(n);
		BigInteger C = BigDecimal.valueOf(c).toBigInteger();

		msgBack = (C.pow(d)).mod(N);
		RetValues answer = new RetValues(c , msgBack , e , d , phi);
		return answer;
	}
}

class RSA{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws NumberFormatException , IOException{
		System.out.println("Enter the message to be encrypted.");
		int message = Integer.parseInt(br.readLine());
		System.out.println("Enter the values of 'p' and 'q' respectively.");
		int p = Integer.parseInt(br.readLine());
		int q = Integer.parseInt(br.readLine());
        Solution obj = new Solution();
        RetValues res = obj.rsaAlgorithm(message , p , q);
        System.out.println("Encrypted value : " + res.encryptedValue + "\n" + "Decrypted Value : " + res.decryptedValue + "\n" + "Public Key : " + res.publicKey + "\n" + "Private Key : " + res.privateKey + "\n" + "Phi : " + res.phi);
	}
}