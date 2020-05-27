package cat.udl.tidic.amd.dam_tips.services;

import androidx.lifecycle.MutableLiveData;

import cat.udl.tidic.amd.dam_tips.models.Question;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GameServiceI {


    @GET("trivial/question/{category}")
    void getQuestion(@Header("Authorization") String auth, @Query("category") String category);


    MutableLiveData<Question>  getLiveDataQuestion();
}
