import java.util.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
import java.math.*;

class Solution{
	int[] key = {1,0,1,0,0,0,0,0,1,0};
	int[] P10 = {3,5,2,7,4,10,1,9,8,6};
	int[] P8 = {6,3,7,4,8,5,10,9};
	int[][] S0 = {{1,0,3,2},{3,2,1,0},{0,2,1,3},{3,1,3,2}};
	int[][] S1 = {{0,1,2,3},{2,0,1,3},{3,0,1,0},{2,1,0,3}};
	int[] EP = {4,1,2,3,2,3,4,1};
	int[] IP = {2,6,3,1,4,8,5,7};
	int[] IP_INV = {4,1,3,5,7,2,8,6};
	int[] P4 = {2,4,3,1};

	int[] key1 = new int[8];
	int[] key2 = new int[8];

	int[] shift(int[] ar , int n){
		while(n > 0){
			int temp = ar[0];
			for(int i = 0;i < ar.length - 1;i++){
				ar[i] = ar[i+1];
			}
			ar[ar.length - 1] = temp;
			n--;
		}
		return ar;
	}

	void keyGeneration(){
		int[] key_ = new int[10];
		for(int i = 0;i < 10;i++){
			key_[i] = key[P10[i] - 1];
		}

		int[] ls = new int[5];
		int[] rs = new int[5];

		for(int i = 0;i < 5;i++){
			ls[i] = key_[i];
			rs[i] = key_[i+5];
		}

		int[] ls1 = shift(ls , 1);
		int[] rs1 = shift(rs , 1);

		for(int i = 0;i < 5;i++){
			key_[i] = ls1[i];
			key_[i+5] = rs1[i];
		}

		for(int i = 0;i < 8;i++){
			key1[i] = key_[P8[i] - 1];
		}

		int[] ls2 = shift(ls , 2);
		int[] rs2 = shift(rs , 2);

		for(int i = 0;i < 5;i++){
			key_[i] = ls2[i];
			key_[i+5] = rs2[i];
		}

		for(int i = 0;i < 8;i++){
			key2[i] = key_[P8[i] - 1];
		}
		System.out.println("Key1 : ");
		for(int i = 0;i < 8;i++){
			System.out.print(key1[i] + " ");
		}System.out.println();
		System.out.println("Key2 : ");
		for(int i = 0;i < 8;i++){
			System.out.print(key2[i] + " ");
		}System.out.println();
		
	}

	String binary_(int val){
		if(val == 0){
			return "00";
		}else if(val == 1){
			return "01";
		}else if(val == 2){
			return "10";
		}else{
			return "11";
		}
	}

	int[] function_(int[] ar , int[] key_){
		int[] l = new int[4];
		int[] r = new int[4];

		for(int i = 0;i < 4;i++){
			l[i] = ar[i];
			r[i] = ar[i+4];
		}

		int[] ep = new int[8];

		for(int i = 0;i < 8;i++){
			ep[i] = r[EP[i] - 1];
		}

		for(int i = 0;i < 8;i++){
			ar[i] = (key_[i] ^ ep[i]);
		}
		int[] l1 = new int[4];
		int[] r1 = new int[4];

		for(int i = 0;i < 4;i++){
			l1[i] = ar[i];
			r1[i] = ar[i+4];
		}

		int row , col , val;
		row = Integer.parseInt("" + l1[0] + l1[3] , 2);
		col = Integer.parseInt("" + l1[1] + l1[2] , 2);
		val = S0[row][col];
		String str_1 = binary_(val);

		row = Integer.parseInt("" + r1[0] + r1[3] , 2);
		col = Integer.parseInt("" + r1[1] + r1[2] , 2);
		val = S1[row][col];
		String str_2 = binary_(val);

		int[] r_ = new int[4];
		for(int i = 0;i < 2;i++){
			char c1 = str_1.charAt(i);
			char c2 = str_2.charAt(i);

			r_[i] = Character.getNumericValue(c1);
			r_[i + 2] = Character.getNumericValue(c2);
		}

		int[] r_p4 = new int[4];
		for(int i = 0;i < 4;i++){
			r_p4[i] = r_[P4[i] - 1];
		}

		for(int i = 0;i < 4;i++){
			l[i] = l[i] ^ r_p4[i];
		}

		int[] output = new int[8];

		for(int i = 0;i < 4;i++){
			output[i] = l[i];
			output[i+4] = r[i];
		}
		return output;
	}

	int[] swap(int[] ar , int n){
		int[] l = new int[n];
		int[] r = new int[n];
		for(int i = 0;i < n;i++){
			l[i] = ar[i];
			r[i] = ar[i+n];
		}
		int[] output = new int[2*n];
		for(int i = 0;i < n;i++){
			output[i] = r[i];
			output[i+n] = l[i];
		}
		return output;
	}

	int[] encryption(int[] plaintext){
		int[] arr = new int[8];
		for(int i = 0;i < 8;i++){
			arr[i] = plaintext[IP[i] - 1];
		}
		int[] ar1 = function_(arr , key1);
		int[] afterSwap = swap(ar1 , ar1.length/2);
		int[] ar2 = function_(afterSwap , key2);

		int[] cipherText = new int[8];
		for(int i = 0;i < 8;i++){
			cipherText[i] = ar2[IP_INV[i] - 1];
		}
		return cipherText;
	}


	int[] decryption(int[] plaintext){
		int[] arr = new int[8];
		for(int i = 0;i < 8;i++){
			arr[i] = plaintext[IP[i] - 1];
		}
		int[] ar1 = function_(arr , key2);
		int[] afterSwap = swap(ar1 , ar1.length/2);
		int[] ar2 = function_(afterSwap , key1);

		int[] cipherText = new int[8];
		for(int i = 0;i < 8;i++){
			cipherText[i] = ar2[IP_INV[i] - 1];
		}
		return cipherText;
	}
	
}

class Sdes{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws NumberFormatException , IOException{
		System.out.println("Enter the plaintext : ");
		String[] plaintext = br.readLine().trim().split("");
		int[] plaintextt = new int[8];
		for(int i = 0;i < 8;i++){
			plaintextt[i] = Integer.parseInt(plaintext[i]);
		}

		System.out.println("Plaintext : ");
		for(int i = 0;i < 8;i++){
			System.out.print(plaintextt[i] + " ");
		}System.out.println();
		Solution obj = new Solution();
		obj.keyGeneration();

		int[] cipherText = obj.encryption(plaintextt);
		int[] decryptedText = obj.decryption(cipherText);

		System.out.println("cipherText : ");
		for(int i = 0;i < 8;i++){
			System.out.print(cipherText[i] + " ");
		}System.out.println();


		System.out.println("decrypted value : ");
		for(int i = 0;i < 8;i++){
			System.out.print(decryptedText[i] + " ");
		}System.out.println();
		
	}
}

// plaintext : 10010111