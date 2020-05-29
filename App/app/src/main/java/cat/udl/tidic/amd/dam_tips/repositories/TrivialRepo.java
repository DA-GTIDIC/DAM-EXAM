package cat.udl.tidic.amd.dam_tips.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;


import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cat.udl.tidic.amd.dam_tips.dao.TrivialDAO;
import cat.udl.tidic.amd.dam_tips.dao.TrivialDAOImpl;
import cat.udl.tidic.amd.dam_tips.models.CategoryEnum;
import cat.udl.tidic.amd.dam_tips.models.Question;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrivialRepo {

    private String TAG = getClass().getSimpleName();

    private TrivialDAO trivialDAO;

    private MutableLiveData<Question> mQuestion;

    public TrivialRepo(){
        trivialDAO = new TrivialDAOImpl();
        mQuestion = new MutableLiveData<>();

    }

    public void getRandomQuestion(String Auth, CategoryEnum cat){
        trivialDAO.getRandomQuestion(Auth, cat).enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {
                if (response.code() == 200) {
                    Question question = response.body();
                    Log.d(TAG,question.toString());

                    mQuestion.setValue(question);


                }else{
                    Log.d(TAG, "getRandomQuestion ----> CODE = " + response.code() + " , " +response.message());
                }
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }

    public MutableLiveData<Question> getQuestionResponse(){
        return mQuestion;
    }



}
