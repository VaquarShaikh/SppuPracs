#include<bits/stdc++.h>
using namespace std;
#define mod 1000000007
#define rep(i,k,n) for(int i = k;i < n;++i)
#define pb push_back
#define mk make_pair
#define f first
#define s second


string HillCipher(string message , string key){
	// Convert the keystring to matrix .
	// Convert the message string to matrix .
	// Multiply the given matrices .
	// modulo 26 to the result .
	int message_len = message.length();
	int key_len = key.length();

	if(sqrt(key_len) != message_len){
		return "";
	}


	int keyCount = 0;

	int keyMatrix[message_len][message_len];
	for(int i = 0;i < message_len;i++){
		for(int j = 0;j < message_len;j++){
			keyMatrix[i][j] = (key[keyCount++]%65);
		}
	}

	int messageCount = 0;
	int messageMatrix[message_len][1];
	for(int i = 0;i < message_len;i++){
		for(int j = 0;j < 1;j++){
			messageMatrix[i][j] = (message[messageCount++]%65);
		}
	}

	int cipherMatrix[message_len][1];

	for(int i = 0;i < message_len;i++){
		for(int j = 0;j < 1;j++){
			cipherMatrix[i][j] = 0;
			for(int x = 0;x < message_len;x++){
				cipherMatrix[i][j] += keyMatrix[i][x] * messageMatrix[x][j];
			}
			cipherMatrix[i][j] = (cipherMatrix[i][j]%26);
		}
	}

	string ciphertext;

	for(int i = 0;i < message_len;i++){
		ciphertext += cipherMatrix[i][0] + 65;
	}

	return ciphertext;

}


int main(){
	string message , key;
	cout<<"Enter the message"<<endl;
	cin>>message;
	cout<<"Enter the key";
	cin>>key;

	cout<<HillCipher(message , key);

	// cout<<"The ciphtertext is : "<<HillCipher(message , key)<<endl;	
}