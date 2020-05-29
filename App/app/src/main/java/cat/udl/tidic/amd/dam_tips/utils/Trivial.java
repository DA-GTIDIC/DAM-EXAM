package cat.udl.tidic.amd.dam_tips.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import cat.udl.tidic.amd.dam_tips.models.CategoryEnum;
import kotlin.collections.UCollectionsKt;

public class Trivial {


    private final String TAG = getClass().getSimpleName();
    private int db_correct_answers;
    private int os_correct_answers;
    private int net_correct_answers;
    private int patterns_correct_answers;

    private static ArrayList<CategoryEnum> categories = CategoryEnum.toArrayList();

    private static final Random RANDOM = new Random();

    public Trivial(){
        db_correct_answers = 0; os_correct_answers = 0;
        net_correct_answers = 0; patterns_correct_answers = 0;
    }

    public static CategoryEnum getRandomCategory(){
        return categories.get(RANDOM.nextInt(categories.size()));
    }


    public void correctAnswer(CategoryEnum category){
        switch (category){
            case db:
                if (db_correct_answers == 2) {
                    db_correct_answers++;
                    categories.remove(category);
                }else{
                    db_correct_answers++;
                }
                break;
            case os:
                if (os_correct_answers == 2) {
                    os_correct_answers++;
                    categories.remove(category);
                }else{
                    os_correct_answers++;
                }
                break;
            case net:
                if (net_correct_answers == 2) {
                    net_correct_answers++;
                    categories.remove(category);
                }else{
                    net_correct_answers++;
                }
                break;
            case patterns:
                if (patterns_correct_answers == 2) {
                    patterns_correct_answers++;
                    categories.remove(category);
                }else{
                    patterns_correct_answers++;
                }
                break;
        }
    }


    public int getDb_correct_answers() {
        return db_correct_answers;
    }

    public void setDb_correct_answers(int db_correct_answers) {
        this.db_correct_answers = db_correct_answers;
    }

    public int getOs_correct_answers() {
        return os_correct_answers;
    }

    public void setOs_correct_answers(int os_correct_answers) {
        this.os_correct_answers = os_correct_answers;
    }

    public int getNet_correct_answers() {
        return net_correct_answers;
    }

    public void setNet_correct_answers(int net_correct_answers) {
        this.net_correct_answers = net_correct_answers;
    }

    public int getPatterns_correct_answers() {
        return patterns_correct_answers;
    }

    public void setPatterns_correct_answers(int patterns_correct_answers) {
        this.patterns_correct_answers = patterns_correct_answers;
    }

    public String toString(){
        return "datavase :   "+ db_correct_answers + "\n" +
                "os  : " + os_correct_answers + "\n"+
                "net :" + net_correct_answers + "\n"+
                " patterns : "+ patterns_correct_answers;
    }
}
