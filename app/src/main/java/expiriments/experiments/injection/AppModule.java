package expiriments.experiments.injection;
import android.app.Application;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by oussama on 8/6/2017.
 */

@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }
}
