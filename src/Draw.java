
import javax.swing.*;
import java.awt.*;



public class Draw extends JPanel {
    Shape current;
    Shape next;
    // Vector<Square> stack;
    Stacks stacks;
    int score;
    boolean drawWelcom=false;
    boolean gg;



    @Override
    public void paint(Graphics g) {

        super.paint(g);

        drawFrames(g);

        drawScore(g);
        drawWelcome(g);
        drawShapes(g,current,stacks);


    }



    public Draw(Shape current, Shape next, Stacks stacks){

        this.current=current;
        this.next=next;
        this.stacks=stacks;

    }


    public void setShape(Shape current,Shape next){
        this.current=current;
        this.next=next;
    }


    public void drawShapes(Graphics g, Shape shape, Stacks stacks){
        if(shape!=null){
            switch (shape.type){
                case 0: {g.setColor(Color.green); break;}
                case 1: {g.setColor(Color.MAGENTA); break;}
                case 2: {g.setColor(Color.orange); break;}
                case 3: {g.setColor(Color.red); break;}
                case 4: {g.setColor(Color.cyan); break;}
            }

            for (Square aSquare : shape.s) {
                if (aSquare.y>=10)
                    g.fill3DRect(aSquare.x, aSquare.y, 25, 25,true);
            }}


        g.setColor(Color.gray);
        if (!stacks.stack.isEmpty()) {
            for (Square aSquare : stacks.stack) {
                for (int i = 0; i < 4; i++)
                    g.fill3DRect(aSquare.x, aSquare.y, 25, 25,true);

            }


        }

    }




    public void drawFrames(Graphics g) {

        g.setColor(Color.black);
        g.fill3DRect(20, 10, 250, 500, false);
        // g.fill3DRect(20, 10, 250, 500, false);
        g.draw3DRect(279,14,102,102,false);

    }


    public void setDrawWelcom(Boolean welcom){
        this.drawWelcom=welcom;

    }

    public void drawWelcome(Graphics g){
        if (drawWelcom){
            g.setFont(new Font("Tiger Expert", Font.BOLD, 16));
            g.setColor(Color.white);
            g.drawString("Press Enter to Start!",50,220);}

    }
    public void setScore(int score){

        this.score=score;
    }

    public void drawScore(Graphics g){

        g.setFont(new Font("Tiger Expert", Font.BOLD, 14));
        g.setColor(Color.RED);
        g.drawString("Score: ",280,160);
        g.drawString(Integer.toString(score),280,180);

    }

    public void drawShapes(Graphics g,Shape shape){
        int x=0;
        int y=0;
        if(shape!=null){
            switch (shape.type){
                //----
                case 0: {g.setColor(Color.green); break;}
                case 1: {g.setColor(Color.PINK); x=280+50; y=10+25; break;}
                case 2: {g.setColor(Color.white); break;}
                case 3: {g.setColor(Color.red); break;}
                case 4: {g.setColor(Color.cyan); break;}

            }

            for (Square aSquare : shape.s) {
                if (aSquare.y>=10)
                    g.fill3DRect(x, y, 25, 25,true);
            }
        }

    }


    public void rotate(){
        int[] center={0,1,2,3};
        int count=1;
        for (int cc:center) {
            rotate(90,cc);

            if (this.current.outBound() | this.current.isHit(stacks.stack))

                rotate(-90,cc);
            else {break;}
        }

    }

    public void rotate(double angle, int c){

        angle=Math.PI*angle/180;
        for (int i = 0; i < 4; i++) {
            if (i == c) {
                continue;
            }
            int xx =(this.current.s[i].x - this.current.s[c].x)*(int)Math.cos(angle) - (this.current.s[i].y - this.current.s[c].y)*(int)Math.sin(angle) + this.current.s[c].x;
            int yy =(this.current.s[i].x - this.current.s[c].x)*(int)Math.sin(angle) + (this.current.s[i].y - this.current.s[c].y)*(int)Math.cos(angle) + this.current.s[c].y;


            this.current.s[i].x = xx;
            this.current.s[i].y = yy;


        }

    }





}
