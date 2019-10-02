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
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
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


        // Instantiate the RequestQueue.
        String tag_json_obj ="json_obj_req";
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://coms-309-sb-7.misc.iastate.edu:8080/api/user";

        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
                    @Override
                public void onResponse(JSONObject response){
                        // Display the first 500 characters of the response string.
                        textView.setText("Response is: "+ response.toString());
                    }
                },new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
        queue.add(jsonObjReq);
    }

    public void openLogin()
    {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }


//    // Tag used to cancel the request
//    String tag_json_obj ="json_obj_req";
//    String url ="http://coms-309-sb-7.misc.iastate.edu:8080/api/user";
//
//    JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//        @Override
//                public void onResponse(JSONObject response){
//            Log.d(TAG, response.toString());
//        }
//    },new Response.ErrorListener() {
//        @Override
//                public void onErrorResponse(VolleyError error) {
//            VolleyLog.d(TAG,"Error: "+ error.getMessage());
//
//        }
//    });
//      //  AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);


//    // Instantiate the RequestQueue.
//    RequestQueue queue = Volley.newRequestQueue(this);
//    String url ="http://coms-309-sb-7.misc.iastate.edu:8080/api/user";
//
//    // Request a string response from the provided URL.
//    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//            new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    // Display the first 500 characters of the response string.
//                    textView.setText("Response is: "+ response.substring(0,500));
//                }
//            }, new Response.ErrorListener() {
//        @Override
//        public void onErrorResponse(VolleyError error) {
//            textView.setText("That didn't work!");
//        }
//    });

}
