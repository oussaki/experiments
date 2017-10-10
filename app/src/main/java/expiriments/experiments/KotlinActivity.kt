package expiriments.experiments

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.Tarakha
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.*

@Tarakha(id = 1, name = "something")
class KotlinActivity : AppCompatActivity() {
    lateinit var btn_coroutines: Button
    lateinit var btn_threads: Button
    lateinit var btn_all: Button
    lateinit var txt_log: TextView

    var ok: OkHttpClient? = null;

    fun init() {

        ok = OkHttpClient.Builder().build();
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
                var end: Long = Calendar.getInstance().timeInMillis
                txt_log.append("\n Threads size of bytes is : ${b?.size} , Takes: ${end - start} ms")
            }
        }).start()
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
