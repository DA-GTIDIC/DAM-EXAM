package cat.udl.tidic.amd.dam_tips.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.dao.AccountDAO;
import cat.udl.tidic.amd.dam_tips.dao.AccountDAOImpl;


public class Formulari extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] numanswer = { "2", "3", "4"};
    String[] category = {"db", "os", "net", "patterns"};

    public Button OKPregunta;
    public ImageView back;
    public Spinner Spinner;
    public Spinner Spinner2;
    public EditText EditTextQuestion;
    public EditText EditTextAnswer1;
    public EditText EditTextAnswer2;
    public EditText EditTextAnswer3;
    public EditText EditTextAnswer4;
    public String sT;
    public Switch SwitchA1;
    public Switch SwitchA2;
    public Switch SwitchA3;
    public Switch SwitchA4;
    private TextView TextoSalida;

    private int contador = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulari);

        OKPregunta = findViewById(R.id.btn_crearpregunta);
        back = findViewById(R.id.imageView_back);

        AccountDAO dao = new AccountDAOImpl();

        Bundle extras = getIntent().getExtras();
        contador = extras.getInt("contador");

        EditTextQuestion = findViewById(R.id.EditText_question);
        EditTextAnswer1 = findViewById(R.id.EditText_Answer1);
        EditTextAnswer2 = findViewById(R.id.EditText_Answer2);
        EditTextAnswer3 = findViewById(R.id.EditText_Answer3);
        EditTextAnswer4 = findViewById(R.id.EditText_Answer4);
        SwitchA1 = findViewById(R.id.Switch_a1);
        SwitchA2 = findViewById(R.id.Switch_a2);
        SwitchA3 = findViewById(R.id.Switch_a3);
        SwitchA4 = findViewById(R.id.Switch_a4);
        TextoSalida = findViewById(R.id.TextView_salida);


        OKPregunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Formulari.this, DashboardActivity.class);
                String question = EditTextQuestion.getText().toString();
                if (!question.equals("")) {
                    if (SwitchA1.isChecked() || SwitchA2.isChecked() || SwitchA3.isChecked() || SwitchA4.isChecked()) {
                        contador = contador + 1;
                        i.putExtra("contador", contador);
                        startActivity(i);
                    } else {
                        TextoSalida.setText("Select the correct answers");
                    }
                } else {
                    TextoSalida.setText("Complete the question");
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Formulari.this, Admin.class);

                startActivity(i);
            }
        });

        //spinner
        Spinner = findViewById(R.id.spinner);
        Spinner.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, numanswer);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner.setAdapter(aa);

        Spinner2 = findViewById(R.id.spinner2);
        Spinner2.setOnItemSelectedListener(this);
        ArrayAdapter aaa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,category);
        aaa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner2.setAdapter(aaa);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        //Toast.makeText(getApplicationContext(),numanswer[position] , Toast.LENGTH_LONG).show();
        switch(adapterView.getId()) {
            case R.id.spinner:
                sT = Spinner.getSelectedItem().toString();
                if(sT.equals("2")){
                    EditTextAnswer1.setEnabled(true);
                    EditTextAnswer2.setEnabled(true);
                    EditTextAnswer1.setVisibility(View.VISIBLE);
                    EditTextAnswer2.setVisibility(View.VISIBLE);
                    EditTextAnswer3.setVisibility(View.INVISIBLE);
                    EditTextAnswer4.setVisibility(View.INVISIBLE);
                    SwitchA1.setVisibility(View.VISIBLE);
                    SwitchA2.setVisibility(View.VISIBLE);
                    SwitchA3.setVisibility(View.INVISIBLE);
                    SwitchA4.setVisibility(View.INVISIBLE);



                }else if(sT.equals("3")){
                    EditTextAnswer1.setEnabled(true);
                    EditTextAnswer2.setEnabled(true);
                    EditTextAnswer3.setEnabled(true);
                    EditTextAnswer1.setVisibility(View.VISIBLE);
                    EditTextAnswer2.setVisibility(View.VISIBLE);
                    EditTextAnswer3.setVisibility(View.VISIBLE);
                    EditTextAnswer4.setVisibility(View.INVISIBLE);
                    SwitchA1.setVisibility(View.VISIBLE);
                    SwitchA2.setVisibility(View.VISIBLE);
                    SwitchA3.setVisibility(View.VISIBLE);
                    SwitchA4.setVisibility(View.INVISIBLE);

                }else if(sT.equals("4")){
                    EditTextAnswer1.setEnabled(true);
                    EditTextAnswer2.setEnabled(true);
                    EditTextAnswer3.setEnabled(true);
                    EditTextAnswer4.setEnabled(true);
                    EditTextAnswer1.setVisibility(View.VISIBLE);
                    EditTextAnswer2.setVisibility(View.VISIBLE);
                    EditTextAnswer3.setVisibility(View.VISIBLE);
                    EditTextAnswer4.setVisibility(View.VISIBLE);
                    SwitchA1.setVisibility(View.VISIBLE);
                    SwitchA2.setVisibility(View.VISIBLE);
                    SwitchA3.setVisibility(View.VISIBLE);
                    SwitchA4.setVisibility(View.VISIBLE);


                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
