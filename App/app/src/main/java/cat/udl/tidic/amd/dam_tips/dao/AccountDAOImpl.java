package cat.udl.tidic.amd.dam_tips.dao;



import com.google.gson.JsonObject;

import cat.udl.tidic.amd.dam_tips.models.Question;
import cat.udl.tidic.amd.dam_tips.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;

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


   /* @Override
    public Call<Void> addQuestion(Question question) {
        return  retrofit.create(AccountDAO.class).addQuestion(questionJSON);
    }*/


}
