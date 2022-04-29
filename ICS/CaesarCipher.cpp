#include<bits/stdc++.h>
using namespace std;
#define mod 1000000007
#define rep(i,k,n) for(int i = k;i < n;++i)
#define pb push_back
#define mk make_pair
#define f first
#define s second

string encryptCaesarCipher(string text , int shiftVal){
	string result = "";
	for(int i = 0;i < text.length();i++){
		if(isupper(text[i])){
			result += char(int(text[i] + shiftVal - 65)%26 + 65);
		}else{
			result += char(int(text[i] + shiftVal - 97)%26 + 97);
		}
	}
	return result;
}

int main(){
	string text;
	int shiftVal;

	cout<<"Enter the text to be encrypted";
	cin>>text;
	cout<<"Enter the shiftValue";
	cin>>shiftVal;
	string cipherText = encryptCaesarCipher(text , shiftVal);
	cout<<"The ciphertext is : "<<cipherText<<endl;	
	cout<<"The decrypted ciphertext is : "<<encryptCaesarCipher(cipherText ,26-shiftVal);	
}