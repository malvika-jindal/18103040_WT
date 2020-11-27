import java.util.Scanner;
import java.util.Arrays;
public class viva {
    public static String sortString(String inputString)
    {
        char tempArray[] = inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter String: ");
        String string = input.nextLine();
        System.out.print("Enter SubString: ");
        String subString = input.nextLine();
        input.close();
        int sub_length = subString.length();
        int txt_length = string.length();
        subString=sortString(subString);
        int ans = 0;
        int j;
        
        for (int i = 0;i<= txt_length-sub_length;i++)
        {
            String temp = string.substring(i,i+sub_length);
            temp = sortString(temp);
            if(subString.equals(temp))
                ans++;
        }
        System.out.println(ans);
    }
}
