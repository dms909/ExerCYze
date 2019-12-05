package com.example.exercyzefrontend.ui.signup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.exercyzefrontend.R;
import com.example.exercyzefrontend.app.AppController;
import com.example.exercyzefrontend.ui.login.LoginActivity;
import com.example.exercyzefrontend.ui.userprofile.UserProfileActivity;
import com.example.exercyzefrontend.utils.Const;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.DecimalMin;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.Min;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Pattern;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SignUpActivity extends AppCompatActivity implements Validator.ValidationListener {

    private String TAG = SignUpActivity.class.getSimpleName();
    private Button signUpButton;
    private Button returnToLoginButton;

    @NotEmpty(trim = true)
    @Length(min = 2, max = 30, message = "Invalid input")
    private EditText firstNameET;

    @NotEmpty(trim = true)
    @Length(min = 2, max = 30, message = "Invalid input")
    private EditText lastNameET;

    @NotEmpty(trim = true)
    @Length(min = 5, max = 15, message = "Invalid input")
    private EditText userNameET;

    @NotEmpty(trim = true)
    @Password(min = 8, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE, message = "Invalid input")
    private EditText passwordET;

    @NotEmpty
    @Length(min = 2, max = 4)
    private EditText heightET;

    @NotEmpty
    @Length(min = 2, max = 5)
    private EditText weightET;

    private Validator validator;

    private boolean validated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
    }

    private void initView() {
        validated = false;
        signUpButton = findViewById(R.id.signUpCompletBtn);
        returnToLoginButton = findViewById(R.id.returnToLoginBtn);
        firstNameET = findViewById(R.id.firstNameSignUpEditText);
        lastNameET = findViewById(R.id.lastNameSignUpEditText);
        userNameET = findViewById(R.id.userNameSignUpEditText);
        passwordET = findViewById(R.id.passwordSignUpEditText);
        heightET = findViewById(R.id.heightSignUpEditText); //preset to 0.0
        heightET.setText("0.0");
        weightET = findViewById(R.id.weightSignUpEditText); //preset to 0.0
        weightET.setText("0.0");

        validator = new Validator(this);
        validator.setValidationListener(this);

        returnToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);

                startActivity(loginActivity);
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String firstName = firstNameET.getText().toString();
                String lasttName = lastNameET.getText().toString();
                String userName = userNameET.getText().toString();
                String password = passwordET.getText().toString();
                double height = Double.parseDouble(heightET.getText().toString());
                double weight = Double.parseDouble(weightET.getText().toString());


                validator.validate();
                if (height == 0.0) {
                    validated = false;
                    heightET.setError("Invalid height");
                }
                if (weight == 0.0) {
                    validated = false;
                    weightET.setError("Invalid weight");
                }
                if (validated) {

                    Toast.makeText(getApplicationContext(), "Profile successfully created!", Toast.LENGTH_SHORT).show();
                    postUserModel(firstName, lasttName, userName, password, height, weight);
                    Intent loginActivity = new Intent(SignUpActivity.this, LoginActivity.class);
                    loginActivity.putExtra("user_Name", userName);
                    loginActivity.putExtra("password", password);
                    startActivity(loginActivity);
                }
            }
        });
    }

    private void postUserModel(String firstName, String lastName, String userName, String password, double height, double weight) {
        final Map<String, String> params = new HashMap<String, String>();
        params.put("firstName", firstName);
        params.put("lastName", lastName);
        params.put("userName", userName);
        params.put("password", password);
        params.put("weight", weight + "");
        params.put("height", height + "");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Const.URL_USER_OBJECT, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage()
                        + "Error cause: " + error.getCause());

            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

        };

        AppController.getInstance().addToRequestQueue(jsonObjReq);

    }

    @Override
    public void onValidationSucceeded() {
        Toast.makeText(this, "Profile successfully completed", Toast.LENGTH_SHORT).show();
        validated = true;
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            // Display error messages
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
