package com.example.exercyzefrontend.ui.progress;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.exercyzefrontend.R;
import com.example.exercyzefrontend.app.AppController;
import com.example.exercyzefrontend.ui.userprofile.UserProfileActivity;
import com.example.exercyzefrontend.utils.Const;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * class that handles with ui of the progress page
 */
public class UserProgressActivity extends AppCompatActivity implements EntryDialog.EntryDialogListener {

    private String TAG = UserProfileActivity.class.getSimpleName();
    /**
     * Bar Chart modifiable variable
     */
    BarChart userWeightBC;

    /**
     *  Data set that is to be entered into the bar chart
     */
    private BarDataSet userWeightDataSet;

    /**
     * keeps track of all the entries
     */
    private ArrayList<BarEntry> userEntries;

    /**
     * will keep track of the current date when the user enters a data point to the bar chart
     */
    private ArrayList<String> datesBC;
    private Calendar c;
    private TextView userTitleTV;
    private Button addEntryBtn, exitBtn;
    private String userID;
    private int barEntryIndex = 0;
    private String finalresult;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_progress);

        userTitleTV = (TextView) findViewById(R.id.userTitleTV);
        userWeightBC = (BarChart) findViewById(R.id.userProgressBC);
        addEntryBtn = (Button) findViewById(R.id.addEntryBtn);
        exitBtn = (Button) findViewById(R.id.exitBtn);

        final String userNameStr = getIntent().getStringExtra("user_name");
        userID = getIntent().getStringExtra("user_ID");
        userTitleTV.setText(userNameStr+ "'s Progress");

        // if add entry button is pressed then opens dialog method
        addEntryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEntryDialog();
            }
        });

        // if button to go back is pressed then starts up the user profile page or home user screen
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent userProfileIntent = new Intent(UserProgressActivity.this, UserProfileActivity.class);
                userProfileIntent.putExtra("user_name", userNameStr);
                startActivity(userProfileIntent);
            }
        });

        String currentDate = new SimpleDateFormat(("dd-MM-yyyy"), Locale.getDefault()).format(new Date());

        //user entries is a list that contains change of weight
        //so this is the y-axis data
        userEntries = new ArrayList<>();
        //userEntries.add(new BarEntry((150f), 0)); //hard coding 150 to test bar chart
        userWeightDataSet = new BarDataSet(userEntries, "Weight (lbs)");

        datesBC = new ArrayList<>();
        //datesBC.add(currentDate);

        // Bar data to be used for the bar chart which consists of both the y-axis data and the x-axis data
        BarData userProgressData = new BarData(datesBC, userWeightDataSet);
        userWeightBC.setData(userProgressData);
        userWeightBC.setTouchEnabled(true);
        userWeightBC.setDragEnabled(true);
        userWeightBC.setScaleEnabled(true);
        //barEntryIndex++;

        new GetJsonData().execute();
    }

    /**
     * will open the entry dialog for entering a new weight value
     */
    private void openEntryDialog() {
        EntryDialog entryDialog = new EntryDialog();
        entryDialog.show(getSupportFragmentManager(), "entry dialog");
    }

    /**
     * will apply the user entered data to the bar chart
     * @param weightEntryVal user weight entry
     */
    @Override
    public void applyValue(double weightEntryVal) {
        c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 0); //date is now added as current date
        Date currentDate = c.getTime();
        addEntryToBC(weightEntryVal, currentDate, barEntryIndex);
        postUserProgressModel(userID, (int) weightEntryVal, currentDate);
        //barEntryIndex++;
    }

    /**
     * helper method for generating bar chart
     * @param weightEntry
     * @param chartIndex
     */
    private void addEntryToBC(double weightEntry, Date dateEntry, int chartIndex) {
        //BarEntry(arg1, arg2) -> arg1 must be a float value
        BarEntry newEntry = new BarEntry((float) weightEntry, chartIndex);
        userWeightDataSet.addEntry(newEntry);
        /*c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 0); //date is now added as current date
        Date currentDate = c.getTime();*/
        datesBC.add(new SimpleDateFormat(("dd-MM-yyyy")).format(dateEntry));
        userWeightBC.setData(new BarData(datesBC, userWeightDataSet));
        //barEntryIndex++;
        barEntryIndex++;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * class to handle with in the background with getting data from the url users from server
     */
    private class GetJsonData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // before making http calls
        }

        @Override
        protected Void doInBackground(Void... arg0) {


            String getUrl = "http://coms-309-sb-7.misc.iastate.edu:8080/api/user-progress/" + userID;
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

        // methos that parses json to return message with username data in message
        private void parseJson(String json) throws JSONException {

            JSONArray jArr = new JSONArray(json);
            String realName = "";
            String weightStr = "";
            String dateStr = "";
            Date dateEntry;
            int indexBC = 0;
            for (int count = 0; count < jArr.length(); count++) {
                JSONObject obj = jArr.getJSONObject(count);
                if(obj.getString("user_id").equals(userID)) {
                    weightStr = obj.getString("new_weight");
                    dateStr = obj.getString("date_entered");
                    try {
                        dateEntry = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(dateStr);
                        addEntryToBC(Double.parseDouble(weightStr), dateEntry, indexBC);
                        System.out.println(dateEntry);
                        System.out.println(dateEntry);
                        System.out.println(dateEntry);
                        System.out.println(dateEntry);

                    } catch(ParseException e) {
                        e.printStackTrace();
                    }
                    indexBC++;
                        //addEntryToBC();
                    //String uName = obj.getString("userName") + "'s Progress";
                    //userTitleTV.setText(uName);

                }
            }
        }

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private void postUserProgressModel(String userID, int newWeight, Date newDate) {
        final Map<String, String> params = new HashMap<String, String>();

        params.put("user_id", userID);
        params.put("new_weight", newWeight + "");
        params.put("date_entered", newDate + "");

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Const.URL_NEW_PROGRESS_ENTRY, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error: " + error.getMessage());
                VolleyLog.d(TAG, "Error: " + error.getMessage()
                        + "Error cause: " + error.getCause());
                error.printStackTrace();

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
        System.out.println(jsonObjReq);
        System.out.println(jsonObjReq);
        System.out.println(jsonObjReq);
        System.out.println(jsonObjReq);
        System.out.println(jsonObjReq);

        AppController.getInstance().addToRequestQueue(jsonObjReq);

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
}
