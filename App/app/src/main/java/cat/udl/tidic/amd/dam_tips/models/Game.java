package cat.udl.tidic.amd.dam_tips.models;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private Round round;

    private HashMap<String,Integer> puntuaciones;
    private int vidas;
    private Boolean alive;
    public Game(){
        this.vidas = 3;
        alive = true;
        puntuaciones = new HashMap<String,Integer>();
        puntuaciones.put("db",0);
        puntuaciones.put("os",0);
        puntuaciones.put("patterns",0);
        puntuaciones.put("net",0);

    }

    public void encertarPregunta(String cat){
        puntuaciones.put(cat, puntuaciones.get(cat) + 1);
    }
    public void fallarPregunta(){
        this.vidas--;
    }

    public boolean gameWinned(){
        boolean ganado = true;

        for(Integer value: puntuaciones.values()){
            if (value < 3) {
                ganado = false;
            }
        }
        return ganado;
    }

    public boolean gameFailed(){
        return (this.vidas <= 0);
    }

    public class Round {

        private Boolean correcto;


        public Round() {
        }

        public void setCorrecto(Boolean respuesta){
            this.correcto = respuesta;
        }

        public Boolean getCorrecto(){
            return this.correcto;
        }

    }

}
