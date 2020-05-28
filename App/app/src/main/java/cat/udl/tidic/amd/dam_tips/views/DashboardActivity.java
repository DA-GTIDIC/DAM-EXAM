package cat.udl.tidic.amd.dam_tips.views;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.viewmodels.LoginViewModel;

public class DashboardActivity extends CustomActivty {

    private Button disconnect;
    private LoginViewModel loginViewModel;
    private CardView admin;
    //Tasca3. Creem contador i textview per mostrar resposta
    private TextView ResultTextView;
    private int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initView();

        ResultTextView = findViewById(R.id.textview_contador);

        try{
            Bundle extras = getIntent().getExtras();
            int i = extras.getInt("contador");
            //Log.d("DashboardActivity",String.valueOf(i));
            contador = i;
            ResultTextView.setText(String.valueOf(i));

        }catch (Exception e){

            System.out.println(e);

        }



        //Tasca1.
        admin = findViewById(R.id.cardViewRanking);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, Admin.class);
                intent.putExtra("contador",contador);
                startActivity(intent);
            }
        });
    }





    protected void initView(){
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


    @Override
    public void onBackPressed(){
        finishAffinity();
    }

}
