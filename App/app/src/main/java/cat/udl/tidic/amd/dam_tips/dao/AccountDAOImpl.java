package cat.udl.tidic.amd.dam_tips.dao;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;

import java.util.List;

import cat.udl.tidic.amd.dam_tips.models.Question;
import cat.udl.tidic.amd.dam_tips.network.RetrofitClientInstance;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

public class AccountDAOImpl implements AccountDAO{

    private Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();

    @Override
    public Call<JsonObject> createTokenUser() {
        return  retrofit.create(AccountDAO.class).createTokenUser();
    }

    @Override
    public Call<Void> deleteTokenUser(JsonObject token) {
        return  retrofit.create(AccountDAO.class).deleteTokenUser(token);
    }

    @Override
    public Call<List<Question>> getQuestionList() {
        return retrofit.create(AccountDAO.class).getQuestionList();
    }

    @Override
    public Call<Question> getAQuestion(String category) {
        return retrofit.create(AccountDAO.class).getAQuestion(category);
    }

    @Override
    public Call<Boolean> validateQuestion(int id, String answer) {
        return retrofit.create(AccountDAO.class).validateQuestion(id,answer);
    }


}
