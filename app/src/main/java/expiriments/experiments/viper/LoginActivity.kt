package expiriments.experiments.viper

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

/**
 * Created by oussama on 10/18/2017.
 */
class LoginActivity : Activity(), LoginContracts.View {

    lateinit var loginButton: Button;
    lateinit var usernameEditText: EditText;
    lateinit var passwordEditText: EditText;
    var presenter: LoginContracts.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = LoginPresenter(this)
        loginButton.setOnClickListener { onLoginButtonClicked() }
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

    private fun onLoginButtonClicked() {
        presenter?.onLoginButtonPressed(usernameEditText.text.toString(), passwordEditText.text.toString())
    }

    override fun showError(message: String) {
        //shows the error on a dialog
    }
}