package model;

public class Wall extends ObjectGame{

//quitamos el value porque las paredes son estaticas

    public Wall(int x, int y) {
        super(x, y,uploadImage("Wall.png"),POINT);
    }

}
