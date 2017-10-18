package expiriments.experiments.viper

import android.app.Activity
import expiriments.experiments.viper.model.User

/**
 * Created by oussama on 10/18/2017.
 */
class LoginPresenter(var view: LoginContracts.View?) : LoginContracts.Presenter, LoginContracts.InteractorOutput {
    var interactor: LoginContracts.Interactor? = LoginInteractor(this)
    var router: LoginContracts.Router? = LoginRouter(view as? Activity)

    override fun onDestroy() {
        view = null
        interactor?.unregister()
        interactor = null
        router?.unregister()
        router = null
    }

    override fun onLoginButtonPressed(username: String, password: String) {
        interactor?.login(username, password)
    }

    override fun onLoginSuccess(user: User) {
//        router?.goToNextScreen(user)
        router?.presentHomeScreen(user)
    }

    override fun onLoginError(message: String) {
        view?.showError(message)
    }
}