package model;

/**
 * El punto es estatico
 * En la matriz esta representado con el numero 2
 * Y si el pacman se lo come gana 10 puntos
 * */

public class Point extends ObjectGame{
    public static final int VALUE = 10;


    public Point(int x, int y) {
        super(x, y,uploadImage("pill.png"),POINT);
    }
}
