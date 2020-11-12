package com.company;
import java.util.Scanner;

public class Main {

    private volatile static int ans;
    private final static int MAX = 100000;
    private static int maxDivisorCount = 0;

    synchronized private static void report(int maxCountFromThread, int intWithMaxFromThread) {
        if (maxCountFromThread > maxDivisorCount) {
            maxDivisorCount = maxCountFromThread;
            ans = intWithMaxFromThread;
        }
    }

    private static class counting_DivisorsThread extends Thread {
        int min, max;
        public counting_DivisorsThread(int min, int max) {
            this.min = min;
            this.max = max;
        }
        public void run() {
            int maxDivisors = 0;
            int whichInt = 0;
            for (int i = min; i < max; i++) {
                int count = 0;
                for (int j = 1; j <= i ; j++) {
                    if ( i % j == 0 )
                        count ++;
                }
                int divisors = count;
                if (divisors > maxDivisors) {
                    maxDivisors = divisors;
                    whichInt = i;
                }
            }
            report(maxDivisors,whichInt);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberOfThreads = 0;
        while (numberOfThreads < 1 || numberOfThreads > 10) {
            System.out.print("How many threads do you want to use  (1 to 10) ?  ");
            numberOfThreads = in.nextInt();
            if (numberOfThreads < 1 || numberOfThreads > 10)
                System.out.println("Enter a number from 1 to 10 !");
        }
        System.out.println("\nCounting divisors using " + numberOfThreads + " threads...");
        long startTime = System.currentTimeMillis();
        counting_DivisorsThread[] worker = new counting_DivisorsThread[numberOfThreads];
        int integersPerThread = MAX/numberOfThreads;
        int start = 1;
        int end = start + integersPerThread - 1;
        for (int i = 0; i < numberOfThreads; i++) {
            if (i == numberOfThreads - 1) {
                end = MAX;
            }
            worker[i] = new counting_DivisorsThread( start, end );
            start = end+1;
            end = start + integersPerThread - 1;
        }
        maxDivisorCount = 0;
        for (int i = 0; i < numberOfThreads; i++)
            worker[i].start();
        for (int i = 0; i < numberOfThreads; i++) {
            while (worker[i].isAlive()) {
                try {
                    worker[i].join();
                }
                catch (InterruptedException e) {
                }
            }
        }
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("\nThe largest number of divisors " + "for numbers between 1 and " + MAX + " is " + maxDivisorCount);
        System.out.println("An integer with that many divisors is " + ans);
        System.out.println("Total elapsed time:  " + (elapsedTime/1000.0) + " seconds.\n");
    }
}
