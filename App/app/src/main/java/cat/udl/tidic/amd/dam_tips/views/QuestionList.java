package cat.udl.tidic.amd.dam_tips.views;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cat.udl.tidic.amd.dam_tips.adapters.QuestionAdapter;
import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.adapters.QuestionDiffCallback;
import cat.udl.tidic.amd.dam_tips.dao.AccountDAO;
import cat.udl.tidic.amd.dam_tips.models.Question;
import cat.udl.tidic.amd.dam_tips.network.RetrofitClientInstance;
import cat.udl.tidic.amd.dam_tips.preferences.PreferencesProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionList extends AppCompatActivity {

    private RecyclerView rvQuestionList;
    private Question question;
    private TextView questcolours;
    private QuestionAdapter questionAdapter;

    private  String TAG = "Question";

    private SharedPreferences sharedPreferences;
    private AccountDAO accountDAO;
    ArrayList <Question> dataquestion = new ArrayList<>();
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        setContentView(R.layout.activity_quest);

        rvQuestionList = findViewById(R.id.questList);
        rvQuestionList.setLayoutManager(new LinearLayoutManager(this));
        questionAdapter = new QuestionAdapter(new QuestionDiffCallback());
        rvQuestionList.setAdapter(questionAdapter);

        accountDAO = RetrofitClientInstance.getRetrofitInstance().create(AccountDAO.class);
        questcolours = findViewById(R.id.colorquest);
        Context context;
        this.sharedPreferences = PreferencesProvider.providePreferences();
        token = this.sharedPreferences.getString("token", " ");

        questionAdapter.setOnItemClickListener(new QuestionAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Question question) {
                Log.d(TAG, " Question List is working");
                dialogOpen();
            }
        });

        getQuestion();
    }

    public void getQuestion(){

        Call<List<Question>> getQuestioncall = accountDAO.getQuestionList();
        getQuestioncall.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {

                //canviar nom al d abans
                Log.d(TAG, "CODE: " + response.code());
                if (response.code() == 200){

                    //obtenir dades consulta
                    dataquestion = (ArrayList<Question>) response.body();
                    questionAdapter.submitList(dataquestion);
                }
                else {
                    //notificar problemes consulta (borrar el else despres)
                }
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Log.d(TAG, "Error en la comunicació amb la API: " + t.getMessage());
            }
        });
    }


    public void dialogOpen(){
        DialogRate opdialog = DialogRate.newInstance(this);
        opdialog.show(getSupportFragmentManager(), "Dialog");
    }
}
