package cat.udl.tidic.amd.dam_tips.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.dao.AccountDAO;
import cat.udl.tidic.amd.dam_tips.dao.AccountDAOImpl;
import cat.udl.tidic.amd.dam_tips.models.Pregunta;
import cat.udl.tidic.amd.dam_tips.models.User;
import cat.udl.tidic.amd.dam_tips.viewmodels.LoginViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends CustomActivty {

    static int count = 0;
    private Button disconnect;
    private LoginViewModel loginViewModel;
    private CardView admin;
    private TextView contad;
    private TextView preguntasTot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Intent intent = getIntent();
        contad =findViewById(R.id.contador);
        String a = intent.getStringExtra("contador");
        Log.d("contador",  String.valueOf(count));
        contad.setText(String.valueOf(count));
        preguntasTot = findViewById(R.id.preguntasTot);
        AccountDAO dao = new AccountDAOImpl();
        Call<User> call_get = dao.getUserProfile();
        call_get.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                assert user != null;
                iniciarTot(user.getId());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        initView();
    }

    public void iniciarTot(Integer user){
        Log.d("id", String.valueOf(user));
        AccountDAO dao = new AccountDAOImpl();
        Call<String> call_get = dao.getNumber(String.valueOf(12));
        call_get.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String num = response.body();
                preguntasTot.setText(num);

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }


    protected void initView(){
        loginViewModel = new LoginViewModel();
        disconnect = findViewById(R.id.disconnect);
        admin = findViewById(R.id.cardViewRanking);

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
        adminClick();;

    }

    public void adminClick(){
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(AdminActivity.class);
            }
        });
    }

    @Override
    public void onBackPressed(){
        finishAffinity();
    }

}
