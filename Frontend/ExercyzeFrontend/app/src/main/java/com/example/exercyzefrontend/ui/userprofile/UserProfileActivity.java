package com.example.exercyzefrontend.ui.userprofile;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.exercyzefrontend.R;
import com.example.exercyzefrontend.ui.progress.UserProgressActivity;

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

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener{

    Button workoutBtn, progressBtn, socialBtn, jsonBtn;
    TextView userNameTV, userRealNameTV, userHeightTV, userWeightTV;
    private String finalresult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userNameTV = (TextView) findViewById(R.id.userName);
        userRealNameTV = (TextView) findViewById(R.id.userRealName);
        userHeightTV = (TextView) findViewById(R.id.userHeight);
        userWeightTV = (TextView) findViewById(R.id.userWeight);

        workoutBtn = (Button) findViewById(R.id.workoutBtn);
        progressBtn = (Button) findViewById(R.id.progressBtn);
        socialBtn = (Button) findViewById(R.id.socialBtn);
        jsonBtn = (Button) findViewById(R.id.jsonArrBtn);

        workoutBtn.setOnClickListener(this);
        progressBtn.setOnClickListener(this);
        socialBtn.setOnClickListener(this);
        jsonBtn.setOnClickListener(this);

        //class for getting and parsing Json data
        new GetJsonData().execute();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.workoutBtn:
                //TO DO
                break;

            case R.id.progressBtn:
                Intent userProgressIntent = new Intent(getApplicationContext(), UserProgressActivity.class);

                startActivity(userProgressIntent);
                break;

            case R.id.socialBtn:
                //TO DO
                break;

            case R.id.jsonArrBtn:
                new GetJsonData().execute();
                break;
        }
    }

    private class GetJsonData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // before making http calls

        }

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

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            try {
                parseJson(finalresult);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void parseJson(String json) throws JSONException {

            JSONArray jArr = new JSONArray(json);
            String realName = "";

            for (int count = 0; count < jArr.length(); count++) {
                JSONObject obj = jArr.getJSONObject(count);
                //double latitude= obj.getDouble("latitude");
                //double longitude= obj.getDouble("longitude");
                //String placeName= obj.getString("placeName");
                userNameTV.setText(obj.getString("userName"));
                realName = obj.getString("firstName") + " " + obj.getString("lastName");
                userRealNameTV.setText(realName);
                userHeightTV.setText(obj.getString("weight"));
                userWeightTV.setText(obj.getString("height"));
            }
        }

    }
}
