import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ques2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Paragraph: ");
        String paragraph = input.nextLine();
        System.out.print("Enter strings: ");
        String[] words_replaced = input.nextLine().split(" ");
        input.close();
        for (String word : words_replaced) {
            Pattern para = Pattern.compile("\\b" + word + "\\b");
            Matcher match = para.matcher(paragraph);
            if (match.find()) {
                StringBuilder newString = new StringBuilder();
                newString.append(word.charAt(0));
                for (int i=1; i<word.length(); i++) {
                    newString.append('*');
                }
                paragraph = match.replaceAll(newString.toString());
            }
        }
        System.out.println("Updated Paragraph: " + paragraph);
    }
}
