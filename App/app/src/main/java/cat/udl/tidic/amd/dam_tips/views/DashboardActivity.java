package cat.udl.tidic.amd.dam_tips.views;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.models.Question;
import cat.udl.tidic.amd.dam_tips.viewmodels.LoginViewModel;

public class DashboardActivity extends CustomActivty {

    private Button disconnect;
    private LoginViewModel loginViewModel;
    private CardView admi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initView();
    }

    protected void initView() {
        loginViewModel = new LoginViewModel();
        disconnect = findViewById(R.id.disconnect);
        disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.logout();
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

        admi = findViewById(R.id.cardViewAdmin);
        admi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(QuestActivity.class);
            }
        });

    }

    /*
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_dashboard);
            initView();

            admi = findViewById(R.id.cardViewAdmin);
            admi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(DashboardActivity.this, QuestActivity.class);
                    startActivity(i);
                }
            });
        }

        protected void initView() {
            loginViewModel = new LoginViewModel();
            disconnect = findViewById(R.id.disconnect);
            disconnect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loginViewModel.logout();
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


     */
        @Override
        public void onBackPressed(){
            finishAffinity();
        }
}