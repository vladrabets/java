package thread;

import exception.InvalidCargoState;
import exception.OverloadCargoException;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public enum Port implements Runnable {
    GENERAL;
    public final int CARGO_CAPACITY = 30;//Груз
    public final int BERTH_CAPACITY = 3;//Причалы
    public int cargo=0;//текущее кол-во груза
    public int berth=0;//текущая занятость причалов
    private LinkedList<Ship> ships = new LinkedList<>();
    private Ship[] activeShips= new Ship[3];

    public LinkedList<Ship> getShips() {
        return ships;
    }
    private void moveCargo(Ship ship) throws OverloadCargoException, InvalidCargoState {
        if (cargo == 0) {
            ship.unloadCargo();
        }
        else if (ship.cargo==0)
        {
            ship.loadCargo();
        } else
        {
            ship.unloadCargo();
        }
        ship.isBerth=false;
    }

    @Override
    public String toString() {
        return "Port{" +
                "cargo=" + cargo +
                ", berth=" + berth +
                '}';
    }
    @Override
    public void run() {
        while (true) {
            if(ships.size()!=0) {
                System.out.println("____________________________________\n____________________________________");
                int temp;
                if(ships.size()>BERTH_CAPACITY){ temp = BERTH_CAPACITY; }
                else{ temp = ships.size(); }

                for (int i = 0; i < temp; i++) {
                    System.out.println(ships.get(i));
                }
                System.out.println("___");
                for (int i = 0; i < temp; i++) {
                    if(!ships.isEmpty()) {
                        try {
                            moveCargo(ships.peekFirst());
                            ships.peekFirst().isBerth=false;
                            System.out.println(ships.pollFirst());
                        } catch (OverloadCargoException | InvalidCargoState e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                System.out.println(this);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
