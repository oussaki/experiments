package expiriments.experiments.viper

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by oussama on 10/18/2017.
 */
class LoginInteractor(var output: LoginContracts.InteractorOutput?) : LoginContracts.Interactor {

    var loginManager = LoginApiManager()

    override fun unregister() {
        output = null
    }

    override fun login(username: String, password: String) {

        loginManager.login(username, password)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    //does something with the user, like saving it or the token
                    output?.onLoginSuccess(it)
                },
                        { output?.onLoginError(it.message ?: "Error!") })
    }
}