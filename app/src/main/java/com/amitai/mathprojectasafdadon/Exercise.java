package com.amitai.mathprojectasafdadon;

import java.util.Random;

public class Exercise {
    private int num1;
    private int num2;
    private int result;

    public Exercise(){

    }

    public void challenge(){
        Random random1=new Random();
        Random random2=new Random();
        num1 = random1.nextInt(9)+1;
        num2 = random2.nextInt(90)+10;
    }

    public void  multi20(){
        Random random1=new Random();
        Random random2=new Random();
        num1 = random1.nextInt(9)+1;
        num2 = random2.nextInt(10)+10;
    }

    public void multiTable(){
        Random random1=new Random();
        Random random2=new Random();
        num1 = random1.nextInt(9)+1;
        num2 = random2.nextInt(9)+1;
    }

    public boolean check(int num){
        if (num1*num2==num)
            return true;
        return false;
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }
}
