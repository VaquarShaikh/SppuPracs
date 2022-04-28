#include<bits/stdc++.h>
using namespace std;
#define mod 1000000007
#define rep(i,k,n) for(int i = k;i < n;++i)
#define pb push_back
#define mk make_pair
#define f first
#define s second


string HillCipher(string message , string key){
	int message_len = message.length();
	int key_len = key.length();

	if(sqrt(key_len) != message_len){
		return "";
	}
	int count = 0;
	int** key_matrix = new int*[message_len];
	for(int i = 0;i < message_len;i++){
		key_matrix[i] = new int[message_len];
		for(int j = 0;j < message_len;j++){
			key_matrix[i][j] = (key[count++]%65);
		}
	}
	int mess_count = 0;
	int** message_matrix = new int*[message_len];
	for(int i = 0;i < message_len ; i++){
		message_matrix[i] = new int[1];
		for(int j = 0;j < 1;j++){
			message_matrix[i][j] = (message[mess_count++]%65);
		}
	}

	int** cipherMatrix = new int*[message_len];
	for(int i = 0;i < message_len;i++){
		cipherMatrix[i] = new int[1];
		for(int j = 0;j < 1;j++){
			cipherMatrix[i][j] = 0;
			for(int x = 0;x < message_len;x++){
				cipherMatrix[i][j] += key_matrix[i][x] * message_matrix[x][j];
			}
			cipherMatrix[i][j] = (cipherMatrix[i][j]%26);
		}
	}
	string CipherText;
    for (int i = 0; i < 3; i++)
        CipherText += cipherMatrix[i][0] + 65;
	return CipherText;
}


int main(){
	string message , key;

	cout<<"Enter the message";
	cin>>message;
	cout<<"Enter the key";

	cin>>key;
	cout<<"The ciphertext is : "<<HillCipher(message , key);	
}

// GYBNQKURP