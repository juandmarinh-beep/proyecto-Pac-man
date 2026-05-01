package model;

import java.awt.image.BufferedImage;

/*
 * Clase pacman personaje principal del juego, gestiona
 * su  posicion
 *
 *
 * */
public class Pacman extends Entity {

    //constante para darle la posicion inicial a pacman

    private static final int X_INITIAL = 240;
    private static final int Y_INITIAL = 432;
    private static final int SPEED = 1;
    private static final int INITIAL_LIFES = 3;

    //COORDENADAS  CAMINOS LATERAL (LA FILA POR PIXELES)
    //Ejemplo = fila 9x24 px

    private static final int Y_TUNNEL = 216;

    //COORDENADAS DEL ANCHO DEL TABLERO
    private static final int X_TUNNEL = 504;

    private int SCORE = 0;


    private int lifes;

    //variable para ver si tiene la boca  abierta
    private boolean openMouth;

    //para pausar
    private boolean paused;

    //enter
    private boolean enter;


    //getter y setters


    public boolean isOpenMouth() {
        return openMouth;
    }

    public void setOpenMouth(boolean openMouth) {
        this.openMouth = openMouth;
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public int getSCORE() {
        return SCORE;
    }

    public void setSCORE(int SCORE) {
        this.SCORE = SCORE;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isEnter() {
        return enter;
    }

    public void setEnter(boolean enter) {
        this.enter = enter;
    }

    public Pacman() {
        super(X_INITIAL, Y_INITIAL, uploadImage("pacman_left.png"));
        this.SCORE = 0;
        this.lifes = INITIAL_LIFES;
        this.openMouth = true;
        this.paused = false;
        this.enter = false;
        setDirection(DIR_LEFT);

    }

    /**
     * metodo para reiniciar la posicion y la direccion
     * cuando pierde una vida
     */
    public void resetPosition() {
        setX(X_INITIAL);
        setY(Y_INITIAL);
        setDirection(DIR_LEFT);
        setSprite(uploadImage("pacman_left.png"));
        setActive(true);

    }
    /**
     * Metodo para reinicar completamente
     */

    public void resetAll(){
        resetPosition();
        this.lifes=INITIAL_LIFES;
        this.SCORE=0;
    }

    public Pacman(int x, int y, BufferedImage sprite) {
        super(x, y, sprite);
    }

    /**
     * Cada subclase debe definir su propia lógica de actualización
     */
    /**
     * Mueve a Pacman según su dirección actual y gestiona el túnel lateral.
     */
    @Override
    public void update() {
        // Túnel: sale por la derecha → entra por la izquierda
        if (getX() > X_TUNNEL && getY() == Y_TUNNEL) {
            setX(0);
        }
        // Túnel: sale por la izquierda → entra por la derecha
        if (getX() < 0 && getY() == Y_TUNNEL) {
            setX(X_TUNNEL);
        }

        setX(getX() + calculateDx() * SPEED);
        setY(getY() + calcularDy() * SPEED);
    }

    /**
     * Suma puntos a la puntuación acumulada.
     */
    public void sumarPuntos(int puntos) {
        this.SCORE += puntos;
    }

    /**
     * Descuenta una vida.
     */
    public void perderVida() {
        if (lifes > 0) lifes--;
    }

    /**
     * Actualiza el sprite según la dirección y alterna la animación de boca.
     * Se llama cada ciertos frames para que la boca "masque".
     */
    public void actualizarSprite(int direction==DIR_DOWN) {
        String archivo;
        if (openMouth) {
            archivo = "pacman_closed.png";
        } else {
            if (direction == DIR_LEFT) archivo = "pacman_left.png";
            else if (direction == DIR_RIGHT) archivo = "pacman_right.png";
            else if (direction == DIR_UP) archivo = "pacman_up.png";
            else if (direction == DIR_DOWN) archivo = "pacman_down.png";
            else archivo = "pacman_left.png";
        }
        setSprite(uploadImage(archivo));
        openMouth = !openMouth;
    }

    /**
     * Activa o desactiva la pausa.
     */
    public void updatePause() {
        paused = !paused;
    }
}
