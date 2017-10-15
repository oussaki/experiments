package expiriments.experiments

import android.app.ActivityManager
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.facebook.stetho.okhttp3.StethoInterceptor
import expiriments.experiments.caching.ImageCache
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.*

class KotlinActivity : AppCompatActivity() {
    val v by lazy {
        print("Lazy boy")
    }
    lateinit var btn_coroutines: Button
    lateinit var btn_threads: Button
    lateinit var btn_all: Button
    lateinit var txt_log: TextView
    fun TextView.CounterDown() {
        var v: TextView = this
        launch(UI) {
            // launch coroutine in UI context
            for (i in 10 downTo 1) { // countdown from 10 to 1
                v.text = "Countdown $i ..." // update text
                delay(1000) // wait half a second
            }
            v.text = "Done!"
        }
    }

    var ok: OkHttpClient? = null;


    fun init() {
        ok = OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()

        btn_coroutines = findViewById(R.id.btn_coroutines) as Button
        btn_threads = findViewById(R.id.btn_threads) as Button
        btn_all = findViewById(R.id.btn_all) as Button
        txt_log = findViewById(R.id.txt_log) as TextView
    }

    suspend fun coroutine_network_call(): Deferred<ByteArray?> {

        var async = async(CommonPool) {
            ok?.newCall(Request.Builder().url("http://reactivex.io/assets/Rx_Logo_S.png")
                    .build())?.execute()?.body()?.bytes()
        }
        return async
    }


    fun threads_network_call() {
        var start: Long = Calendar.getInstance().timeInMillis
        Thread(Runnable {
            var b: ByteArray? = ok?.newCall(Request.Builder().url("http://reactivex.io/assets/Rx_Logo_S.png")
                    .build())?.execute()?.body()?.bytes()
            runOnUiThread {
                var end = Calendar.getInstance().timeInMillis
                txt_log.append("\n Threads size of bytes is : ${b?.size} , Takes: ${end - start} ms")
            }
        }).start()
    }

    lateinit var cache: ImageCache
    fun init_cache() {
        val memClass = (getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager).memoryClass
        cache = ImageCache(memClass * 1024 * 1024 / 8)
    }

    fun cache_bitmap(k: String, bitmap: Bitmap) {
        if (cache == null) {
            init_cache()
        }
        cache.put(k, bitmap)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        init()

        btn_all.setOnClickListener({ _ ->
            btn_threads.performClick()
            btn_coroutines.performClick()
        })
        btn_threads.setOnClickListener({ _ ->
            txt_log.CounterDown()
            threads_network_call()
        })

        btn_coroutines.setOnClickListener({ _ ->
            var start: Long = Calendar.getInstance().timeInMillis
            launch(UI) {
                Log.i("kotlin", "before coroutine")
                txt_log.append("Coroutine size of bytes is :" +
                        "${coroutine_network_call().await()?.size.also {
                            txt_log.append("\n takes : ${Calendar.getInstance().timeInMillis - start} ms , ")
                        }}")
                Log.i("kotlin", "after coroutine")
            }
        })

    }
}
