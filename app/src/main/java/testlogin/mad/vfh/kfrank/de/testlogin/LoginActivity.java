package testlogin.mad.vfh.kfrank.de.testlogin;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {

    // REGEX
    private static final String MAIL_REGEX = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}";
    private static final String PASSWORD_REGEX = "\\d{6}";

    // LOGIN DATEN
    private Map<String, String> loginData;

    // FLAGS
    private boolean validPassword = false;
    private TextView wrongMailView;

    // VIEWS
    private EditText emailField;
    private boolean validMail = false;
    private EditText passwordField;
    private TextView wrongPasswordView;
    private Button signinButton;
    private TextView loginFailedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // standard gedöns
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // init login data
        loginData = new HashMap<>();
        loginData.put("e@mail.de", "123456");

        // determine views
        emailField = (EditText) findViewById(R.id.email);
        wrongMailView = (TextView) findViewById(R.id.wrongMailMessage);
        passwordField = (EditText) findViewById(R.id.password);
        wrongPasswordView = (TextView) findViewById(R.id.wrongPasswordMessage);
        signinButton = (Button) findViewById(R.id.email_sign_in_button);
        loginFailedView = (TextView) findViewById(R.id.loginFailedMessage);

        // Listener um zur ListView zu kommen
        passwordField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    asyncCheckLogin();
                    return true;
                }
                return false;
            }
        });

        signinButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncCheckLogin();
            }
        });

        // Handler zur Evaluation des Inputs
        emailField.addTextChangedListener(new AbstractTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loginFailedView.setVisibility(View.INVISIBLE);
                validateEmail();
                updateSigninButton();
            }
        });

        passwordField.addTextChangedListener(new AbstractTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loginFailedView.setVisibility(View.INVISIBLE);
                validatePassword();
                updateSigninButton();
            }
        });
    }

    private void validateEmail() {
        String text = emailField.getText().toString();
        validMail = text.matches(MAIL_REGEX);
        wrongMailView.setVisibility(text.isEmpty() || validMail ? View.INVISIBLE : View.VISIBLE);
    }

    private void validatePassword() {
        String text = passwordField.getText().toString();
        validPassword = text.matches(PASSWORD_REGEX);
        wrongPasswordView.setVisibility(text.isEmpty() || validPassword ? View.INVISIBLE : View.VISIBLE);
    }

    private void updateSigninButton() {
        signinButton.setEnabled(validMail && validPassword);
    }

    private void asyncCheckLogin() {
        new AsyncTask<Void, Void, Void>() {

            private ProgressDialog dialog = null;

            @Override
            protected void onPreExecute() {
                dialog = ProgressDialog.show(LoginActivity.this, "Bitten warten Sie...", "Nutzerdaten werden evaluiert.");
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(3000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            checkLogin();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                dialog.cancel();
            }
        }.execute();
    }

    private void checkLogin() {
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        if(loginData.containsKey(email) && password.equals(loginData.get(email))) {
            goToListView();
        } else {
            loginFailedView.setVisibility(View.VISIBLE);
        }
    }

    private void goToListView() {
        startActivity(new Intent(LoginActivity.this, ListViewActivity.class));
    }
}

