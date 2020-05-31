package cat.udl.tidic.amd.dam_tips.dao;

import java.util.List;

import cat.udl.tidic.amd.dam_tips.models.Question;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface QuestionDAO {

    @GET("trivial/question/list")
    Call<List<Question>> getQuestions(@Header("Authorization") String auth);
}
