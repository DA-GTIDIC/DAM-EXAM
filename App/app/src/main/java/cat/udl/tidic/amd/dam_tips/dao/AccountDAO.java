package cat.udl.tidic.amd.dam_tips.dao;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;

import cat.udl.tidic.amd.dam_tips.models.Pregunta;
import cat.udl.tidic.amd.dam_tips.models.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AccountDAO {

    @POST("account/create_token")
    Call<JsonObject> createTokenUser();

    @POST("account/delete_token")
    Call<Void> deleteTokenUser(@Body JsonObject token);

    @POST("/trivial/question/add")
    Call<Void> createPregunta(@Body Pregunta pregunta);

    @GET("/trivial/question/{owner_id}}")
    Call<String> getNumber(@Path("owner_id") String owner);

    @GET("/account/profile")
    Call<User> getUserProfile();
}
