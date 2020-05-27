package cat.udl.tidic.amd.dam_tips.utils;

import android.graphics.Matrix;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GameManager
{
    static String[] categories= new String[]{"db","os","net","patterns"};
    public static final int MAXPOINTS = 120;
    public static final int LIVES = 3;
    public static final int MAXIM_PER_CATEGORIA = 3;
    public static final int PROGRESS_PER_PREGUNTA = 10;

    public static String[] getCategories()
    {
        return categories;
    }
}
