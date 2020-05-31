package cat.udl.tidic.amd.dam_tips.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.List;

import cat.udl.tidic.amd.dam_tips.dao.QuestionDAO;
import cat.udl.tidic.amd.dam_tips.dao.QuestionDAOImpl;
import cat.udl.tidic.amd.dam_tips.models.Question;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionRepo {

    private String TAG="QuestionRepo";

    private QuestionDAO questionDAO;
    private MutableLiveData<List<Question>> mResponseQuestions;

    public QuestionRepo(){
        this.questionDAO = new QuestionDAOImpl();
        this.mResponseQuestions = new MutableLiveData<>();
    }

    public void getQuestions(String auth){

        questionDAO.getQuestions(auth).enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                int code = response.code();
                Log.d(TAG,  "getQuestions() -> ha rebut del backend un codi:  " + code);

                if (code == 200 ){
                    List<Question> questions = response.body();
                    Log.d(TAG,  "getQuestions() -> ha rebut una llista de mida: "+ questions.size());
                    mResponseQuestions.setValue(questions);
                }
                else{
                    try {
                        String error_msg = "Error: " + response.errorBody().string();
                        Log.d(TAG,  "createTokenUser() -> ha rebut l'error:  " + error_msg);
                        mResponseQuestions.setValue(null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                String error_msg = "Error: " + t.getMessage().toString();
                Log.d(TAG,  "getEvents() onFailure() -> ha rebut el missatge:  " + error_msg);
                mResponseQuestions.setValue(null);
            }
        });
    }

    public MutableLiveData<List<Question>> getmResponseQuestions(){return mResponseQuestions;}
}
