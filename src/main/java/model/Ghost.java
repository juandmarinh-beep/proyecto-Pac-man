package model;

/**
* Clase anstracta va a ser el papá de los fantasmas,
* Define el mobimiento basico y el estado comestible
* Cada Hijo Implementa su propia logica y tambien podemos mamipular el tienpo del estado en el que se puede comer Fantasma
* */

import java.awt.image.BufferedImage;

public abstract class Ghost extends Entity{

    /**
    * Constante en que  durara el tiempo del estado consumible del fantasma
    * 9 seg X 60 FPS
    * */
    private static final int DURATION_EATABLE = 50;


    private int speed;//velocidad
    private boolean stateEatable;//estado comestible
    private int countEatable;//duracion estado comestible
    private BufferedImage spriteOriginal;//imagen de la entidad



    protected Ghost(int x, int y, String nombreSprite, int dirInicial, int velocidad) {
        super(x, y, uploadImage(nombreSprite));
        this.speed = velocidad;
        this.stateEatable = false;
        this.countEatable = 0;
        this.spriteOriginal = getSprite();
        setDirection(dirInicial);
    }

    /**
     * Cada subClase decide como cambiar de direccion al chovar con un miro
     * Blinky: Persigue, Pinky: persigue (Polimorfismo)
     * */
    public abstract void changeDirection(int posPacX, int posPacY);

    /**
     * Reinicia el fantasma a su pocicion inicial
     * */
    public abstract void restart();

    /**
     * Metodo para invertir la reccion del fantasma
     * */
    public void reverseDirection(){
        int direction = getDirection();
        if(direction == DIR_LEFT){
            setDirection(DIR_RIGHT);

        } else if (direction == DIR_RIGHT){
            setDirection(DIR_LEFT);

        } else if (direction == DIR_DOWN) {
            setDirection(DIR_UP);

        } else if (direction == DIR_UP) {
            setDirection(DIR_DOWN);
        }
    }
    /**
     * Activa o desactiva el estado comestible
     * Al activarse cambia el sprint del fantasma y empieza la cuenta atras
     * */

    public void setStateEatable(boolean active){
        if(active){
            stateEatable = true;
            countEatable = DURATION_EATABLE;
            setSprite(uploadImage("ghost_asustado.png"));
        }else {
            stateEatable = false;
            countEatable = 0;
            setSprite(spriteOriginal);
        }
    }

    /**
    * Se sobreescribe el metodo actualizar para que los fantasmas y descuenta el contador comestinle si el fantasma esta activo
    * */
    @Override
    public void update(){
        setX(getX() + calculateDx()*speed);
        setY(getY() + calcularDy()*speed);

        if(stateEatable){
            countEatable--;
            if (countEatable <= 0){
                stateEatable = false;
                setSprite(spriteOriginal);
            }
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isStateEatable() {
        return stateEatable;
    }

    public int getCountEatable() {
        return countEatable;
    }

    public void setCountEatable(int countEatable) {
        this.countEatable = countEatable;
    }

    public BufferedImage getSpriteOriginal() {
        return spriteOriginal;
    }

    public void setSpriteOriginal(BufferedImage spriteOriginal) {
        this.spriteOriginal = spriteOriginal;
    }
}

