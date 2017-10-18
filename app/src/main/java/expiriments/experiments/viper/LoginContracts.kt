package expiriments.experiments.viper

import expiriments.experiments.viper.model.User

/**
 * Created by oussama on 10/18/2017.
 */
interface LoginContracts {
    interface View {
        fun showError(message: String)
    }

    interface Presenter {
        fun onDestroy()
        fun onLoginButtonPressed(username: String, password: String)
    }

    interface Interactor {
        fun unregister()
        fun login(username: String, password: String)
    }

    interface InteractorOutput {
        fun onLoginSuccess(user: User)
        fun onLoginError(message: String)
    }

    interface Router {
        fun unregister()
        fun presentHomeScreen(user: User)
    }
}