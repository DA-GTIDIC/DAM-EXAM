package cat.udl.tidic.amd.dam_tips.models;

import android.graphics.Color;

import cat.udl.tidic.amd.dam_tips.R;

public enum QuestionColours {

    db("db","db"), os("os","os"), net("net","net"), patterns("patterns","patterns");

    String id;
    String name;

    QuestionColours(String id, String name){
        id = id;
        name = name;
    }

    public String getName(){
        return name;
    }

    public static int getColours(String color){
        switch (color){
            case "db":
                return Color.MAGENTA;
            case "os":
                return Color.GREEN;
            case "patterns":
                return Color.BLUE;
            case "net":
                return Color.GRAY;
            default:
                return R.color.red;
        }
    }
}
