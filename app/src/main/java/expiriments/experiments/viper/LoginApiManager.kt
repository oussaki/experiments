package expiriments.experiments.viper

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by oussama on 10/18/2017.
 */
interface NetworkApi {
    @GET("users/login/{user_name}/{pass}")
    fun login(@Path("user_name") user_name: String, @Path("pass") pass: String): Observable<String>
}


class LoginApiManager {
    lateinit var retrofit: Retrofit;
    var service = retrofit.create<NetworkApi>(NetworkApi::class.java!!)

    init {
        var retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

    }

    fun login(user_name: String, pass: String): Observable<String> {
        return service.login(user_name, pass)
    }

}