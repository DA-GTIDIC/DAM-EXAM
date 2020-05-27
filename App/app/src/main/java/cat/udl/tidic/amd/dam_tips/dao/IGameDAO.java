package cat.udl.tidic.amd.dam_tips.dao;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IGameDAO {
    @GET("trivial/question")
    Call<ResponseBody> getQuestion(@Header("Authorization") String auth, @Query("category") String category);


}
