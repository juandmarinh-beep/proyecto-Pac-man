package controller;

public class GameController implements Runnable {

    /**
     * fps a los que va a ir el juego
     */


    private static final int FPS = 60;

    /**
    *
    * los nanos por frame
    */
    private static final long NANOS_PER_FRAME=1_000_000_000L/FPS;

    /***
     * Cuantos frames se muestran en ese tiempo
     *
     */
    private static final  int FRAMES_ANIMATION=8;

    /***
     * total puntos dalen del total de 
     */

    private static final int TOTAL_POINTS =185;

    /**
     * Runs this operation.
     */
    @Override

    public void run() {

    }
}
