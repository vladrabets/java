import human.Human;
import humanDestroyer.HumanDestroyer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String ...args)
    {
        System.out.println("Введите чило человек N");
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        System.out.println("1 -- Выводить список по мере удаления людей\nДругое -- не выводить список людей по мере удаления");
        int soutKey = sc.nextInt();
        System.out.println("1 -- Использовать ArrayList\nДругое -- Использовать LinkedList");
        int listKey = sc.nextInt();
        List<Human> list;
        if(listKey == 1) {
            list = new ArrayList<>();
        } else { list = new LinkedList<>(); }
        System.out.println("Исходный круг:");
        for (int i = 0; i < N ; i++) {
            list.add(new Human());
            System.out.println(list.get(i));
        }
        HumanDestroyer.GENERAL.destroy(list, soutKey);
    }
}
