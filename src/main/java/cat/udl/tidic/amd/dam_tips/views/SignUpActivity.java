package cat.udl.tidic.amd.dam_tips.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.utils.EULA;

public class SignUpActivity extends CustomActivty {

   // final String TAG = "SignUpActivity";

    final EULA eula_dialog = new EULA(this);
    CheckBox _agreement_terms_and_conditions;
    TextView _link_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initView();
    }


    private void initView(){

        _link_login = findViewById(R.id.link_login);

        _link_login.setOnClickListener(v -> goTo(LoginActivity.class));

        // Accepting Terms and conditions
        _agreement_terms_and_conditions = findViewById(R.id.agreement_terms_and_conditions);
        _agreement_terms_and_conditions.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        eula_dialog.show(R.id.agreement_terms_and_conditions);
                    }
                });
    }

//    public void goTo(Class _class){
//        Log.d(TAG, "goTo() -> Navigate to " + _class.getSimpleName());
//        Intent intent = new Intent(this, _class);
//        startActivity(intent);
//    }





}