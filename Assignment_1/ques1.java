import java.util.Scanner;

public class ques1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter String: ");
        String string = input.nextLine();
        System.out.print("Enter SubString: ");
        String subString = input.nextLine();
        input.close();
        int sub_length = subString.length();
        int txt_length = string.length();
        int ans = 0;
        int j;
        for (int i = 0;i<= txt_length-sub_length;i++)
        {
            for (j = 0;j<sub_length;j++) {
                if (string.charAt(i+j)!= subString.charAt(j)) {
                    break;
                }
            }
            if (j==sub_length) {
                ans++;
                j=0;
            }
        }
        System.out.println(ans);
    }
}
