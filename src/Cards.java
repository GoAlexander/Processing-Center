/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Александр
 */
public class Cards {
    String Name;
    int pin;
    long number;
    int[] numberArr; 
    int cur;
    int type;
    boolean BL;
    Cards(){
    numberArr= new int[16]; 
    for(int i=0;i<numberArr.length;i++)
        numberArr[i]=0;
    numberArr[0]=4;
        numberArr[1]=2;
        numberArr[2]=2;
        numberArr[3]=8;
        numberArr[4]=0;
        numberArr[5]=1;
       numberArr[6]=0;
       numberArr[7]=1;
    BL=false;
    }
    void findMoon() throws Exception{
        for(int i=0;i<numberArr.length;i=i+2){
            numberArr[i]=numberArr[i]*2;
            if(numberArr[i]>9)
                numberArr[i]=numberArr[i]-9;
        }
            int sum=0;
        for(int i=0;i<numberArr.length;i++){
            sum+=numberArr[i];
        }
            numberArr[15]+=10-sum%10;
            if(numberArr[15]>9)
                numberArr[15]-=10;
            sum=0;
        for(int i=0;i<numberArr.length;i++){
            sum+=numberArr[i];
        }
            if(sum%10!=0)
                throw new Exception();
        }
        
            
            
    }
