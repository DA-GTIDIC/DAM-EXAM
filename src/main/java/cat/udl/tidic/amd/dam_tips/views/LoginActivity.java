package cat.udl.tidic.amd.dam_tips.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.viewmodels.LoginViewModel;

public class LoginActivity extends CustomActivty {
    // private static final String TAG = "LoginActivity";
    private Button _loginButton;
    private EditText _emailEditText;
    private EditText _passwordEditText;
    private LoginViewModel loginViewModel;
    private LinearLayout _progressBar;
    private TextView _link_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    protected void initView(){
        loginViewModel = new LoginViewModel();
        _progressBar = findViewById(R.id.linearLayoutProgressBar);
        _emailEditText = findViewById(R.id.editTextEmail);
        _passwordEditText = findViewById(R.id.editTextPassword);
        _loginButton = findViewById(R.id.btn_login);
        _link_signup = findViewById(R.id.link_signup);

        _link_signup.setOnClickListener(v -> goTo(SignUpActivity.class));

        _loginButton.setOnClickListener(v -> login());

        loginViewModel.getResponseLogin().observe(this, s -> {
            _progressBar.setVisibility(View.GONE);
            if ( s.startsWith("Error: ")){
                Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
                layoutFocus(true);
            } else {
                goTo(DashboardActivity.class);

            }
        });

    }

    private void login() {
        Log.d(TAG, "Entering into login()...");

        String email = _emailEditText.getText().toString();
        String password = _passwordEditText.getText().toString();

        int isValid = loginViewModel.validate(email, password);

        switch (isValid){
            case LoginViewModel.ERROR_EMPTY_EMAIL:
                _emailEditText.setError(getText(R.string.error_field_required));
                break;

            case LoginViewModel.ERROR_EMPTY_PASS:
                _passwordEditText.setError(getText(R.string.error_field_required));
                break;

            default:
                _emailEditText.setError(null);
                _passwordEditText.setError(null);
                loginViewModel.login(email,password);
                layoutFocus(false);
                _progressBar.setVisibility(View.VISIBLE);
        }

    }

    public void layoutFocus(Boolean enable){
        _emailEditText.setEnabled(enable);
        _passwordEditText.setEnabled(enable);
        _loginButton.setEnabled(enable);
    }



//    public void goTo(Class _class){
//        Intent intent = new Intent(this, _class);
//        startActivity(intent);
//    }
}
