package humanDestroyer;

import human.Human;

import java.util.List;

public enum HumanDestroyer {
    GENERAL;
    public void destroy(List<Human> list, int key)
    {
        int i = 0;//итератор для листа
        int it=0;//счетчик итераций
        while(list.size()!=1)
        {
            if(it%2==0)
            {
                list.remove(i);
                i--;
            }
            if(key==1)
            {
                System.out.println("____________________________________________");
                for (int j = 0; j <list.size() ; j++) {
                    System.out.println(list.get(j));
                }
                System.out.println(it+1+"-ая итерация");
                System.out.println("____________________________________________");
            }
            i++;
            it++;
            if(i==list.size())
            {
                i=0;
            }
        }
    }
}
