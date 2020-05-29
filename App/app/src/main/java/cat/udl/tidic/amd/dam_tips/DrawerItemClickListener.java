package cat.udl.tidic.amd.dam_tips;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import static androidx.core.content.ContextCompat.startActivity;

public class DrawerItemClickListener implements ListView.OnItemClickListener {

    private Context c;
    private MainClassViewModel mc;
    public DrawerItemClickListener(Context c)
    {
        this.c = c;
    }
    public DrawerItemClickListener(Context c, MainClassViewModel mc)
    {
        this.c = c;
        this.mc = mc;
    }
    //Aquesta classe afegeix un "OnClcickListener" a cada item de la llista.
    //Exemple : Tinc la llista recyclerView
    //Per a fer que els items de la llista es puguin clickar ...
    //... haig de fer : recyclerView.setOnItemClickListener(new DrawerItemClickListener(this));
    //on "this" és el context de la classe

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        DataModel d = (DataModel) parent.getItemAtPosition(position);
        //Si el item que s'ha clickat és de la classe MenuList(el menu de la pàgina principal...)
        if (d instanceof DataModel.MenuList)
        {
            switch (position)
            {
                case 0:
                    Log.d("Open profile", "clicked");
                    goToProfile();
                    break;
            }

        }
        //Si el item que s'ha clickat és un favor...
        else if (d instanceof DataModel.Favour)
        {
            //goToSeeAnunci((DataModel.Favour)d);
        }
        else
        {
            Log.d("No type", "mafren");
        }
    }

    private void goTo(Class classe)
    {
        Intent intent = new Intent (c, classe);
        startActivity(c,intent,Bundle.EMPTY);
    }

    private void goToProfile()
    {
        //Intent intent = new Intent (c, ProfileView.class);
        Bundle b= new Bundle();
        b.putBoolean("myprofile", true);
        //startActivity(c,intent,b);
    }


}