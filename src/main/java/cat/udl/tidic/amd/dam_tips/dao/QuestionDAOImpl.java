package cat.udl.tidic.amd.dam_tips.dao;

import java.util.List;

import cat.udl.tidic.amd.dam_tips.models.Question;
import cat.udl.tidic.amd.dam_tips.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Retrofit;

public class QuestionDAOImpl implements QuestionDAO {
    private Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();

    @Override
    public Call<List<Question>> getQuestions(String auth){return retrofit.create(QuestionDAO.class).getQuestions(auth);}
}
