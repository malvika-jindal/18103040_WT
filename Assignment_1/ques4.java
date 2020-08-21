import java.util.Scanner;
import java.util.Arrays;
public class ques4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first string: ");
        String first = input.nextLine();
        System.out.print("Enter second string: ");
        String second = input.nextLine();
        input.close();

        char[] first_array = first.toCharArray();
        char[] second_array = second.toCharArray();
        Arrays.sort(first_array);
        Arrays.sort(second_array);
         if(Arrays.equals(first_array, second_array))
         {
             System.out.println("Strings are Anagrams");
         }
         else
         {
             System.out.println("Strings are not Anagrams");
         }
    }
}
