import java.util.Scanner;

public class q6_A2 {
    public static void main(String[] args) {
        Scanner s= new Scanner(System.in);
        System.out.println("Enter the number:");
        long n = s.nextLong();
        if(n<=0 )
        {
            System.out.println("Invalid input--It should be a positive number ");
            System.exit(0);
        }
        else if(n>Integer.MAX_VALUE){
            System.out.println("Invalid input--It should be a positive number within the range of Integer ");
            System.exit(0);
        }
        System.out.println("Hailstone Sequence: " + n);
        while(n!=1)
        {
            if(n%2==0)
                n=n/2;
            else
                n=n*3 +1;
            System.out.print(n+" ");
        }
    }
}
