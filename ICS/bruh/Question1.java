import java.util.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
import java.math.*;


class Solution{
	String hillCipher(String message , String key){
		int messageLen = message.length();
		int keyLen = key.length();

		if(Math.sqrt(keyLen) != messageLen){
			return "";
		}

		int[][] messageMatrix = new int[messageLen][1];
		int[][] keyMatrix = new int[messageLen][messageLen];
		int[][] cipherMatrix = new int[messageLen][1];

		int keyCount = 0 , messageCount = 0;

		for(int i = 0;i < messageLen;i++){
			for(int j = 0;j < messageLen;j++){
				keyMatrix[i][j] = (key.charAt(keyCount++)%65);
			}
		}

		for(int i = 0;i < messageLen;i++){
			for(int j = 0;j < 1;j++){
				messageMatrix[i][j] = (message.charAt(messageCount++)%65);
			}
		}

		for(int i = 0;i < messageLen;i++){
			for(int j = 0;j < 1;j++){
				cipherMatrix[i][j] = 0;
				for(int x = 0;x < messageLen;x++){
					cipherMatrix[i][j] += keyMatrix[i][x] * messageMatrix[x][j];
				}
				cipherMatrix[i][j] = (cipherMatrix[i][j]%26);
			}
		}

		String result = "";
		for(int i = 0;i < messageLen;i++){
			result += (char)(cipherMatrix[i][0] + 65);
		}
		return result;
	}

	String caesarCipher(String message , int shiftVal){
		String result = "";
		for(int i = 0;i < message.length();i++){
			if(Character.isUpperCase(message.charAt(i))){
				result += (char)((message.charAt(i) + shiftVal - 65)%26 + 65);
			}else{
				result += (char)((message.charAt(i) + shiftVal - 97)%26 + 97);
			}
		}
		return result;
	}
}

class Question1{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws NumberFormatException , IOException{
		// System.out.println("");
		while(true){
			System.out.println("Enter 1 for hill cipher and 2 for caesar cipher .");
			int choice = Integer.parseInt(br.readLine());
			if(choice == 1){
				System.out.println("You have selected hill cipher .");
				System.out.println("Enter the message you want to encrypt");
				String message = br.readLine();
				System.out.println("Enter the value of key");
				String key = br.readLine();
				Solution obj = new Solution();
				System.out.println("The ciphertext is : " + obj.hillCipher(message , key));
				// System.out.println();
			}else if(choice == 2){
				System.out.println("You have selected caesar cipher .");
				System.out.println("Enter the message you want to encrypt");
				String message = br.readLine();
				System.out.println("Enter the shift value");
				int shiftVal = Integer.parseInt(br.readLine());
				Solution obj = new Solution();
				String cipherText = obj.caesarCipher(message , shiftVal);
				System.out.println("The ciphertext is : " + cipherText);
				System.out.println("The decrypted text is : " + obj.caesarCipher(cipherText , 26 - shiftVal));
			}else{
				break;
			}
		}
	}
}