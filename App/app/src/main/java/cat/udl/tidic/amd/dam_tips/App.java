package cat.udl.tidic.amd.dam_tips;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import cat.udl.tidic.amd.dam_tips.preferences.PreferencesProvider;

public class App extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public static Context getAppContext() {
        return App.context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        PreferencesProvider.init(this);
        App.context = getApplicationContext();
    }
}

