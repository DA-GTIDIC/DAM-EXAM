package cat.udl.tidic.amd.dam_tips.models;

public enum Category {
    /*db = "db"
    os = "os"
    net = "net"
    patterns = "patterns"*/

    DB("DB","db"), OS("OS","os"),NET("NET","net"), PATTERNS("PATTERNS","patterns");

    String id;
    String name;

    Category(String _id, String _name){
        id = _id;
        name = _name;
    }

    public  String getId(){
        return id;
    }
}
