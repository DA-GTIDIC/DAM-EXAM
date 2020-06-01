package cat.udl.tidic.amd.dam_tips.views;
import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.adapters.QuestAdapter;
import cat.udl.tidic.amd.dam_tips.models.Question;
import cat.udl.tidic.amd.dam_tips.adapters.QuestDiffCallback;
import cat.udl.tidic.amd.dam_tips.dao.AccountDAO;
import cat.udl.tidic.amd.dam_tips.network.RetrofitClientInstance;
import cat.udl.tidic.amd.dam_tips.preferences.PreferencesProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


import static cat.udl.tidic.amd.dam_tips.R.layout.activity_quest;


public class QuestActivity extends AppCompatActivity {

    private RecyclerView rvQuestList;
    private QuestAdapter qadapter;
    private TextView colours;

    private String TAG = "Questions";
    private AccountDAO accountDAO;
    private SharedPreferences mPreferences;
    private String tok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_quest);

        Intent i = getIntent();

        rvQuestList = findViewById(R.id.rvquestionList);
        rvQuestList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        qadapter = new cat.udl.tidic.amd.dam_tips.adapters.QuestAdapter(new cat.udl.tidic.amd.dam_tips.adapters.QuestDiffCallback());
        rvQuestList.setAdapter(qadapter);

        accountDAO = RetrofitClientInstance.getRetrofitInstance().create(AccountDAO.class);
        this.mPreferences = PreferencesProvider.providePreferences();
        tok = this.mPreferences.getString("token", "");

        colours = findViewById(R.id.quest_colour);

        qadapter.setOnItemClickListener(new QuestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Question question) {
                Log.d(TAG, "Question works");
            }
        });

        getQuestionList();

    }

    public void getQuestionList() {

        Call<List<Question>> getquest = accountDAO.getQuestList();
        getquest.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                if (response.code() == 200) {
                    Log.d(TAG, "Llista d'assignatures obtinguda");
                    List<Question> list = response.body();
                    Log.d(TAG, "Llista retornada" + list);
                } else {
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
