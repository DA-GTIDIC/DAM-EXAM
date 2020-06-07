package cat.udl.tidic.amd.dam_tips.dao;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;

import java.util.List;

import cat.udl.tidic.amd.dam_tips.models.Question;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AccountDAO {

    @POST("account/create_token")
    Call<JsonObject> createTokenUser();

    @POST("account/delete_token")
    Call<Void> deleteTokenUser(@Body JsonObject token);

    @GET("trivial/question/list")
    Call<List<Question>> getQuestionList();

}
