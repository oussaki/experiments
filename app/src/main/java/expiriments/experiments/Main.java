package expiriments.experiments;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.OkHttpClient;


/**
 * Created by oussama on 8/7/2017.
 */

//@Tarakha(id = 1, name = "oussaki")
public class Main extends AppCompatActivity {
    String TAG = "Main";
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

        int memClass = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        Log.i(TAG,"memClass="+memClass);
        startActivity(new Intent(this, KotlinActivity.class));
    }


}
