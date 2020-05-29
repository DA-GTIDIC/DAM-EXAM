package cat.udl.tidic.amd.dam_tips.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;

import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.dao.AccountDAO;
import cat.udl.tidic.amd.dam_tips.models.Account;
import cat.udl.tidic.amd.dam_tips.models.Question;
import cat.udl.tidic.amd.dam_tips.viewmodels.LoginViewModel;
import cat.udl.tidic.amd.dam_tips.viewmodels.MainActivityViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends CustomActivty {

    private Button disconnect;
    private LoginViewModel loginViewModel;
    private MainActivityViewModel mainActivityViewModel;
    private CardView admin;
    private TextView contador;
    private EditText num_preguntesAPI;
    static int cont = 0;
    private JsonObject question;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
//        getQuestions();


        initView();
    }



    protected void initView(){
        loginViewModel = new LoginViewModel();
        disconnect = findViewById(R.id.disconnect);
        admin=findViewById(R.id.cardViewRanking);
        contador=findViewById(R.id.contador);
        num_preguntesAPI = findViewById(R.id.edit_preguntes);
        mainActivityViewModel = new MainActivityViewModel();


        contador.setText(String.valueOf(cont));

        mainActivityViewModel.getSizeQUesiotn();
        mainActivityViewModel.getresponseSize().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                num_preguntesAPI.setText(integer.toString());
            }
        });

        disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    loginViewModel.logout();
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(admin_activity.class);
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

//public void getQuestions() {
//
//        Call<JsonObject> call_get = AccountDAO.getQuestions();
//        call_get.enqueue(new Callback<Question>() {
//            @Override
//            public void onResponse(Call<Question> call, Response<Question> response) {
//
//                if (response.code() == 200) {
//                    Log.d(TAG, "getQuestion -> API ha devuelto el codigo: " + response.code() + "");
//                    Question q = response.body();
//                    BindQuestionTOUI(q);
//                } else {
//                    Log.d(TAG, "getQuestion -> API NO ha devuelto el codigo: " + response.code() + "");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Question> call, Throwable t) {
//                Log.d(TAG, "getQuestion -> Problemas con la comunicación con API: " + t.getMessage() + "");
//
//            }
//        });
//    }


    public void BindQuestionTOUI(Question q) {
        if (q != null) {
            num_preguntesAPI.setText(q.getQuestion().length());
        }
    }



    @Override
    public void onBackPressed(){
        finishAffinity();
    }

}
