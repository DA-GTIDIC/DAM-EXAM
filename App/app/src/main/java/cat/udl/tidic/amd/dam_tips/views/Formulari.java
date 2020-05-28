package cat.udl.tidic.amd.dam_tips.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cat.udl.tidic.amd.dam_tips.R;


public class Formulari extends AppCompatActivity {

    public Button OKPregunta;
    private int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulari);

        OKPregunta = findViewById(R.id.btn_crearpregunta);

        Bundle extras = getIntent().getExtras();
        contador = extras.getInt("contador");

        OKPregunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                contador = contador + 1;

                Intent i = new Intent(Formulari.this, DashboardActivity.class);
                i.putExtra("contador", contador);
                startActivity(i);
            }
        });
    }
}
