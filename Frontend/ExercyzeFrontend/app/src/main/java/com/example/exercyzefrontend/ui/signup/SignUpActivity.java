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

public class SignUpActivity extends AppCompatActivity implements Validator.ValidationListener  {

    private String TAG = SignUpActivity.class.getSimpleName();

    private String tag_json_obj = "jobj_req";

    private Button signUpButton;

    @NotEmpty
    @Length(min = 2, max = 30)
    private EditText firstNameET;

    @NotEmpty
    @Length(min = 2, max = 30)
    private EditText lastNameET;

    @NotEmpty
    @Length(min = 5, max = 15)
    private EditText userNameET;

    @NotEmpty
    @Password(min = 8, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE)
    private EditText passwordET;

    @NotEmpty
    @Length(min = 2, max = 2)
    private EditText heightET;

    @NotEmpty
    @Length(min = 2, max = 3)
    private EditText weightET;

    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initView();

        /*
         signUpButton = findViewById(R.id.signUpCompletBtn);
         firstNameET = findViewById(R.id.firstNameSignUpEditText);
         lastNameET = findViewById(R.id.lastNameSignUpEditText);
         userNameET = findViewById(R.id.userNameSignUpEditText);
         passwordET = findViewById(R.id.passwordSignUpEditText);
         heightET = findViewById(R.id.heightSignUpEditText);
         weightET = findViewById(R.id.weightSignUpEditText);

        signUpButton.setOnClickListener(this);
        /*
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = firstNameET.getText().toString();
                String lasttName = lastNameET.getText().toString();
                String userName = userNameET.getText().toString();
                String password = passwordET.getText().toString();
                double height = Double.parseDouble(heightET.getText().toString());
                double weight = Double.parseDouble(weightET.getText().toString());

                postUserModel(firstName, lasttName, userName, password, height, weight);
                Intent profileActivity = new Intent(getApplicationContext(), UserProfileActivity.class);

                startActivity(profileActivity);
            }
        });*/
    }

    private void initView() {
        signUpButton = findViewById(R.id.signUpCompletBtn);
        firstNameET = findViewById(R.id.firstNameSignUpEditText);
        lastNameET = findViewById(R.id.lastNameSignUpEditText);
        userNameET = findViewById(R.id.userNameSignUpEditText);
        passwordET = findViewById(R.id.passwordSignUpEditText);
        heightET = findViewById(R.id.heightSignUpEditText);
        heightET.setText("0.0");
        weightET = findViewById(R.id.weightSignUpEditText);
        weightET.setText("0.0");

        validator =  new Validator(this);
        validator.setValidationListener(this);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean empty = false;

                String firstName = firstNameET.getText().toString();
                String lasttName = lastNameET.getText().toString();
                String userName = userNameET.getText().toString();
                String password = passwordET.getText().toString();
                double height = Double.parseDouble(heightET.getText().toString());
                double weight = Double.parseDouble(weightET.getText().toString());

                if (firstName.isEmpty()) {
                    firstNameET.setError("Field is empty");
                    empty = true;
                }
                if (lasttName.isEmpty()) {
                    lastNameET.setError("Field is empty");
                }
                if (userName.isEmpty()) {
                    userNameET.setError("Field is empty");
                }
                if (password.isEmpty()) {
                    passwordET.setError("Field is empty");
                }
                if (height == 0.0) {
                    heightET.setError("Field is empty");
                }
                if (weight == 0.0) {
                    weightET.setError("Field is empty");
                }
                validator.validate();

                //postUserModel(firstName, lasttName, userName, password, height, weight);
                //Intent profileActivity = new Intent(getApplicationContext(), UserProfileActivity.class);

                //startActivity(profileActivity);
            }
        });
    }

//    @Override
//    public void onValidationSucceeded() {
//        Toast.makeText(this, "Task Succeded", Toast.LENGTH_SHORT).show();
//    }
    /*
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signUpCompletBtn) {
            String firstName = firstNameET.getText().toString();
            String lasttName = lastNameET.getText().toString();
            String userName = userNameET.getText().toString();
            String password = passwordET.getText().toString();
            double height = Double.parseDouble(heightET.getText().toString());
            double weight = Double.parseDouble(weightET.getText().toString());

            postUserModel(firstName, lasttName, userName, password, height, weight);
            Intent profileActivity = new Intent(getApplicationContext(), UserProfileActivity.class);

            startActivity(profileActivity);
        }

            /*
        switch(v.getId()) {
            case R.id.signUpCompletBtn:

                break;
        }
    }*/

    private void postUserModel(String firstName, String lastName, String userName, String password, double height, double weight){
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
                + "Error cause: "  + error.getCause());

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
        Toast.makeText(this, "We got it right!", Toast.LENGTH_SHORT).show();
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
