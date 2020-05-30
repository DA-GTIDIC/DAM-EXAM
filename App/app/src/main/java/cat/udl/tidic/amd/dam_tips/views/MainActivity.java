package cat.udl.tidic.amd.dam_tips.views;

import android.os.Bundle;
import android.util.Log;

import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.viewmodels.MainActivityViewModel;

public class MainActivity extends CustomActivty {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        MainActivityViewModel mainActivityViewModel = new MainActivityViewModel();

        if (mainActivityViewModel.isCurrentLogIn()) {
            Log.d(TAG, "onCreate() -> El usuario ya tiene token, por lo tanto entro." );
            goTo(DashboardActivity.class);
        }
        else {
            Log.d(TAG, "onCreate() -> El usuario no tiene token, por lo tanto ir a login." );
            goTo(LoginActivity.class);
        }
    }


}
