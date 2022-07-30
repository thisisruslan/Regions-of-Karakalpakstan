package uz.gita.regionsofkarakalpakhstan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import uz.gita.regionsofkarakalpakhstan.controller.Controller;
import uz.gita.regionsofkarakalpakhstan.data.RegionData;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Controller controller;
    private ArrayList<LinearLayoutCompat> linearList;
    private DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        loadView();
        loadDataToView();

        drawerLayout = findViewById(R.id.nav_drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        findViewById(R.id.menu_btn).setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });

        findViewById(R.id.main_location).setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.app.goo.gl/maCugiYYbacxxoAL9"));
            this.startActivity(intent);
        });
    }

    private void loadView() {
        linearList = new ArrayList<>();
        LinearLayoutCompat param = findViewById(R.id.param);
        for (int i = 0, j = 0; i < param.getChildCount(); i++, j++) {
            linearList.add((LinearLayoutCompat) param.getChildAt(i));
            linearList.get(j).setTag(Integer.valueOf(j));
            linearList.get(j).setOnClickListener(v -> {
                LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) v;
                TextView tv = (TextView) linearLayoutCompat.getChildAt(1);

                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                intent.putExtra("id", (Integer) v.getTag());
                intent.putExtra("title", String.valueOf(tv.getText()));
                intent.putExtra("location", String.valueOf(controller.getDataByPos((Integer) v.getTag()).getLocation()));

                startActivity(intent);
            });
        }
    }

    private void loadDataToView() {
        controller = Controller.getInstance();
        controller.loadData();
        ArrayList<RegionData> laptopList = controller.getList();
        for (int i = 0; i < laptopList.size(); i++) {
            RegionData data = laptopList.get(i);
            ImageView image = (ImageView) linearList.get(i).getChildAt(0);
            image.setImageResource(data.getLogoID());
            TextView textLaptopName = (TextView) linearList.get(i).getChildAt(1);
            textLaptopName.setText(data.getName());
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        controller.getList().clear();
    }



    //TODO
    private void rateApp() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
    }

    private void shareApp() {
        Intent intent = new Intent((Intent.ACTION_SEND));
        intent.setType("text/plain");
        String text = "Регионы Каракалпакстана  \n\nRuslan Jumatov\n"
                + "https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName();
        intent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(intent, "Поделиться:"));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share: {
                shareApp();
                break;
            }
            case R.id.about: {
                drawerLayout.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.exit: {
                finish();
                break;
            }

        }
        return true;
    }
}

