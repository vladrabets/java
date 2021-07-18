import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите число");
        String number = sc.next();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < number.length() ; i++) {
            stack.push(number.charAt(i));
        }
        int n = stack.size();
        System.out.println("Перевернутое число: ");
        for (int i = 0; i < n ; i++) {
            System.out.print(stack.pop());
        }
    }
}
