package cat.udl.tidic.amd.dam_tips.dao;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;

import java.util.List;

import cat.udl.tidic.amd.dam_tips.models.Question;
import cat.udl.tidic.amd.dam_tips.network.RetrofitClientInstance;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Header;

public class AccountDAOImpl implements AccountDAO{

    private Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();

    @Override
    public Call<JsonObject> createTokenUser() {
        return  retrofit.create(AccountDAO.class).createTokenUser();
    }

    @Override
    public Call<List<Question>> getQuestions() {
        return  retrofit.create(AccountDAO.class).getQuestions();
    }

//    @Override
//    public Call<JsonObject> addQuestion(JsonObject userJson) {
//        return  retrofit.create(AccountDAO.class).addQuestion(userJson);
//    }

    @Override
    public Call<Void> deleteTokenUser(JsonObject token) {
        return  retrofit.create(AccountDAO.class).deleteTokenUser(token);
    }
}
