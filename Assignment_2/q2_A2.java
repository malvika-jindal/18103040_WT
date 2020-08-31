import java.util.Arrays;
import java.util.Scanner;

public class q2_A2 {
    public static void main(String[] args) {
        Scanner s= new Scanner(System.in);
        System.out.println("Enter the length of the array:");
        int length = s.nextInt();
        int [] myArray = new int[21];
        System.out.println("Enter the elements of the array( The input must be between 0 to 20):");
        int x;
        for(int i=0; i<length; i++ )
        {
            x=s.nextInt();
            if(x>=0 && x<=20) {
                myArray[x]++;
            }
            else {
                System.out.println("Invalid Input");
                System.exit(0);
            }
        }
        System.out.println("Sorted array after counting sort: ");
        for(int i=0;i<=20;i++)
        {
            for(int j=0;j<myArray[i];j++)
            {
                System.out.print(i + " ");
            }
        }
    }
}
