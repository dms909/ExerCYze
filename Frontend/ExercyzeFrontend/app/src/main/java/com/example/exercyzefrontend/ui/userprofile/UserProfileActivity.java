package com.example.exercyzefrontend.ui.userprofile;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.exercyzefrontend.R;
import com.example.exercyzefrontend.ui.progress.UserProgressActivity;

import com.example.exercyzefrontend.ui.workout.workoutActivity;
import com.example.exercyzefrontend.ui.workoutroutine.workoutroutineActivity;

import com.example.exercyzefrontend.ui.webChat.webChatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * class that handles with user profile page ofo the app
 */
public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private ConstraintLayout userProfileCL;
    private Button workoutBtn, progressBtn, socialBtn;
    private FloatingActionButton editFAB;
    private TextView userNameTV, userRealNameTV, userHeightTV, userWeightTV;
    private EditText userRealNameET, userHeightET, userWeightET;
    private Button editSaveBtn;
    private String finalresult;
    private String userNameStr = "";
    private boolean editMode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        editMode = false;

        Intent profileIntent = getIntent();
        userNameStr = profileIntent.getStringExtra("user_name");
        //variable for constraint layout
        //used when entering edit mode
        userProfileCL = findViewById(R.id.userProfileCL);

        userNameTV = (TextView) findViewById(R.id.user_Name);
        userRealNameTV = (TextView) findViewById(R.id.userRealName);
        userHeightTV = (TextView) findViewById(R.id.userHeight);
        userWeightTV = (TextView) findViewById(R.id.userWeight);

        userRealNameET = (EditText) findViewById(R.id.userRealNameET);
        userHeightET = (EditText) findViewById(R.id.userHeightET);
        userWeightET = (EditText) findViewById(R.id.userWeightET);

        //hiding edit text while not in user edit mode
        userRealNameET.setVisibility(View.GONE);
        userHeightET.setVisibility(View.GONE);
        userWeightET.setVisibility(View.GONE);

        workoutBtn = (Button) findViewById(R.id.workoutBtn);
        progressBtn = (Button) findViewById(R.id.progressBtn);
        socialBtn = (Button) findViewById(R.id.socialBtn);

        editFAB = (FloatingActionButton) findViewById(R.id.editFAB);

        workoutBtn.setOnClickListener(this);
        progressBtn.setOnClickListener(this);
        socialBtn.setOnClickListener(this);

        editFAB.setOnClickListener(this);

        //class for getting and parsing Json data
        new GetJsonData().execute();

    }
    // method that catches if either button on page is pressed and it opens each corresponding page of that button
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.workoutBtn:
                Intent workoutIntent = new Intent(UserProfileActivity.this, workoutroutineActivity.class);
                workoutIntent.putExtra("user_name", userNameStr);
                startActivity(workoutIntent);
                break;

            case R.id.progressBtn:
                Intent userProgressIntent = new Intent(getApplicationContext(), UserProgressActivity.class);
                userProgressIntent.putExtra("user_name", userNameStr);
                startActivity(userProgressIntent);
                break;

            case R.id.socialBtn:
                Intent chatroom = new Intent(getApplicationContext(), webChatActivity.class);
                chatroom.putExtra("user_name", userNameStr);
                startActivity(chatroom);
                break;

            case R.id.editFAB:
                //TO DO
                editMode(true);
                editSaveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO
                        //Save user data to backend
                        editMode(false);
                    }
                });

                break;
        }
    }

    // methos that handles with see if edit mode is on or not
    private void editMode(boolean mode) {
        if (mode) {
            userRealNameTV.setVisibility(View.GONE);
            userHeightTV.setVisibility(View.GONE);
            userWeightTV.setVisibility(View.GONE);
            userRealNameET.setVisibility(View.VISIBLE);
            userHeightET.setVisibility(View.VISIBLE);
            userWeightET.setVisibility(View.VISIBLE);

            editSaveBtn = new Button(this);
            editSaveBtn.setText("Save");
            editSaveBtn.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            userProfileCL.addView(editSaveBtn);
        } else {
            userRealNameTV.setVisibility(View.VISIBLE);
            userHeightTV.setVisibility(View.VISIBLE);
            userWeightTV.setVisibility(View.VISIBLE);
            userRealNameET.setVisibility(View.GONE);
            userHeightET.setVisibility(View.GONE);
            userWeightET.setVisibility(View.GONE);
            editSaveBtn.setVisibility(View.GONE);
        }
    }


    /**
     * class that handles with background activty with getting data on user to the page
     */
    private class GetJsonData extends AsyncTask<Void, Void, Void> {

        // method that executes first before making doing background activity
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // before making http calls

        }

        // background activity connection request to url server
        @Override
        protected Void doInBackground(Void... arg0) {


            String getUrl = "http://coms-309-sb-7.misc.iastate.edu:8080/api/user";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                url = new URL(getUrl);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");

                StringBuffer response = new StringBuffer();
                int responseCode = urlConnection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) { //success
                    BufferedReader inurl = new BufferedReader(new InputStreamReader(
                            urlConnection.getInputStream()));
                    String inputLine;
                    while ((inputLine = inurl.readLine()) != null) {
                        response.append(inputLine);
                    }
                    inurl.close();

                } else {

                    Log.i("test", "POST request not worked.");
                }

                finalresult = response.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        // once executed then parses json of the final result
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            try {
                parseJson(finalresult);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // method that parses through the data fo user and updates fields on page
        private void parseJson(String json) throws JSONException {

            JSONArray jArr = new JSONArray(json);
            String realName = "";

            for (int count = 0; count < jArr.length(); count++) {
                JSONObject obj = jArr.getJSONObject(count);
                if (obj.getString("userName").equals(userNameStr)) {
                    userNameTV.setText(userNameStr);
                    realName = obj.getString("firstName") + " " + obj.getString("lastName");
                    userRealNameTV.setText(realName);
                    userHeightTV.setText(obj.getString("height") + " in");
                    userWeightTV.setText(obj.getString("weight") + " lbs");
                }
            }
        }

    }
}
