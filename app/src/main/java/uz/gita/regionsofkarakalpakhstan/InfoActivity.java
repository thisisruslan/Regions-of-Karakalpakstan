package uz.gita.regionsofkarakalpakhstan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import uz.gita.regionsofkarakalpakhstan.controller.Controller;
import uz.gita.regionsofkarakalpakhstan.data.RegionData;

public class InfoActivity extends AppCompatActivity {
    private Controller controller;
    private ImageView image;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        controller = Controller.getInstance();

        Bundle bundle = getIntent().getExtras();
        int pos = bundle.getInt("id");
        String title = bundle.getString("title");
        String location = bundle.getString("location");


        TextView tv = findViewById(R.id.title_info);
        tv.setText(title);
        RegionData laptopData = controller.getDataByPos(pos);
        image = findViewById(R.id.image);
        description = findViewById(R.id.textDescription);
        image.setImageResource(laptopData.getImageID());
        description.setText(laptopData.getDescription());

        findViewById(R.id.buttonClose).setOnClickListener(v -> {
            finish();
        });

        findViewById(R.id.location_id).setOnClickListener(v -> {
            shareApp(location);
        });


    }

    private void shareApp(String location) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(location));
        this.startActivity(intent);
    }

}