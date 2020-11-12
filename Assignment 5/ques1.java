package com.company;

public class Main {
    static class MyThread extends Thread {
        public void run() {
            for (int i = 1; i <= 100; i++) {
                if (i % 10 == 0)
                    System.out.println(i);
                try {
                    //pausing one second between each number
                    Thread.sleep(1000);
                } catch (Exception error) {
                }
            }
        }
    }
    public static void main(String[] args) {
        MyThread a = new MyThread();
        a.start();
    }
}
