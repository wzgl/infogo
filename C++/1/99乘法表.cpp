#include<iostream>
#include<stdio.h>
using namespace std;

int main(){

    int i=1,j=1;
    for(;i<10;i++){
     for(j=1;j<=i;j++)
        printf("%d*%d=%-4d",i,j,i*j);
     cout<<endl;
    }
    return 0;
}
