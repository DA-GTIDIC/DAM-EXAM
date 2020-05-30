package cat.udl.tidic.amd.dam_tips.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.dao.AccountDAO;
import cat.udl.tidic.amd.dam_tips.dao.AccountDAOImpl;
import cat.udl.tidic.amd.dam_tips.models.Categories;
import cat.udl.tidic.amd.dam_tips.models.Pregunta;
import cat.udl.tidic.amd.dam_tips.models.Respuesta;
import cat.udl.tidic.amd.dam_tips.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearActivity extends AppCompatActivity {
    private EditText pregunta;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private Button fin;
    private Spinner spinner;
    public int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);
        pregunta = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText5 = findViewById(R.id.editText5);
        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);

        AccountDAO dao = new AccountDAOImpl();
        fin = findViewById(R.id.button);
        spinner = findViewById(R.id.spinner);
        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashboardActivity.count++;
                Log.d("contador",  String.valueOf(contador));
                Pregunta pregunta_ = new Pregunta();

                try {
                    ArrayList<Respuesta> respuestas = new ArrayList<>();
                    pregunta_.setQuestion(pregunta.getText().toString());
                    if(spinner.getSelectedItem().toString().equals(Categories.db.toString())){
                        pregunta_.setCategory(Categories.db);
                    }
                    if(spinner.getSelectedItem().toString().equals(Categories.net.toString())){
                        pregunta_.setCategory(Categories.net);
                    }
                    if(spinner.getSelectedItem().toString().equals(Categories.os.toString())){
                        pregunta_.setCategory(Categories.os);
                    }
                    if(spinner.getSelectedItem().toString().equals(Categories.patterns.toString())){
                        pregunta_.setCategory(Categories.patterns);
                    }

                    Respuesta respuesta = new Respuesta();
                    if (!editText2.getText().toString().equals("")) {
                        respuesta.setAnswer(editText2.getText().toString());
                        if(checkBox1.isChecked()) {
                            respuesta.setIs_correct(true);
                        }
                        else{
                            respuesta.setIs_correct(false);
                        }
                        respuestas.add(respuesta);
                    }

                    if (!editText3.getText().toString().equals("")) {
                        respuesta.setAnswer(editText3.getText().toString());
                        if(checkBox2.isChecked()) {
                            respuesta.setIs_correct(true);
                        }
                        else{
                            respuesta.setIs_correct(false);
                        }
                        respuestas.add(respuesta);
                    }

                    if (!editText4.getText().toString().equals("")) {
                        respuesta.setAnswer(editText4.getText().toString());
                        if(checkBox3.isChecked()) {
                            respuesta.setIs_correct(true);
                        }
                        else{
                            respuesta.setIs_correct(false);
                        }
                        respuestas.add(respuesta);
                    }

                    if (!editText5.getText().toString().equals("")) {
                        respuesta.setAnswer(editText5.getText().toString());
                        if(checkBox4.isChecked()) {
                            respuesta.setIs_correct(true);
                        }
                        else{
                            respuesta.setIs_correct(false);
                        }
                        respuestas.add(respuesta);
                    }

                    pregunta_.setRespuestas(respuestas);
                    pregunta_.setOwner_id(getNumero());



                    Call<Void> call =  dao.createPregunta(pregunta_);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() == 200) {
                                Toast.makeText(CrearActivity.this, "Question registered", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent (CrearActivity.this, DashboardActivity.class);
                                startActivityForResult(intent, 0);
                            } else {
                                try {
                                    Toast.makeText(CrearActivity.this, Objects.requireNonNull(response.errorBody()).string(), Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });
                }
                catch (Exception e){
                    System.out.println("");
                }


            }
        });

        initSpinner();
    }

    public int getNumero(){
       /* AccountDAO dao = new AccountDAOImpl();
        Call<User> call_get = dao.getUserProfile();
        call_get.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                assert user != null;
                Log.d("owner", String.valueOf(user.getId()));

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });*/
        return 12;
    }
    public void initSpinner() {
        String[] arraySpinner = new String[]{
                "db", "os", "net", "patterns"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        spinner.setAdapter(adapter);
    }

}
