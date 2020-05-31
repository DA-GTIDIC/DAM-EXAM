package cat.udl.tidic.amd.dam_tips.dao;

import cat.udl.tidic.amd.dam_tips.network.RetrofitClientInstance;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

public class GameDAOImpl implements IGameDAO{
    Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();



    @Override
    public Call<ResponseBody> getQuestion(String auth, String category) {
        return  retrofit.create(IGameDAO.class).getQuestion(auth,category);
    }

}
