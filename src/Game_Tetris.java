
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.TimeUnit;


public class Game_Tetris extends JFrame {

    Shape current;
    Shape next;
    Stacks stacks=new Stacks();
    Draw draw;
    URL url;
    ImageIcon icon;
    ImageIcon[] images=new ImageIcon[5];
    JLabel cat;
    Drop drop;
    JLabel drawNext=new JLabel();
    int level=1;
    int speed=10;
    boolean gg=false;
    int score=0;
    boolean go;
    boolean running;
    boolean welcom;
    boolean reset;
    int nextType;


    public static void main(String[] args) throws InterruptedException {

        Game_Tetris gt=new Game_Tetris();

        gt.welcom();

    }


    public void run() throws InterruptedException{

        //main game loop
        running=true;
        System.out.println("running");
        init();
        gg=false;
        while(!gg){

            if (current.isTouch(stacks.stack)) {
                for (Square aSquare : current.s)
                    stacks.stack.add(new Square(aSquare));
                // System.out.println(current.type);

                score+=10*this.remove();
                current = new Shape(nextType);
                nextType=new Random().nextInt(5);


                drawNext.setIcon(images[nextType]);
                draw.setShape(current, next);
                draw.setScore(score);
                // drop=new Drop(current,level);
                if (current.isTouch(stacks.stack)) {
                    System.out.println("GAME OVER");

                    gg=true;
                    break;
                }
                speed = 10;


            }

            draw.repaint();

        }
        running=false;
        go=false;

        restart();




        //repaint


    }


    public void init(){

        nextType=new Random().nextInt(5);
        current=new Shape(new Random().nextInt(5));
        stacks.stack.clear();
        score=0;
        draw.setShape(current, next);
        drawNext=new JLabel(images[nextType]);
        drawNext.setSize(100,100);
        drawNext.setLocation(280,15);
        draw.add(drawNext);
        draw.setLayout(null);


    }

    public void welcom() throws InterruptedException{
        welcom=true;
        draw.setDrawWelcom(welcom);
        System.out.println(go);
        while (!go){
            System.out.println(welcom);

        }
        welcom=false;
        draw.setDrawWelcom(welcom);
        run();
    }



    public void restart() throws InterruptedException{
        reset=true;
        System.out.println("in restart");
        while(reset){

            System.out.println(reset);
        }
        System.out.println("out of restart");
        run();
    }


    public Game_Tetris(){

        //icon image
        url=getClass().getClassLoader().getResource("Images/icon.png");
        icon = new ImageIcon(url);

        //shape images
        for(int i=0;i<5;i++){
            url=getClass().getClassLoader().getResource("Images/"+i+".png");
            images[i]=new ImageIcon(url);
            images[i].setImage(images[i].getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        }

        next=null;
        draw=new Draw(current,next,stacks);

        //set dimensions
        draw.setPreferredSize(new Dimension(500,560));
        draw.setBorder(BorderFactory.createLineBorder(Color.darkGray));

        //add cat
        /*
        url=Game_Tetris.class.getResource("/Images/cat1.gif");

        imageIcon = new ImageIcon(url);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        cat = new JLabel(imageIcon);
        draw.add(cat);
        draw.setLayout(null);

        cat.setSize(150,150);
        cat.setLocation(300,350);*/


        this.setIconImage(icon.getImage());

        this.setSize(415,560);
        this.add(draw);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //key listener
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(current!=null) {
                    if (e.getKeyCode() == KeyEvent.VK_UP & current.type != 2)
                        draw.rotate();
                    if (e.getKeyCode() == KeyEvent.VK_SPACE)
                        speed = 1;


                    if (e.getKeyCode() == KeyEvent.VK_LEFT & !current.isHit("l", stacks.stack) & !current.isTouch(stacks.stack)) {
                        current.move('x', -25);
                    }
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT & !current.isHit("r", stacks.stack) & !current.isTouch(stacks.stack)) {
                        current.move('x', 25);
                    }
                }

                if ((e.getKeyCode() == KeyEvent.VK_R) && reset) {
                    reset = false;
                    System.out.println("R pressed");
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    System.out.println(welcom);
                    if (welcom) go = true;
                }




            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        //lunch drop thread
        // drop=new Drop(current,level);
        Thread t=new Thread(){

            public void run() {

                while(true){
                    if(current!=null)
                        current.move('y',level);
                    try{

                        TimeUnit.MILLISECONDS.sleep(speed);} catch (InterruptedException e){}


                }

            }

        };
        t.start();




    }

    public int remove() {

        // Vector<Square> temp = new Vector<>();

        Vector<Vector<Square>> all=new Vector<>();
        int start=0;
        int count=0;
        if (!stacks.stack.isEmpty()) {
            for (int i = 510 - 25; i >= 10; i -= 25) {
                Vector<Square> temp = new Vector<>();
                //for( Iterator<Square> it = stack.iterator(); it.hasNext();){
                for (Square s : stacks.stack) {

                    //     Square s=it.next();
                    if (s.y == i) {
                        temp.add(s);

                    }
                    if (temp.size() == 10 ) {
                        if(start==0) start=i;
                        count++;

                        all.add(new Vector<Square>(temp));
                        temp.clear();
                        System.out.println(temp.size());


                    }


                }

            }

            // stack.removeAll(temp);
            for (int i=0;i<all.size();i++) {
                for (int j=0;j<10;j++){
                    if (stacks.stack.contains(all.get(i).get(j)))
                        stacks.stack.remove(all.get(i).get(j));}

                for(Square ss:stacks.stack) {
                    if (ss.y < all.get(i).get(0).y)
                        ss.y += 25;
                }


            }


        }

        return count;
    }



}
