package Given;


import java.io.Serializable;

public class Coordinates implements Serializable {
    private static final long serialVersionUID = 103L;
    private int x; //Максимальное значение поля: 189
    private Float y; //Поле не может быть null
    public Coordinates(int x, Float y) throws Exception{
        if (x>189 || y==null)throw new Exception();
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    @Override
    public String toString(){
        return "("+x+","+y+")";
    }
}
