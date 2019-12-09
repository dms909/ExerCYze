package com.example.exercyzefrontend.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.exercyzefrontend.R;
import com.example.exercyzefrontend.app.AppController;
import com.example.exercyzefrontend.data.LoginDataSource;
import com.example.exercyzefrontend.data.model.LoggedInUser;
import com.example.exercyzefrontend.ui.signup.SignUpActivity;
import com.example.exercyzefrontend.ui.userprofile.UserProfileActivity;
import com.example.exercyzefrontend.utils.Const;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * class that handles with the login page.
 */
public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    private String TAG = LoginActivity.class.getSimpleName();

    private String userNameStr = "";

    private String passwordStr = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final Button registerButton = findViewById(R.id.signup);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        Intent loginIntent = getIntent();
        userNameStr = loginIntent.getStringExtra("user_Name");
        passwordStr = loginIntent.getStringExtra("password");

        if ((userNameStr != null) && (passwordStr != null)) {
            usernameEditText.setText(userNameStr);
            passwordEditText.setText(passwordStr);
        }

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    userNameStr = usernameEditText.getText().toString();
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        // watches for change in text fields, if not touched then is ingored, after the text field is changes then login view model will get data of username and passwords
        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        // sending request to the url server on username and passwords to authenticate if user is in database to login in
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                final Map<String, String> params = new HashMap<String, String>();
                String url = Const.URL_USER_AUTHENTICATION + "?userName=" + usernameEditText.getText().toString();
                params.put("password", passwordEditText.getText().toString());
                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                        url, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d(TAG, response.toString());
                                try {
                                    if(response.getBoolean("authenticated")){
                                        loginViewModel.login(usernameEditText.getText().toString(), passwordEditText.getText().toString());
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    // outputting different error messages is request did not work
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof TimeoutError) {
                            Log.i(TAG, "onErrorResponse: timeout error" );
                        } else if (error instanceof ServerError) {
                            Log.i(TAG, "onErrorResponse: server error");
                        } else if (error instanceof NetworkError) {
                            //toast or log your error
                        } else if (error instanceof ParseError) {
                            Log.i(TAG, "onErrorResponse: parse error" );
                            error.printStackTrace();
                        } else {
                            Log.i(TAG, "onErrorResponse: Something went wrong ");
                        }

                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }
                };
                AppController.getInstance().addToRequestQueue(jsonObjReq);

            }
        });

        // if user clicks register button on login page then moving to register page event
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpActivity = new Intent(getApplicationContext(), SignUpActivity.class);

                startActivity(signUpActivity);
            }
        });
    }

    // method that handles with when successful login in to profile page will greet with user's username
    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();

        // TODO : initiate successful logged in experience

        Intent profileActivity = new Intent(LoginActivity.this, UserProfileActivity.class);
        profileActivity.putExtra("user_name", userNameStr);
        startActivity(profileActivity);

        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    // method to show if login progress was a fail to the user
    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
