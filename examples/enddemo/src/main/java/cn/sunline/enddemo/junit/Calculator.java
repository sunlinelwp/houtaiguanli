package cn.sunline.enddemo.junit;

public class Calculator {
	public int add(int num1,int num2){  
        return num1+num2;  
    }  
      
    public int sub(int num1,int num2){  
        return num1-num2;  
    }  
      
    public int mul(int num1,int num2){  
        return num1*num2;  
    }  
      
    public int div(int num1,int num2){  
        if(num2==0){  
            throw new MyException();  
        }  
          
        return num1/num2;  
    } 
}
