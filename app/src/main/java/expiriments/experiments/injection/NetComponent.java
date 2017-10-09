package expiriments.experiments.injection;

import javax.inject.Singleton;

import dagger.Component;
import expiriments.experiments.Main;

/**
 * Created by salih on 8/7/2017.
 */

@Singleton
@Component(modules={ AppModule.class,  NetModule.class })
public interface NetComponent {
     void inject(Main activity);
    // void inject(MyFragment fragment);
    // void inject(MyService service);
}