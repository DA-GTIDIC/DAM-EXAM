package cat.udl.tidic.amd.dam_tips.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cat.udl.tidic.amd.dam_tips.R;

public class admin_activity extends CustomActivty {
    private FloatingActionButton plus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_activity);

        plus = findViewById(R.id.floatingActionButton);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(formulario_activity.class);
            }
        });
    }
}
