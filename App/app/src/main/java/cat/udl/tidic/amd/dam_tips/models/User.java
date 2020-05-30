package cat.udl.tidic.amd.dam_tips.models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private int id;
    public User(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
