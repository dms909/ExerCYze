package com.example.exercyzefrontend.ui.progress;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exercyzefrontend.R;
import com.example.exercyzefrontend.ui.userprofile.UserProfileActivity;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class UserProgressActivity extends AppCompatActivity implements EntryDialog.EntryDialogListener {

    BarChart userWeightBC;
    TextView userTitleTV;
    Button addEntryBtn;
    //ArrayList<BarEntry> userEntries;
   // ArrayList<String> datesBC;
    int barEntryIndex = 0;
    private String finalresult;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_progress);

        userTitleTV = (TextView) findViewById(R.id.userTitleTV);
        userWeightBC = (BarChart) findViewById(R.id.userProgressBC);
        addEntryBtn = (Button) findViewById(R.id.addEntryBtn);

        addEntryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEntryDialog();
            }
        });

        //createUserProgressBC();
        //currentDate will be user for when the user enters their weight it will be associated to
        //the day the user adds the weight
        String currentDate = new SimpleDateFormat(("dd-MM-yyyy"), Locale.getDefault()).format(new Date());

        //user entries is a list that contains change of weight
        //so this is the y-axis data
        ArrayList<BarEntry> userEntries = new ArrayList<>();
        userEntries.add(new BarEntry((150f), 0));
        //userEntries.add(new BarEntry((148f), 1));
        //userEntries.add(new BarEntry((151f), 2));
        //userEntries.add(new BarEntry((152f), 3));
        //userEntries.add(new BarEntry((151f), 4));
        //userEntries.add(new BarEntry((151f), 5));
        //userEntries.add(new BarEntry((151f), 6));
        //BarDataSet userProgressDataSet = new BarDataSet(userEntries, "Weight (lbs)");
        BarDataSet userWeightDataSet = new BarDataSet(userEntries,"Weight (lbs)");

        ArrayList<String> datesBC = new ArrayList<>();
        datesBC.add(currentDate);
        //datesByWeek.add("10/22");
        //datesByWeek.add("10/23");
        //datesByWeek.add("10/24");
        //datesByWeek.add("10/25");
        //datesByWeek.add("10/26");
        //datesByWeek.add("10/27");

        BarData userProgressData = new BarData(datesBC, userWeightDataSet);
        userWeightBC.setData(userProgressData);

        userWeightBC.setTouchEnabled(true);
        userWeightBC.setDragEnabled(true);
        userWeightBC.setScaleEnabled(true);

        new GetJsonData().execute();

    }
    public void openEntryDialog() {
        EntryDialog entryDialog = new EntryDialog();
        entryDialog.show(getSupportFragmentManager(), "entry dialog");
    }

    @Override
    public void applyValue(double weightEntryVal) {
        addEntryToBC(weightEntryVal, new SimpleDateFormat(("dd-MM-yyyy"), Locale.getDefault()).format(new Date())
                , barEntryIndex);
    }

    public void addEntryToBC(double weightEntry, String date, int chartIndex) {
        //userEntries.add(new BarEntry((float) weightEntry, chartIndex));
        //chartIndex++;
        //datesBC.add(date);
    }
    public void createUserProgressBC() {
       // BarDataSet userWeightDataSet = new BarDataSet(userEntries,"Weight (lbs)");
       // BarData userWeightData = new BarData(datesBC,userWeightDataSet);
       // userWeightBC.setData(userWeightData);
        //barChart.setDescription("My First Bar Graph!");
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
                userTitleTV.setText(obj.getString("userName") + "'s Progress");
                //double latitude= obj.getDouble("latitude");
                //double longitude= obj.getDouble("longitude");
                //String placeName= obj.getString("placeName");
                //userNameTV.setText(obj.getString("userName"));
                //realName = obj.getString("firstName") + " " + obj.getString("lastName");
                //userRealNameTV.setText(realName);
                //userHeightTV.setText(obj.getString("height") + " in");
                //userWeightTV.setText(obj.getString("weight") + " lbs");
            }
        }

    }
}
