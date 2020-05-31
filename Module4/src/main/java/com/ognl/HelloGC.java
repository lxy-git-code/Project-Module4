package com.ognl;

 class HelloGC implements Runnable  {
int a=100000;


     @Override
     public void run() {
        String.valueOf(1);
        new String().isEmpty();
     }
/* public HelloGC(int myinteger) {
        this.myinteger = myinteger;
    }

    public static void main(String[] args) {
        String str1 = "aaa";
        String str2 = "bbb";
        String str3 = "aaabbb";
        String str4 = str1 + str2;
        String str5 = "aaa" + "bbb";
        System.out.println(str3 == str4); // false or true
        System.out.println(str3 == str4.intern()); // true or false
        System.out.println(str3 == str5);
    }

    @Override
    public void run() {

    }*/
}