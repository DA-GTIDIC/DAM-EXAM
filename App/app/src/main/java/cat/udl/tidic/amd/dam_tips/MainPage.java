package cat.udl.tidic.amd.dam_tips;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class MainPage extends AppCompatActivity  {
    private DrawerLayout drawerLayout;
    private ListView llista;
    private ListView recyclerView;
    private Button uploadFavour;
    MainClassViewModel mainClassViewModel;
    DrawerItemCustomAdapter adapter_event;
    TabLayout tabs;
    View googleMap;
    DataModel.Favour[] mapFavours;
    Spinner filterSpinner;
    Spinner filterSpinnerCategory;
    MutableLiveData<UserModel> userModel = new MutableLiveData<UserModel>();
    DataModel dataModel;



    public MainPage() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        mainClassViewModel = new MainClassViewModel(this, this);
        getAllActivityData();
        //addListeners();
        setScrollListener();
        setUpObserver();



    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }

    private void setUpObserver()
    {

            mainClassViewModel.getAllFavours().observe(this, this::onGetFavoursData);


    }

    private void onGetFavoursData(List<DataModel.Favour> all_f)
    {
        DataModel.Favour[] eventList = all_f.toArray(new DataModel.Favour[0]);
        mapFavours = eventList;

        //Si el adaptador es nul, vol dir que no hi havien favors previament
       // if (adapter_event == null)
        //{
            //Posem els favors que ens retorna la crida i ja esta
            adapter_event = new DrawerItemCustomAdapter(this, R.layout.favours_list, eventList, this);
        //}
        //En cas contrari, vol dir que hem carregat més favors
       // else
        //{
            //Concateno l'array de favors que ja teniem amb els que ens retorna la crida a al API
         //   DataModel.Favour[] concatenateArray = (DataModel.Favour[]) ArrayUtils.appendToArray(adapter_event.getData(),eventList);
         //   adapter_event.setData(concatenateArray);
       // }
        Log.d("QUESTIONS--------", Arrays.toString(all_f.toArray(new DataModel.Favour[0])));
        Log.d("QUESTIONS----------", Arrays.toString(eventList));
        recyclerView.setAdapter(adapter_event);
        recyclerView.setOnItemClickListener(new DrawerItemClickListener(this,mainClassViewModel));

    }

    public void onGetFavoursArray(DataModel.Favour[] eventList)
    {
        mapFavours = eventList;
        System.out.println("EVENT LIST ======="+ Arrays.toString(eventList));

        //Si el adaptador es nul, vol dir que no hi havien favors previament
        // if (adapter_event == null)
        //{
        //Posem els favors que ens retorna la crida i ja esta
        //adapter_event.clear();
        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.favours_list, eventList, this);

        //adapter_event = new DrawerItemCustomAdapter(this, R.layout.favours_list, eventList, this);
        //}
        //En cas contrari, vol dir que hem carregat més favors
        // else
        //{
        //Concateno l'array de favors que ja teniem amb els que ens retorna la crida a al API
        //   DataModel.Favour[] concatenateArray = (DataModel.Favour[]) ArrayUtils.appendToArray(adapter_event.getData(),eventList);
        //   adapter_event.setData(concatenateArray);
        // }
        recyclerView.invalidate();
        recyclerView.setAdapter(adapter);
        recyclerView.setOnItemClickListener(new DrawerItemClickListener(this,mainClassViewModel));

    }

    private void getAllActivityData()
    {
        drawerLayout = findViewById(R.id.drawer_layout);
        recyclerView = findViewById(R.id.rv_recycler_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        llista = findViewById(R.id.left_drawer);

    }

    private void OnClickTab(boolean list)
    {

            recyclerView.setVisibility(View.VISIBLE);
            uploadFavour.setVisibility(View.VISIBLE);


    }







    private void setScrollListener()
    {
        recyclerView.setOnScrollListener(new AbsListView.OnScrollListener()
        {
            private int mLastFirstVisibleItem;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState)
            {

            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                if (mLastFirstVisibleItem == 0)
                {
                    //uploadFavour.animate().alpha(1.0f).setDuration(500).start();
                   // uploadFavour.setClickable(true);
                }
                else
                {
                    //uploadFavour.animate().alpha(0.0f).setDuration(500).start();
                   //uploadFavour.setClickable(false);
                }
                mLastFirstVisibleItem=firstVisibleItem;
            }
        });
    }






    public void LoadMore(int listnumber)
    {
       // mainClassViewModel.getFavours(listnumber);
    }

    public void setSpinnerVisible(Boolean visible) {
        if (visible){
            filterSpinnerCategory.setVisibility(View.VISIBLE);
        } else{
            filterSpinnerCategory.setVisibility(View.GONE);
        }


    }






    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

