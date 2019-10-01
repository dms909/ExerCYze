package com.example.exercyze;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.exercyze.app.AppController;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity  {

    private Button bLogout;
    private EditText etName, etAge, etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etUsername = (EditText) findViewById(R.id.etUsername);

        bLogout = (Button) findViewById(R.id.bLogout);

        bLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bLogout:
                        openLogin();
                        break;
                }
            }
        });
    }

    public void openLogin()
    {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }


    // Tag used to cancel the request
    String tag_json_obj ="json_obj_req";
    String url ="https://coms-309-sb-7.misc.iastate.edu/api/users";

    JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
        @Override
                public void onResponse(JSONObject response){
            Log.d(TAG, response.toString());
        }
    },new Response.ErrorListener() {
        @Override
                public void onErrorResponse(VolleyError error) {
            VolleyLog.d(TAG,"Error: "+ error.getMessage());

        }
    });
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);


}
