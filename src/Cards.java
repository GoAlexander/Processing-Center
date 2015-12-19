import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
    Date data;
    String Name;
    int pin;
    String number;
    int[] numberArr; 
    int limit;
    int cur;
    int type;
    boolean BL;
    int CVV;
    Cards(){
    numberArr= new int[16]; 
    for(int i=0;i<numberArr.length;i++)
        numberArr[i]=0;
    numberArr[0]=4;
        numberArr[1]=1;
        numberArr[2]=2;
        numberArr[3]=8;
        numberArr[4]=0;
        numberArr[5]=1;
       numberArr[6]=0;
       numberArr[7]=1;
      number="";
    BL=false;
    limit=0;
        Calendar calendar = GregorianCalendar.getInstance();
calendar.add(Calendar.DAY_OF_YEAR, 1085);
data=calendar.getTime();
    }
    int findMoon() throws Exception{
         int[] numberArrCopy=new int[16];
         for(int i=0;i<numberArr.length;i++)
             numberArrCopy[i]=numberArr[i];
        for(int i=0;i<numberArrCopy.length;i=i+2){
            numberArrCopy[i]=numberArrCopy[i]*2;
            if(numberArrCopy[i]>9)
                numberArrCopy[i]=numberArrCopy[i]-9;
        }
            int sum=0;
        for(int i=0;i<numberArrCopy.length;i++){
            sum+=numberArrCopy[i];
        }
            numberArrCopy[15]+=10-sum%10;
            if(numberArrCopy[15]>9)
                numberArrCopy[15]-=10;
            numberArr[15]= numberArrCopy[15];
            sum=0;
               for(int i=0;i<numberArrCopy.length;i++)
            sum+=numberArrCopy[i];
            if (sum%10==0)
                return numberArrCopy[15];
            else throw new Exception();
        
    }
    void calculateNumber(){
        for(int i=0;i<numberArr.length;i++){
            number+=numberArr[i];
        }
            
    }
        
            
            
    }
