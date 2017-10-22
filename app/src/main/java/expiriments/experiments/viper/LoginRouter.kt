package expiriments.experiments.viper

import android.app.Activity
import android.content.Intent
import expiriments.experiments.Main
import expiriments.experiments.viper.model.User

/**
 * Created by oussama on 10/18/2017.
 */
class LoginRouter(var activity: Activity?) : LoginContracts.Router {

    override fun unregister() {
        activity = null
    }

    override fun presentHomeScreen(user: User) {
        val intent = Intent(activity, Main::class.java)
//        intent.putExtra(Constants.IntentExtras.USER, user)
        activity?.startActivity(intent)
    }
}