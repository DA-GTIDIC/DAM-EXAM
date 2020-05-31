package cat.udl.tidic.amd.dam_tips.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import cat.udl.tidic.amd.dam_tips.R;

public class Admin extends AppCompatActivity {

    public ImageView Crear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Bundle extras = getIntent().getExtras();
        final int current_contador = extras.getInt("contador");

        Crear = findViewById(R.id.btn_crear);


        Crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this, Formulari.class);
                intent.putExtra("contador",current_contador);
                startActivity(intent);
            }
        });

    }
}
