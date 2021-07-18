import thread.Port;
import thread.Ship;

public class Main {
    public static void main(String ...args) {
        Thread port = new Thread(Port.GENERAL, "port");
        port.start();
        Thread[] ships = new Thread[4];
        for (int i = 0; i < 4; i++) {
            ships[i]= new Thread(new Ship("ship"+(i+1)), "ship"+(i+1));
            ships[i].start();
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        port.stop();
        for (int i = 0; i < 4 ; i++) {
            ships[i].stop();
            System.out.println("ship "+(i+1)+" is terminated");
        }
    }
}
