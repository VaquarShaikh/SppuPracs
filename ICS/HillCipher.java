import java.util.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

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
			cipherMatrix[i][0] = 0;
			for(int x = 0;x < messageLen;x++){
				cipherMatrix[i][0] += keyMatrix[i][x] * messageMatrix[x][0];
			}
			cipherMatrix[i][0] = (cipherMatrix[i][0]%26);
		}

		String cipherText ="";

		for(int i = 0;i < messageLen;i++){
			cipherText += (char)(cipherMatrix[i][0] + 65);
		}

		return cipherText;
	}
}

class HillCipher{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		// System.out.println();
		String message , key;
		System.out.println("Enter the message");
		message = br.readLine();
		System.out.println("Enter the key");
		key = br.readLine();
		Solution obj = new Solution();
		System.out.println(obj.hillCipher(message , key));
	}
}