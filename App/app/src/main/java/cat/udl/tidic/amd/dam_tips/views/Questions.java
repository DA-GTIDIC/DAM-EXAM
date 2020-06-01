package cat.udl.tidic.amd.dam_tips.views;
import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.dao.AccountDAO;
import cat.udl.tidic.amd.dam_tips.models.Question;
import cat.udl.tidic.amd.dam_tips.network.RetrofitClientInstance;
import cat.udl.tidic.amd.dam_tips.preferences.PreferencesProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Questions extends AppCompatActivity {

    private RecyclerView questionListView;
    private Question question;
    private cat.udl.tidic.amd.dam_tips.adapters.QuestAdapter questAdapter;
    private TextView colours;

    private String TAG = "Questions";
    private SharedPreferences mPreferences;
    private AccountDAO accountDAO;
    ArrayList <Question> quest_data = new ArrayList<>();
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);

        Intent intent = getIntent();

        questionListView = findViewById(R.id.rvQuestList);
        questionListView.setLayoutManager(new LinearLayoutManager(this));
        questAdapter = new cat.udl.tidic.amd.dam_tips.adapters.QuestAdapter(new cat.udl.tidic.amd.dam_tips.adapters.QuestDiffCallback());
        questionListView.setAdapter(questAdapter);

        accountDAO = RetrofitClientInstance.getRetrofitInstance().create(AccountDAO.class);
        this.mPreferences = PreferencesProvider.providePreferences();
        token = this.mPreferences.getString("token", "");

        colours = findViewById(R.id.quest_colour);


        questAdapter.setOnItemClickListener(new cat.udl.tidic.amd.dam_tips.adapters.QuestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Question question) {
                Log.d(TAG, "Question works");
            }
        });

        getQuestion();
    }

    public void getQuestion(){
        Call<List<Question>> call_get_question = accountDAO.getQuestList();
        call_get_question.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                Log.d(TAG,"code:"+response.code());
                if (response.code() == 200){
                    quest_data = (ArrayList<Question>) response.body();
                    questAdapter.submitList(quest_data);
                } else{
                    Throwable t = null;
                    Log.d(TAG, "Error en la comunicació amb la API: " + t.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
            }
        });
    }
}