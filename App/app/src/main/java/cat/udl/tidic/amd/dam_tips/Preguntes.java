package cat.udl.tidic.amd.dam_tips;

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

import cat.udl.tidic.amd.dam_tips.adapter.PreguntesAdapter;
import cat.udl.tidic.amd.dam_tips.dao.AccountDAO;
import cat.udl.tidic.amd.dam_tips.models.Pregunta;
import cat.udl.tidic.amd.dam_tips.network.RetrofitClientInstance;
import cat.udl.tidic.amd.dam_tips.preferences.PreferencesProvider;
import cat.udl.tidic.amd.dam_tips.views.DialogClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Preguntes extends AppCompatActivity {

    private TextView question;
    private RecyclerView questionListView;
    private Pregunta pregunta;
    private PreguntesAdapter preguntaAdapter;
    private TextView colors;

    private String TAG = "PREGUNTES";

    ArrayList <Pregunta>preguntes_data = new ArrayList<>();
    private SharedPreferences mPreferences;
    private AccountDAO accountDAO;

    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntes);

        Intent intent = getIntent();

        questionListView = findViewById(R.id.preguntesList);
        questionListView.setLayoutManager(new LinearLayoutManager(this));
        preguntaAdapter = new PreguntesAdapter(new cat.udl.tidic.amd.dam_tips.adapter.PreguntesDiffCallback());
        questionListView.setAdapter(preguntaAdapter);

        colors = findViewById(R.id.question_colour);

        accountDAO = RetrofitClientInstance.getRetrofitInstance().create(AccountDAO.class);

        this.mPreferences = PreferencesProvider.providePreferences();
        token = this.mPreferences.getString("token", "");


        preguntaAdapter.setOnItemClickListener(new PreguntesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Pregunta preguntaa) {
                Log.d(TAG, "funciona");
                openDialog();
            }
        });


        SearchQuestion();
    }

    public void SearchQuestion(){


        Call<List<Pregunta>> call_get_question = accountDAO.getQuestionlist();
        call_get_question.enqueue(new Callback<List<Pregunta>>() {
            @Override
            public void onResponse(Call<List<Pregunta>> call, Response<List<Pregunta>> response) {
                Log.d(TAG,"code:"+response.code());
                if (response.code() == 200){

                    // Obtenim les dades de la consulta
                    preguntes_data = (ArrayList<Pregunta>) response.body();
                    preguntaAdapter.submitList(preguntes_data);
                } else{
                    // notificar problemes amb la consulta
                }
            }

            @Override
            public void onFailure(Call<List<Pregunta>> call, Throwable t) {
                // Notificar problemes amb la red
            }
        });



    }

    public void openDialog() {
        DialogClass dialogDesc =  DialogClass.newInstance(this );
        dialogDesc.show(getSupportFragmentManager(),"Dialog description");

    }


}
