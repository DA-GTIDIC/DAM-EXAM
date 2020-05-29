package cat.udl.tidic.amd.dam_tips.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.viewmodels.LoginViewModel;

public class DashboardActivity extends CustomActivty {

    private Button disconnect;
    private CardView play;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initView();
    }



    protected void initView(){
        loginViewModel = new LoginViewModel();
        disconnect = findViewById(R.id.disconnect);
        disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.logout();
                goTo(LoginActivity.class);
            }
        });
        play = findViewById(R.id.cardViewPlay);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(GameActivity.class);
            }
        });


        loginViewModel.getResponseLogin().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!s.startsWith("Error")) {
                    goTo(LoginActivity.class);
                }
            }
        });

    }


    @Override
    public void onBackPressed(){
        finishAffinity();
    }

}
