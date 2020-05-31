package cat.udl.tidic.amd.dam_tips.views;
import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.adapters.QuestAdapter;
import cat.udl.tidic.amd.dam_tips.models.Question;
import cat.udl.tidic.amd.dam_tips.adapters.QuestDiffCallback;
import cat.udl.tidic.amd.dam_tips.dao.AccountDAO;
import cat.udl.tidic.amd.dam_tips.network.RetrofitClientInstance;
import cat.udl.tidic.amd.dam_tips.preferences.PreferencesProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


import static cat.udl.tidic.amd.dam_tips.R.layout.activity_quest;


public class QuestActivity extends AppCompatActivity {


    private TextView tvquestion;
    private RecyclerView rvQuestList;
    private QuestAdapter qadapter;
    private TextView colours;
    private Question question;

    private String TAG = "Questions";

    ArrayList<Question> questdata = new ArrayList<>();
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
        qadapter = new QuestAdapter(new QuestDiffCallback());
        rvQuestList.setAdapter(qadapter);

        accountDAO = RetrofitClientInstance.getRetrofitInstance().create(AccountDAO.class);
        this.mPreferences = PreferencesProvider.providePreferences();
        tok = this.mPreferences.getString("token", "");

        colours = findViewById(R.id.quest_colour);



       /*qadapter.setOnItemClickListener(new QuestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Question question) {
                Log.d(TAG, "Question working");
                //openDialog();
            }
        });
        */


    }

}
