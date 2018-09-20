import java.util.concurrent.TimeUnit;

public class Drop implements Runnable {

    Shape shape;
    int level;

    public Drop(Shape shape, int level){


        this.shape=shape;
        this.level=level;

    }

    @Override
    public void run() {

        while(true){

            shape.move('y',level);
            try{

            TimeUnit.MILLISECONDS.sleep(10);} catch (InterruptedException e){}


        }

    }

}
