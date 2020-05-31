package cat.udl.tidic.amd.dam_tips.dao;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;

import java.util.List;
import cat.udl.tidic.amd.dam_tips.models.Question;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;

public interface AccountDAO {

    @POST("account/create_token")
    Call<JsonObject> createTokenUser();

    @POST("account/delete_token")
    Call<Void> deleteTokenUser(@Body JsonObject token);

    @GET("/trivial/question/list")
    Call<List<Question>> getQuestList();

}
