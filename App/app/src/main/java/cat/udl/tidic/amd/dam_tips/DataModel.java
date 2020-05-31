package cat.udl.tidic.amd.dam_tips;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;



@SuppressLint("Registered")
public class DataModel extends Activity
{
    int icon;
    UserModel userModel;


    //Cunstructor vuit de DataModel
    //S'ha utilitzat "herència" per a que sigui més fàcil crear i retornar arrays de
    //DataModel sense que la funció requereixi un tipus específic
    public DataModel() { }

    //Tot el que necessita la llista de la pàgina principal
    public static class MenuList extends DataModel
    {
        public String name;
        public MenuList(int icon, String name)
        {
            this.icon = icon;
            this.name = name;
        }

        public String getName()
        {
            return this.name;
        }
    }

    //Tot el que necessiten els favors
    public static class Favour extends DataModel implements Serializable
    {

        private MutableLiveData<UserModel> userMutable = new MutableLiveData<>();

        @SerializedName("category")
        public String category;
        @SerializedName("type")
        public FavourTypeEnum type;
        @SerializedName("question")
        public String name;
        @SerializedName("id")
        public int id;
        @SerializedName("rating ")
        public int rating;




        public Favour(String name, FavourTypeEnum type, int rating, String category, int id, String user, int owner_id, double lat, double long_)
        {
            this.category = category;
            this.name = name;
            this.id = id;
            this.type = type;
            this.rating = rating;
            setIcon();

        }



        public int getRating()
        {
            return  this.rating;
        }
        public String getName()
        {
           return  this.name;
        }

        public void setRating(int rating){ this.rating = rating;}


        public float parseFloat(float amount)
        {
            if(amount % 1 != 0) {return  amount;}
            else { return (int) amount;}
        }

        public FavourTypeEnum getType(){
            return type;
        }

        public String getCategoria()
        {
            return  this.category;
        }
        @SuppressLint("DefaultLocale")

        public void setIcon()
        {
            this.icon = CategoryManager.getImageId(this.category);
        }


        public void setCategory(String category)
        {
            this.category = category;
        }

        public void setName(String name)
        {
            this.name = name;
        }


    }


}