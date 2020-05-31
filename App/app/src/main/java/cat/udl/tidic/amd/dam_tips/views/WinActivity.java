package cat.udl.tidic.amd.dam_tips.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cat.udl.tidic.amd.dam_tips.R;

public class WinActivity extends AppCompatActivity {

    public Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        back = findViewById(R.id.btnGanar);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent da = new Intent(WinActivity.this,MainActivity.class);
                startActivity(da);
            }
        });
    }
}
