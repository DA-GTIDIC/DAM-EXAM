package cat.udl.tidic.amd.dam_tips.models;

import android.graphics.Color;

import cat.udl.tidic.amd.dam_tips.R;

public  enum PreguntaEnum {

        db("db","db"),
        os("os","os"),
        net("net","net"),
        patterns("patterns","patterns");

        String name;
        String id;

        PreguntaEnum(String _id, String _name){
            id = _id;
            name = _name;
        }

        public String getName(){
            return name;
        }

    public static int getColourResource(String e){
        switch(e){
            case "db":
                return Color.BLUE;
            case "os":
                return Color.RED;
            case "patterns":
                return Color.GREEN;
            case "net":
                return Color.YELLOW;
            default:
                return R.color.black;
        }

    }
}
