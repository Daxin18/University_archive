import java.util.Scanner;

public class TEST
{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String t = "1234567890";
        t = t.substring(t.length(), 0);
        System.out.println(t);
    }
}
