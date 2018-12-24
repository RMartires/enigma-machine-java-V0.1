package enigma.machine;



public class Rotor {

private char[][] rotor = new char[26][2];
private int num[] ={10,2,5,4,0,19,8,15,16,18,20,22,25,17,24,14,3,6,9,12,7,21,23,1,13,6,11};


public Rotor(){
    
   char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
   
    for(int i=0;i<26;i++){
        
    rotor[i][0]=alphabet[i];
    rotor[i][1]=alphabet[num[i]];
    
   }
    

}

public char getRotorForward(char x){

char key='a';
    
for(int i=0;i<26;i++){

if(Character.toUpperCase(x) ==rotor[i][0])
    key=rotor[i][1];
}

return key;
}

public char getRotorBackward(char x){

char key='a';
    
for(int i=0;i<26;i++){

if(Character.toUpperCase(x) ==rotor[i][1])
    key=rotor[i][0];
}

return key;
}

public void rotor(){

for(int i=0;i<26;i++){

System.out.print(rotor[i][0]+" ");
System.out.println(rotor[i][1]);
}

    
}

}