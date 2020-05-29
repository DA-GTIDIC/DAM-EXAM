package cat.udl.tidic.amd.dam_tips.models;

import java.util.ArrayList;

public enum CategoryEnum {
    db("db","db"),os("os","os"),net("net", "net"), patterns("patterns", "patterns");

    String id;
    String name;

    CategoryEnum(String _id, String _name){
        id = _id;
        name = _name;
    }

    public static ArrayList<CategoryEnum> toArrayList() {
        ArrayList<CategoryEnum> result = new ArrayList<CategoryEnum>();
        result.add(db);
        result.add(os);
        result.add(net);
        result.add(patterns);
        return result;

    }

    public String getName(){
    return name;}
}
