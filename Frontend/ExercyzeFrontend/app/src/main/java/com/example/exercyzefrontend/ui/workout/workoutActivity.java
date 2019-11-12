package com.example.exercyzefrontend.ui.workout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.exercyzefrontend.R;
import com.example.exercyzefrontend.app.AppController;
import com.example.exercyzefrontend.ui.login.LoginActivity;
import com.example.exercyzefrontend.ui.workoutroutine.workoutroutineActivity;
import com.example.exercyzefrontend.utils.Const;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.example.exercyzefrontend.R;

public class workoutActivity extends AppCompatActivity {


    private String TAG = workoutActivity.class.getSimpleName();
    private String tag_json_obj = "jobj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        final Button saveworkout = (Button) findViewById(R.id.saveworkout);
        final Button nosaveworkout = (Button) findViewById(R.id.nosaveworkout);
        final TextView workoutname = (TextView) findViewById(R.id.workoutname);
        final TextView creator = (TextView) findViewById(R.id.creator);
        final EditText workoutinput = (EditText) findViewById(R.id.workoutinput);
        final EditText creatorname = (EditText) findViewById(R.id.creatorname);

        saveworkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String workout = workoutinput.getText().toString();
                String creator = creatorname.getText().toString();

                postUserModel(workout, creator);
                Intent routineActivty = new Intent(getApplicationContext(), workoutroutineActivity.class);

                startActivity(routineActivty);
            }
        });
        nosaveworkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent routineActivty = new Intent(getApplicationContext(), workoutroutineActivity.class);
                startActivity(routineActivty);
            }
        });
    }

    private void postUserModel(String workout, String creator){
        final Map<String, String> params = new HashMap<String, String>();
        params.put("workoutRoutineName", workout);
        params.put("workoutRoutineCreator", creator);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Const.URL_WORKOUTROUTINE_OBJECT, new JSONObject(params),
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
