package cat.udl.tidic.amd.dam_tips.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.adapters.QuestionAdapter;
import cat.udl.tidic.amd.dam_tips.adapters.QuestionDiffCallback;
import cat.udl.tidic.amd.dam_tips.models.Question;
import cat.udl.tidic.amd.dam_tips.preferences.PreferencesProvider;
import cat.udl.tidic.amd.dam_tips.viewmodels.QuestionViewModel;

public class AdminActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;

    private QuestionViewModel viewModel;
    private String auth;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        this.mPreferences = PreferencesProvider.providePreferences();
        auth = mPreferences.getString("token","");
        initViews();
    }

    private void initViews() {

        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final QuestionAdapter adapter = new QuestionAdapter(new QuestionDiffCallback());
        recyclerView.setAdapter(adapter);

        viewModel = new QuestionViewModel();
        viewModel.getQuestions(auth);
        viewModel.getResponseQuestions().observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                adapter.submitList(questions);
            }
        });

        refreshList();
    }

    protected void refreshList(){
        viewModel.getQuestions(auth);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.questions_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //falta
            case R.id.itemDB:
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
