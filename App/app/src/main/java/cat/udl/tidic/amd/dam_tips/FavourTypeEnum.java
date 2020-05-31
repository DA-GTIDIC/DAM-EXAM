package cat.udl.tidic.amd.dam_tips;

import android.graphics.Color;
import android.util.Log;

public enum FavourTypeEnum {
    db("db","db"),
    os("os","os"),
    net("net","net"),
    patterns("patterns","patterns");

    String name;
    String id;

    FavourTypeEnum(String _id, String _name){
        id = _id;
        name = _name;
    }

    public String getName(){
        return name;
    }

    public static int getColourResource(String e){

        switch(e){
            case "db":
                Log.d("COLOR", " "+e);
                return Color.BLUE;
            case "os":
                Log.d("COLOR", " "+e);
                return Color.RED;
            case "net":
                Log.d("COLOR", " "+e);
                return Color.GREEN;
            case "patterns":
                Log.d("COLOR", " "+e);
                return Color.YELLOW;
            default:
                return R.color.white;
        }

    }
}
