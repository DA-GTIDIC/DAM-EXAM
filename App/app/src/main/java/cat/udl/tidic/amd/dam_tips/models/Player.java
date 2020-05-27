package cat.udl.tidic.amd.dam_tips.models;

import android.util.Log;

import java.util.HashMap;
import java.util.Random;

import cat.udl.tidic.amd.dam_tips.utils.GameManager;

public class Player
{
    private String currentCategory;
    private HashMap<String, Integer> preguntesCorrectes = new HashMap<String, Integer>();
    private int lives;
    private int progress;

    public Player()
    {
        this.lives = GameManager.LIVES;
        this.progress = 0;
        initHasMap();
    }

    private void initHasMap()
    {
        for (String categoria: GameManager.getCategories())
        {
            preguntesCorrectes.put(categoria, 0);
        }
    }

    public void sumarPreguntaCorrecta()
    {
        String categoria = getCurrentCategory();
        if (preguntesCorrectes.containsKey(categoria))
        {
            Integer catnumber = preguntesCorrectes.get(categoria);

            if (catnumber != null)
            {
                preguntesCorrectes.put(categoria, catnumber + 1);
                this.progress += GameManager.PROGRESS_PER_PREGUNTA;
                Log.d("Sumant pregunta correcta a la categoria de ", categoria);
            }
        }

        checkMaximumQuestuin(categoria);
    }

    public int getCategoriaNumber(String cat)
    {
        Integer catnumber = preguntesCorrectes.get(cat);
        if (catnumber != null) {return catnumber;}
        else{return  -1;}

    }

    private void checkMaximumQuestuin(String categoria)
    {
        //Aquesta funció serveix per saber si s'ha arribat al màxim de pregutes
        //correctes d'una categoria (3).
        if (preguntesCorrectes.containsKey(categoria))
        {
            Integer catnumber = preguntesCorrectes.get(categoria);
            if (catnumber != null)
            {
                if (catnumber == GameManager.MAXIM_PER_CATEGORIA)
                {
                    eliminarCategoria(categoria);
                }
            }
        }

    }

    private void eliminarCategoria(String categoria)
    {
        preguntesCorrectes.remove(categoria);
    }

    public void restarVida()
    {
        this.lives -= 1;
    }

    public int getLives()
    {
        return this.lives;
    }

    public int getProgress() {
        return progress;
    }

    public String generateRandomCategory()
    {
        Object randomName = preguntesCorrectes.keySet().toArray()[new Random().nextInt(preguntesCorrectes.keySet().toArray().length)];
        return randomName.toString();
    }

    public void setCurrentCategory(String cat) { this.currentCategory = cat;}
    public String getCurrentCategory() { return this.currentCategory;}
}
