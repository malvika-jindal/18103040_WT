import java.util.Scanner;

public class q1_A2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first string: ");
        String first = input.nextLine();
        System.out.print("Enter second string: ");
        String second = input.nextLine();
        input.close();
        int flag=0;
        int l=Math.min(first.length(),second.length());
        if(second.length()==first.length())
            flag=-1;
        else if(l==first.length())
            flag=1;
        for(int i=0;i<l;i++)
        {
            if(first.charAt(i)<second.charAt(i))
            {
                flag=1;
                break;
            }
            else if(first.charAt(i)>second.charAt(i))
            {
                flag=0;
                break;
            }
        }
        if(flag==-1)
            System.out.println("Both the strings are equal");
        else if(flag==1)
            System.out.println(second+ " is larger than "+ first);
        else
            System.out.println(first+ " is larger than "+ second);
        System.out.println(first.compareTo(second));
    }
}
