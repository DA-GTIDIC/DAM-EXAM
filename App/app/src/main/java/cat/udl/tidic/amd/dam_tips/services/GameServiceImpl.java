package cat.udl.tidic.amd.dam_tips.services;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cat.udl.tidic.amd.dam_tips.dao.GameDAOImpl;
import cat.udl.tidic.amd.dam_tips.dao.IGameDAO;
import cat.udl.tidic.amd.dam_tips.models.Answer;
import cat.udl.tidic.amd.dam_tips.models.Game;
import cat.udl.tidic.amd.dam_tips.models.Question;
import cat.udl.tidic.amd.dam_tips.network.RetrofitClientInstance;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GameServiceImpl implements GameServiceI {
    private IGameDAO gameDAO = new GameDAOImpl();
    Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
    public final MutableLiveData<Question> mResponseQuestion;

    public GameServiceImpl(){
        gameDAO = new GameDAOImpl();
        mResponseQuestion = new MutableLiveData<>();
    }

    @Override
    public void getQuestion(String auth, String category) {
        Log.d("getQuestion","getQuestion auth: " + auth + " category" + category + " " );
        gameDAO.getQuestion(auth,category).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int codigo = response.code();
                Log.d("getQuestion","url: " + response.raw().request().url());
                if(codigo == 200){
                    String respuestaBody = null;
                    try{
                        respuestaBody = response.body().string();
                        JSONObject mQuestionjson = new JSONObject(respuestaBody); //respuestaBody.getJSONObject(0);
                        int id_question = mQuestionjson.getInt("id");
                        String question = mQuestionjson.getString("question");
                        String category = mQuestionjson.getString("category");
                        List<Answer> mList = new ArrayList<>();
                        JSONArray mAnswers = mQuestionjson.getJSONArray("answers");
                        for(int i = 0; i < mAnswers.length(); i++){
                            JSONObject ans = mAnswers.getJSONObject(i);
                            int id_ans = ans.getInt("id");
                            String answer = ans.getString("answer");
                            Boolean correct = ans.getBoolean("is_correct");
                            Answer a = new Answer(id_ans, answer, correct);
                            mList.add(a);
                        }
                        Question q = new Question(id_question, question, category, (ArrayList<Answer>) mList);
                        mResponseQuestion.setValue(q);



                    }catch (IOException | JSONException e) {
                        e.printStackTrace();
                        Log.d("getQuestion ", e.toString());
                        mResponseQuestion.setValue(new Question(-1,"","",null));
                    }

                }
                else{
                    Log.d("getQuestion","llamada fallida else con codigo: " + codigo);
                    mResponseQuestion.setValue(new Question(-1,"","",null));

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("getQuestion","llamada fallida");
                mResponseQuestion.setValue(new Question(-1,"","",null));
            }
        });
    }

    @Override
    public MutableLiveData<Question> getLiveDataQuestion() {
        return mResponseQuestion;
    }


}
