package cat.udl.tidic.amd.dam_tips.views;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.viewmodels.LoginViewModel;

public class DashboardActivity extends CustomActivty {

    private Button disconnect;
    private CardView playButton;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initView();
    }



    protected void initView(){
        loginViewModel = new LoginViewModel();
        playButton = findViewById(R.id.cardViewPlay);
        disconnect = findViewById(R.id.disconnect);


        loginViewModel.getResponseLogin().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!s.startsWith("Error")) {
                    goTo(LoginActivity.class);
                }
            }
        });

        setOnClickListeners();

    }


    void setOnClickListeners()
    {
        disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.logout();
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(GameActivity.class);
            }
        });


    }

    @Override
    public void onBackPressed(){
        finishAffinity();
    }

}
