

public class Square {

    int x;
    int y;

    public Square(int x,int y){

        this.x=x;
        this.y=y;
    }

    public Square(Square square){

        this.x=square.x;
        this.y=square.y;
    }
}
