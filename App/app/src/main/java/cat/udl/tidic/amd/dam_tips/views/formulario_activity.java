package cat.udl.tidic.amd.dam_tips.views;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Objects;

import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.dao.AccountDAO;
import cat.udl.tidic.amd.dam_tips.dao.AccountDAOImpl;
import cat.udl.tidic.amd.dam_tips.models.Answer;
import cat.udl.tidic.amd.dam_tips.models.Question;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class formulario_activity extends CustomActivty {
    private TextView pregunta;
    private TextView resp1;
    private TextView resp2;
    private TextView resp3;
    private TextView resp4;
    private Spinner spinner;
    private Button guardar;


    private Object JsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_activity);

        spinner = findViewById(R.id.spinner);
        guardar = findViewById(R.id.button);
        pregunta = findViewById(R.id.edit_pregunta);
        resp1 = findViewById(R.id.editText);
        resp2 = findViewById(R.id.editText2);
        resp3 = findViewById(R.id.editText3);
        resp4 = findViewById(R.id.editText4);





        String[] categorias = new String[]{
                "db", "os", "net", "patterns"
        };

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categorias);
        spinner.setAdapter(adaptador);


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashboardActivity.cont += 1;
                goTo(DashboardActivity.class);


            }
        });

    }




}
