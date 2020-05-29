package cat.udl.tidic.amd.dam_tips.dao;

import org.json.JSONObject;

import cat.udl.tidic.amd.dam_tips.models.CategoryEnum;
import cat.udl.tidic.amd.dam_tips.models.Question;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface TrivialDAO {

    @GET("trivial/question")
    Call<Question> getRandomQuestion(@Header("Authoriation") String Auth, @Query("category") CategoryEnum category);



}
