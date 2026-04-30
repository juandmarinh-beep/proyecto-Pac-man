package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

/**
 * Clase abstracta base para todas las entidades del juego.
 * Define posición, sprite, dirección y tamaño.
 * Las subclases deben implementar el método update()
 */
public abstract class Entity {

    /**
     * Constantes para el manejo de dirección de las entidades
     */
    protected static final int DIR_NONE = 0;
    protected static final int DIR_LEFT = 1;
    protected static final int DIR_UP = 2;
    protected static final int DIR_RIGHT = 3;
    protected static final int DIR_DOWN = 4;

    // Posición de la entidad
    private int x;
    private int y;

    // Dimensiones de la entidad
    private int width;
    private int height;

    // Imagen que representa la entidad
    private BufferedImage sprite;

    // Indica si la entidad está activa en el juego
    private boolean active;

    // Dirección actual de la entidad
    private int direction;

    /**
     * Constructor de la entidad
     * Inicializa posición, sprite y configura tamaño automáticamente
     */
    public Entity(int x, int y, BufferedImage sprite){
        this.x = x;
        this.y = y;
        this.sprite = sprite;

        // Si hay sprite, se ajusta el tamaño automáticamente
        if (sprite != null){
            this.width = sprite.getWidth();
            this.height = sprite.getHeight();
        }

        this.active = true;
        this.direction = DIR_NONE;
    }

    /**
     * Cada subclase debe definir su propia lógica de actualización
     */
    public abstract void update();

    /**
     * Método para obtener la caja de colisión actual (HitBox)
     */
    public Rectangle getHitBox(){
        return new Rectangle(x, y, width, height);
    }

    /**
     * Método para calcular una colisión futura
     * @param px posición futura en X
     * @param py posición futura en Y
     * @return Rectángulo representando la futura hitbox
     */
    public Rectangle getHitB(int px, int py){
        return new Rectangle(px, py, width, height);
    }

    /**
     * Calcula el desplazamiento horizontal según la dirección actual.
     * Devuelve -1, 0 o +1.
     */
    public int calculateDx() {
        if (direction == DIR_LEFT) return -1;
        if (direction == DIR_RIGHT) return 1;
        return 0;
    }

    /**
     * Calcula el desplazamiento vertical según la dirección actual.
     * Devuelve -1, 0 o +1.
     */
    public int calcularDy() {
        if (direction == DIR_UP) return -1;
        if (direction == DIR_DOWN) return 1;
        return 0;
    }

    /**
     * Carga una imagen desde la carpeta de recursos.
     * Uso: Entidad.cargarImagen("pacman_left.png")
     */
    public static BufferedImage uploadImage(String nombre) {
        try {
            InputStream is = Entity.class.getResourceAsStream("/resources/images/" + nombre);
            if (is == null) {
                System.err.println("Imagen no encontrada: " + nombre);
                return null;
            }
            return ImageIO.read(is);
        } catch (Exception e) {
            System.err.println("Error cargando imagen: " + nombre);
            return null;
        }
    }

    /**
     Métodos getters y setters
    */
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    /*
     * Asigna un nuevo sprite y actualiza automáticamente el tamaño
     */
    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
        if (sprite != null){
            this.width = sprite.getWidth();
            this.height = sprite.getHeight();
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
