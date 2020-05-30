package cat.udl.tidic.amd.dam_tips.models;

public enum Categories {
    db("db","db"),os("os","os"),net("net","net"),patterns("patterns","patterns");

    String name;
    String id;

    Categories(String _id, String _name){
        id = _id;
        name = _name;
    }
}
