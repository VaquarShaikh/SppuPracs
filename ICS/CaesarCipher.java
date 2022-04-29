import java.util.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

class Solution{
	String caesarCipher(String text , int shiftVal){
		String result = "";
		for(int i = 0;i < text.length();i++){
			if(Character.isUpperCase(text.charAt(i))){
				result += (char)(((int)text.charAt(i) + shiftVal - 65)%26 + 65);
			}else{
				result += (char)(((int)text.charAt(i) + shiftVal - 97)%26 + 97);
			}
		}
		return result;
	}
}

class CaesarCipher{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		// System.out.println();
		System.out.println("Enter the text to be encrypted");
		String text = br.readLine();
		System.out.println("Enter the shift value");
		int shiftVal = Integer.parseInt(br.readLine());
		Solution obj = new Solution();
		String cipherText = obj.caesarCipher(text , shiftVal);
		System.out.println("The entered ciphertext is : " + cipherText);
		System.out.println("The decrypted ciphertext is : " + obj.caesarCipher(cipherText , 26 - shiftVal));
	}
}