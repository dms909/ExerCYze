package com.example.exercyzefrontend.ui.signup;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.exercyzefrontend.R;
import com.example.exercyzefrontend.app.AppController;
import com.example.exercyzefrontend.ui.login.LoginActivity;
import com.example.exercyzefrontend.utils.Const;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private String TAG = SignUpActivity.class.getSimpleName();

    private String tag_json_obj = "jobj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final Button signUpButton = findViewById(R.id.signUpCompletBtn);
        final EditText firstNameET = findViewById(R.id.firstNameSignUpEditText);
        final EditText lastNameET = findViewById(R.id.lastNameSignUpEditText);
        final EditText userNameET = findViewById(R.id.userNameSignUpEditText);
        final EditText passwordET = findViewById(R.id.passwordSignUpEditText);
        final EditText heightET = findViewById(R.id.heightSignUpEditText);
        final EditText weightET = findViewById(R.id.weightSignUpEditText);


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
                Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);

                startActivity(loginActivity);
            }
        });
    }

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
}
