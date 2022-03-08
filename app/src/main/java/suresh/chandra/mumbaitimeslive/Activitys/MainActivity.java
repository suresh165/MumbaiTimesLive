package suresh.chandra.mumbaitimeslive.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import suresh.chandra.mumbaitimeslive.API_Interface;
import suresh.chandra.mumbaitimeslive.Adapter_recyclerview;
import suresh.chandra.mumbaitimeslive.R;
import suresh.chandra.mumbaitimeslive.Retrofit_covertor;
import suresh.chandra.mumbaitimeslive.WP_post;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar mtoolbar;
    //canect api key
    API_Interface apiInterface;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //toolbar
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigation_view);
        mtoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu);

        //menu items click
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        finish();
                        return true;
                    case R.id.nav1_exit:
                        Toast.makeText(MainActivity.this, "Exit", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return true;
            }
        });
        API_Integrate();
    }

    //drawerLayout item click
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
    // API_Integrate in recyclerview
    private void API_Integrate() {
        recyclerView = findViewById(R.id.recyclerView);
        apiInterface = Retrofit_covertor.getRetrofit().create(API_Interface.class);
        apiInterface.getlist().enqueue(new Callback<List<WP_post>>() {
            @Override
            public void onResponse(Call<List<WP_post>> call, Response<List<WP_post>> response) {
                for (int i=0;i<response.body().size();i++){
                    response.body().get(i).getTitle().getRendered();
                    List<WP_post> getlistdata = response.body();
                    //Log.d("mylog",getlistdata.toString());
                    Adapter_recyclerview adapter_recyclerview = new Adapter_recyclerview(getlistdata,MainActivity.this);
                    RecyclerView.LayoutManager linearLayoutManager = new GridLayoutManager(MainActivity.this,2);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(adapter_recyclerview);
                    recyclerView.setLayoutManager(linearLayoutManager);
                }
            }

            @Override
            public void onFailure(Call<List<WP_post>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "List is empty", Toast.LENGTH_SHORT).show();
            }
        });
    }

}