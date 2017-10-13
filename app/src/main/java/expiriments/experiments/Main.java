package expiriments.experiments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.Tarakha;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.OkHttpClient;


/**
 * Created by salih on 8/7/2017.
 */

//@Tarakha(id = 1, name = "oussaki")
public class Main extends AppCompatActivity {

    @Inject
    @Named("cached")
    OkHttpClient mOkHttpClient;
    @Inject
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MyApp) getApplication()).getNetComponent().inject(this);


        startActivity(new Intent(this, KotlinActivity.class));
    }


}
