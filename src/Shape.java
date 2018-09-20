import java.util.Vector;

public class Shape {

    Square[] s=new Square[4];
    int type;
    int position=0;


    public Shape(int type){

        switch (type){
            // ---
            case 0:{
                s[1]=new Square(70,10-50);
                s[2]=new Square(70,35-50);
                s[0]=new Square(70,60-50);
                s[3]=new Square(70,85-50);
                this.type=type;
                break;

            }
            // Z
            case 1:{

                s[1]=new Square(70,10-50);
                s[2]=new Square(70+25,10-50);
                s[0]=new Square(70,10-25);
                s[3]=new Square(70-25,10-25);
                this.type=type;
                break;

            }
            // []
            case 2:{
                s[1]=new Square(70,10-50);
                s[2]=new Square(70+25,10-50);
                s[0]=new Square(70,10-25);
                s[3]=new Square(70+25,10-25);
                this.type=type;
                break;
            }
            //L
            case 3:{
                s[1]=new Square(70+25,10-25);
                s[2]=new Square(70+50,10-25);
                s[0]=new Square(70,10-25);
                s[3]=new Square(70,10);
                this.type=type;
                break;
            }
            case 4:{
                s[1]=new Square(70-25,10-25);
                s[2]=new Square(70+25,10-25);
                s[0]=new Square(70,10-25);
                s[3]=new Square(70,10);
                this.type=type;
                break;
            }
        }
    }





    //
    public void move(char dir, int speed){

        switch (dir) {
            case 'x':
                if ((getLeft()>=20+25 && speed<0)|(getRight()<=245-25 && speed>0)){
                    for (Square ss : this.s) {
                        ss.x += speed;

                    }}
                break;


            case 'y':

                for (Square ss : this.s) {
                    ss.y += speed;

                }
                break;
        }

    }

    public boolean isTouch(Vector<Square> stack) {
        boolean touch = false;
        //touch the stack
        if (!stack.isEmpty()) {
            for (Square aSquare:stack){
                for (Square ss:this.s){
                    if (ss.x==aSquare.x && Math.abs(ss.y-aSquare.y)==25){
                        touch=true;
                    }

                }

            }

        }
        //touch frame bottom
        if (this.getBottom() == 510 - 25) {
            touch = true;}

        return touch;
    }

    public boolean outBound(){
        boolean out=false;
        for (Square ss:this.s){
            if (ss.x>245 ||  ss.x<20 || this.getBottom()>510-25){
                out=true;

            }

        }

        return  out;
    }

    public boolean hitBound(){
        boolean out=false;
        for (Square ss:this.s){
            if (ss.x==245 ||  ss.x==20){
                out=true;

            }

        }

        return  out;
    }


    public int getBottom(){
        int bottom=s[0].y;
        for (Square ss:this.s){
            if (ss.y>bottom)
                bottom=ss.y;

        }
        return bottom;
    }
    public int getLeft(){
        int Left=s[0].x;
        for (Square ss:this.s){
            if (ss.x<Left)
                Left=ss.x;

        }
        return Left;
    }
    public int getRight(){
        int Right=s[0].x;
        for (Square ss:this.s){
            if (ss.x>Right)
                Right=ss.x;

        }
        return Right;
    }

    public boolean isHit(Vector<Square> stack){
        boolean hit=false;
        if(!stack.isEmpty()){
            for (Square aSquare:stack) {
                for (Square ss:this.s){
                    if (Math.abs(ss.y-aSquare.y)<=25 & Math.abs(ss.x-aSquare.x)<=25 & Math.abs(ss.x-aSquare.x)>=0 &Math.abs(ss.y-aSquare.y)>=0){
                        hit=true;
                    }
                }

            }}
        return hit;
    }



    public boolean isHit(String lr, Vector<Square> stack){
        boolean hit=false;
        if(!stack.isEmpty() & lr.equals("l")){
            for (Square aSquare:stack) {
                for (Square ss:this.s){
                    if (Math.abs(ss.y-aSquare.y)<=25 & ss.x>aSquare.x & Math.abs(ss.x-aSquare.x)<=25){
                        hit=true;
                    }
                }

            }}

        else if(!stack.isEmpty()& lr.equals("r")){
            for (Square aSquare:stack) {
                for (Square ss:this.s){
                    if (Math.abs(ss.y-aSquare.y)<=25 & ss.x<aSquare.x & Math.abs(ss.x-aSquare.x)<=25){
                        hit=true;
                    }
                }

            }}


        return hit;
    }




}
