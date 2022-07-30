package uz.gita.regionsofkarakalpakhstan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class AboutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        findViewById(R.id.buttonClose).setOnClickListener(v -> {
            finish();
        });

        //instagram link
        findViewById(R.id.instagram_tv).setOnClickListener(v->{
            Uri uri = Uri.parse("http://instagram.com/_u/ruslan_jumatov");
            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

            likeIng.setPackage("com.instagram.android");

            try {
                startActivity(likeIng);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://instagram.com/ruslan_jumatov")));
            }
        });


        //telegram link
        findViewById(R.id.telegram_tv).setOnClickListener(v->{
            Uri uri = Uri.parse("http://t.me/ruslan_jumatov");
            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

            likeIng.setPackage("com.telegram.android");

            try {
                startActivity(likeIng);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://t.me/ruslan_jumatov")));
            }
        });


    }
}