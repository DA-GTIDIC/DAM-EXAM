package cat.udl.tidic.amd.dam_tips.dao;

import org.json.JSONObject;

import cat.udl.tidic.amd.dam_tips.models.CategoryEnum;
import cat.udl.tidic.amd.dam_tips.models.Question;
import cat.udl.tidic.amd.dam_tips.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Retrofit;

public class TrivialDAOImpl implements TrivialDAO {

    private Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
    @Override
    public Call<Question> getRandomQuestion(String Auth, CategoryEnum cat) {
        return retrofit.create(TrivialDAO.class).getRandomQuestion(Auth, cat);
    }
}
