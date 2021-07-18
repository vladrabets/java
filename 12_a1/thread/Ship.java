package thread;

import exception.InvalidCargoState;
import exception.OverloadCargoException;

import java.util.LinkedList;

public class Ship implements Runnable {
    public final int Cargo_CAPACITY = 10;
    public int cargo=10;
    boolean isBerth=false;//заняло ли очередь на причаливание
    boolean isLoading=false;
    private String name;
    public Ship(String name)
    {
        this.name = name;
    }
    private void moor()//занять очередь на причаливание
    {
        System.out.println(Thread.currentThread().getName()+" has moored");
        LinkedList<Ship> ships = Port.GENERAL.getShips();
        ships.addLast(this);
        isBerth=true;
    }

    @Override
    public String toString() {
        return "Ship{" + "Name=" + name +
                ", cargo=" + cargo +
                '}';
    }

    public void unloadCargo() throws OverloadCargoException//выгрузка из корабля
    {
        int tempCargo;//Переменная в которой сохранен объем разгружаемого груза на случай если что-то пойдет не так, можно будет обратить операцию
        if(Port.GENERAL.CARGO_CAPACITY>=Port.GENERAL.cargo+cargo)
        {
            tempCargo=cargo;
            Port.GENERAL.cargo=Port.GENERAL.cargo + tempCargo;//
            cargo=0;
        } else
        {
            tempCargo=Port.GENERAL.CARGO_CAPACITY-Port.GENERAL.cargo;
            cargo=cargo-tempCargo;
            Port.GENERAL.cargo=+tempCargo;
        }
        if(Port.GENERAL.CARGO_CAPACITY<Port.GENERAL.cargo)
        {
            cargo=+tempCargo;
            Port.GENERAL.cargo=-tempCargo;
            throw new OverloadCargoException("Too many cargo in port. Transaction canceled");
        }
    }

    public void loadCargo() throws InvalidCargoState // погрузка на корабль
    {
        int tempCargo;
        if(Port.GENERAL.cargo>Cargo_CAPACITY-cargo)
        {
            tempCargo=Cargo_CAPACITY-cargo;
            cargo=+tempCargo;
            Port.GENERAL.cargo=Port.GENERAL.cargo-tempCargo;
        } else
        {
            tempCargo=Port.GENERAL.cargo;
            cargo=cargo+tempCargo;
            Port.GENERAL.cargo=Port.GENERAL.cargo-cargo;
        }
        if(Port.GENERAL.cargo<0)
        {
            cargo=cargo-tempCargo;
            Port.GENERAL.cargo=Port.GENERAL.cargo+tempCargo;
            throw new InvalidCargoState("Cargo volume is negative. Transaction canceled");
        }

    }
    @Override
    public void run() {
        while (true) {
            if(!isBerth)
            {
                moor();
            }
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
