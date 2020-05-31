package cat.udl.tidic.amd.dam_tips;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class UserModel
{

    @SerializedName("question ")
    private String name;
    @SerializedName("category")
    public String category;
    @SerializedName("type")
    private FavourTypeEnum type;
    @SerializedName("id")
    private int id;
    @SerializedName("rate")
    private int rate;




    public UserModel(String name, String category, int id, FavourTypeEnum type, int rate)
    {
        this.name = name;
        this.id = id;
        this.category = category;
        this.type = type;
        this.rate = rate;
    }




    public int getId() { return this.id; }

    public FavourTypeEnum getType(){
        return type;
    }
    public int getRate(){
        return rate;
    }

    public void setRate(int rate){
        this.rate = rate;
    }

    public void setType(FavourTypeEnum type){
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   public void setCategory(String category){this.category = category;}

   public String getCategory(){return category;}



}
