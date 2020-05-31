package cat.udl.tidic.amd.dam_tips;

import android.app.Application;

import cat.udl.tidic.amd.dam_tips.preferences.PreferencesProvider;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PreferencesProvider.init(this);
    }
}

