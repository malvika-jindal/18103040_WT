import java.util.*;
import java.lang.Integer;

public class Ques2 {
    public static void main(String[] args) {
        Integer num = -1;
        byte byte_value = num.byteValue();
        char char_value = (char)byte_value;
        int answer = char_value;
        System.out.println(answer);
    }
}
/*
Reason:
The range of byte is -128 to 127 and when integer of -1 is typecasted to byte it remains within the range
and byte_value is -1. Now in java character is in the form unicode which is has a range of 0 to 2^16-1. Hence when
byte_value is typecasted to char it takes the value of 2^16-1 because -1 is not in the range of char.
When char_value is changed to int, since the value can be fit in the range of int that is why no change
of value will occur here.
 */
