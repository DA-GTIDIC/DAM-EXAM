package cat.udl.tidic.amd.dam_tips.models;
import cat.udl.tidic.amd.dam_tips.R;
import android.graphics.Color;

public enum QuestColor {

    db("db","db"),
    os("os","os"),
    net("net","net"),
    patterns("patterns","patterns");

    String id;
    String name;


    QuestColor(String id, String name){
        id = id;
        name = name;
    }

    public String getName(){
        return name;
    }

    public static int getColourResource(String e){
        switch(e){
            case "db":
                return Color.DKGRAY;
            case "os":
                return Color.BLUE;
            case "patterns":
                return Color.MAGENTA;
            case "net":
                return Color.GREEN;
            default:
                return R.color.red;
        }

    }
}


