package expiriments.experiments;

import android.app.Application;

import com.facebook.stetho.Stetho;

import expiriments.experiments.injection.AppModule;
import expiriments.experiments.injection.DaggerNetComponent;
import expiriments.experiments.injection.NetComponent;
import expiriments.experiments.injection.NetModule;

/**
 * Created by oussama on 8/6/2017.
 */

public class MyApp  extends Application{
    private NetComponent mNetComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .netModule(new NetModule("https://api.github.com"))
                .build();

        Stetho.initializeWithDefaults(this);

//        mNetComponent = DaggerNetComponent
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
