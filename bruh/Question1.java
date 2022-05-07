import java.io.*;
import java.util.*;
import java.util.Arrays;
import java.util.Scanner;
import java.math.*;

class Solution{
	StringBuffer hillCipher(String message , String key){
		int messageLen = message.length();
		int keyLen = key.length();

		if(Math.sqrt(keyLen) != messageLen){
			StringBuffer res = null;
			return res;
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
				cipherMatrix[i][j] = cipherMatrix[i][j]%26;
			}
		}

		StringBuffer result = new StringBuffer();

		for(int i = 0;i < messageLen;i++){
			result.append((char)(cipherMatrix[i][0] + 65));
		}

		return result;
		
	}

	StringBuffer caesarCipher(String message , int shiftVal){
		StringBuffer result = new StringBuffer();
		for(int i = 0;i < message.length();i++){
			if(Character.isUpperCase(message.charAt(i))){
				char ch = (char)((int)(message.charAt(i) + shiftVal - 65)%26 + 65);
				result.append(ch);
			}else{
				char ch = (char)((int)(message.charAt(i) + shiftVal - 97)%26 + 97);
				result.append(ch);
			}
		}
		return result;
	}
}

class Question1{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException , NumberFormatException{
		// System.out.println()
		// System.out.print()
		// for(int i = 0;i < n;i++)
		// System.out.println("abcd");
		while(true){
			System.out.println("Enter one for hill and two for caesar cipher");
			int num = Integer.parseInt(br.readLine());
			if(num == 1){
				System.out.println("You have selected hill cipher");
				System.out.println("Enter the message.");
				String message = br.readLine();
				System.out.println("Enter the key");
				String key = br.readLine();
				Solution obj = new Solution();
				StringBuffer cipherText = obj.hillCipher(message , key);
				System.out.println("ciphertext is : " + cipherText);
			}else if(num == 2){
				System.out.println("You have chosen caesar cipher .");
				System.out.println("Enter the message to be encrypted .");
				String message = br.readLine();
				System.out.println("Enter the shift value");
				int shiftVal = Integer.parseInt(br.readLine());
				Solution obj = new Solution();
				String cipherText = obj.caesarCipher(message , shiftVal).toString();
				System.out.println("ciphertext : " + cipherText);
				System.out.println("decrypted value : " + obj.caesarCipher(cipherText, 26 - shiftVal).toString());
			}else{
				break;
			}
		}
	}
}