package com.example.exercyze;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.exercyze.app.AppController;



public class MainActivity<stringRequest> extends AppCompatActivity  {

    private Button bLogout;
    private EditText etName, etAge, etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etUsername = (EditText) findViewById(R.id.etUsername);
        final TextView textView = (TextView) findViewById(R.id.text);
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

        String tag_json_obj ="json_obj_req";
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://coms-309-sb-7.misc.iastate.edu:8080/api/user";

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                    @Override
                public void onResponse(JSONArray response){
                        textView.setText("Response is: "+ response);
                    }
                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    textView.setText("That didn't work!");
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);


    }

    public void openLogin()
    {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

}
