package com.example.covid19india;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView txtConfirmed , Active , Recovered , Deaths , deltaConfirmed , deltaRecovered , deltaDead , Testing, lastUpdate;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        txtConfirmed = findViewById(R.id.txtConfirmed);
        deltaConfirmed = findViewById(R.id.txtDeltaConfirmed);
        Active = findViewById(R.id.txtActive);
        Deaths = findViewById(R.id.txtDead);
        deltaDead = findViewById(R.id.txtDeltaDead);
        Recovered = findViewById(R.id.txtRecovered);
        deltaRecovered = findViewById(R.id.txtDeltaRecovered);
        Testing = findViewById(R.id.txtTest);
        lastUpdate = findViewById(R.id.txtLastUpdate);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19india.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Covid19API covid19API = retrofit.create(Covid19API.class);

        Call<AllData> call = covid19API.getAllData();

        call.enqueue(new Callback<AllData>() {
            @Override
            public void onResponse(Call<AllData> call, Response<AllData> response) {
                if (!response.isSuccessful()){
                    txtConfirmed.setText("Code :"+response.code());
                    return;
                }
                AllData allData = response.body();
                List<Statewise> statewiseList = allData.getStatewise();
                List<Tested> testedList = allData.getTested();

                //Displaying Data
                //Confirmed cases
                //10,000 to 99,999
                if (statewiseList.get(0).getConfirmed().length() == 5){
                    StringBuilder sb = new StringBuilder(statewiseList.get(0).getConfirmed());
                    sb.insert(2,",");
                    txtConfirmed.setText(sb);
                } //100,000 to 999,999
                else if (statewiseList.get(0).getConfirmed().length() == 6){
                    StringBuilder sb = new StringBuilder(statewiseList.get(0).getConfirmed());
                    sb.insert(3,",");
                    txtConfirmed.setText(sb);
                } //1,000,000 to 9,999,999
                else if (statewiseList.get(0).getConfirmed().length() == 7){
                    StringBuilder sb = new StringBuilder(statewiseList.get(0).getConfirmed());
                    sb.insert(4,",");
                    sb.insert(1,",");
                    txtConfirmed.setText(sb);
                } //10,000,000 to 99,999,999
                else if (statewiseList.get(0).getConfirmed().length() == 8) {
                    StringBuilder sb = new StringBuilder(statewiseList.get(0).getConfirmed());
                    sb.insert(5, ",");
                    sb.insert(2, ",");
                    txtConfirmed.setText(sb);
                } //100,000,000 to 999,999,999
                else {
                    StringBuilder sb = new StringBuilder(statewiseList.get(0).getConfirmed());
                    sb.insert(6, ",");
                    sb.insert(3, ",");
                    txtConfirmed.setText(sb);
                }

                //Active Cases
                //10,000 to 99,999
                if (statewiseList.get(0).getActive().length() == 5){
                    StringBuilder sb = new StringBuilder(statewiseList.get(0).getActive());
                    sb.insert(2,",");
                    Active.setText(sb);
                }  //100,000 to 999,999
                else if (statewiseList.get(0).getConfirmed().length() == 6){
                    StringBuilder sb = new StringBuilder(statewiseList.get(0).getActive());
                    sb.insert(3,",");
                    Active.setText(sb);
                } else if (statewiseList.get(0).getConfirmed().length() == 6){
                    StringBuilder sb = new StringBuilder(statewiseList.get(0).getActive());
                    sb.insert(3,",");
                    Active.setText(sb);
                } //1,000,000 to 9,999,999
                else if (statewiseList.get(0).getConfirmed().length() == 7){
                    StringBuilder sb = new StringBuilder(statewiseList.get(0).getActive());
                    sb.insert(4,",");
                    sb.insert(1,",");
                    Active.setText(sb);
                } //10,000,000 to 99,999,999
                else if (statewiseList.get(0).getConfirmed().length() == 8) {
                    StringBuilder sb = new StringBuilder(statewiseList.get(0).getActive());
                    sb.insert(5, ",");
                    sb.insert(2, ",");
                    Active.setText(sb);
                } //100,000,000 to 999,999,999
                //10,000 and below
                else if (statewiseList.get(0).getConfirmed().length() == 4){
                    StringBuilder sb = new StringBuilder(statewiseList.get(0).getActive());
                    sb.insert(1,",");
                    Active.setText(sb);
                } else {
                    Active.setText(statewiseList.get(0).getActive());
                }

                //Recovered Cases
                //10,000 to 99,999
                if (statewiseList.get(0).getRecovered().length() == 5){
                    StringBuilder sb = new StringBuilder(statewiseList.get(0).getRecovered());
                    sb.insert(2,",");
                    Recovered.setText(sb);
                } //100,000 to 999,999
                else if (statewiseList.get(0).getRecovered().length() == 6){
                    StringBuilder sb = new StringBuilder(statewiseList.get(0).getRecovered());
                    sb.insert(3,",");
                    Recovered.setText(sb);
                } //1,000,000 to 9,999,999
                else if (statewiseList.get(0).getRecovered().length() == 7){
                    StringBuilder sb = new StringBuilder(statewiseList.get(0).getRecovered());
                    sb.insert(4,",");
                    sb.insert(1,",");
                    Recovered.setText(sb);
                } //10,000,000 to 99,999,999
                else if (statewiseList.get(0).getRecovered().length() == 8) {
                    StringBuilder sb = new StringBuilder(statewiseList.get(0).getRecovered());
                    sb.insert(5, ",");
                    sb.insert(2, ",");
                    Recovered.setText(sb);
                } //100,000,000 to 999,999,999
                else {
                    StringBuilder sb = new StringBuilder(statewiseList.get(0).getRecovered());
                    sb.insert(6, ",");
                    sb.insert(3, ",");
                    Recovered.setText(sb);
                }

                //Deaths
                //1,000 to 9,999
                if (statewiseList.get(0).getDeaths().length() == 4){
                    StringBuilder sb = new StringBuilder(statewiseList.get(0).getDeaths());
                    sb.insert(1,",");
                    Deaths.setText(sb);
                }//10,000 to 99,999
                else if (statewiseList.get(0).getDeaths().length() == 5){
                    StringBuilder sb = new StringBuilder(statewiseList.get(0).getDeaths());
                    sb.insert(2,",");
                    Deaths.setText(sb);
                } //100,000 to 999,999
                else if (statewiseList.get(0).getDeaths().length() == 6){
                    StringBuilder sb = new StringBuilder(statewiseList.get(0).getDeaths());
                    sb.insert(3,",");
                    Deaths.setText(sb);
                } //1,000,000 to 9,999,999
                else if (statewiseList.get(0).getDeaths().length() == 7){
                    StringBuilder sb = new StringBuilder(statewiseList.get(0).getDeaths());
                    sb.insert(4,",");
                    sb.insert(1,",");
                    Deaths.setText(sb);
                } //10,000,000 to 99,999,999
                else if (statewiseList.get(0).getDeaths().length() == 8) {
                    StringBuilder sb = new StringBuilder(statewiseList.get(0).getDeaths());
                    sb.insert(5, ",");
                    sb.insert(2, ",");
                    Deaths.setText(sb);
                } //100,000,000 to 999,999,999
                else {
                    StringBuilder sb = new StringBuilder(statewiseList.get(0).getDeaths());
                    sb.insert(6, ",");
                    sb.insert(3, ",");
                    Deaths.setText(sb);
                }

                //Tests
                //1,000,000 to 9,999,999
                if (testedList.get(63).getTotalsamplestested().length() == 7){
                    StringBuilder sb = new StringBuilder(testedList.get(63).getTotalsamplestested());
                    sb.insert(4,",");
                    sb.insert(1,",");
                    Testing.setText(sb);
                } //10,000,000 to 99,999,999
                else if (testedList.get(63).getTotalsamplestested().length() == 8) {
                    StringBuilder sb = new StringBuilder(testedList.get(63).getTotalsamplestested());
                    sb.insert(5, ",");
                    sb.insert(2, ",");
                    Testing.setText(sb);
                } //100,000,000 to 999,999,999
                else {
                    StringBuilder sb = new StringBuilder(testedList.get(63).getTotalsamplestested());
                    sb.insert(6, ",");
                    sb.insert(3, ",");
                    Testing.setText(sb);
                }
                deltaConfirmed.setText("[+"+statewiseList.get(0).getDeltaconfirmed()+"]");
                deltaRecovered.setText("[+"+statewiseList.get(0).getDeltarecovered()+"]");
                deltaDead.setText("[+"+statewiseList.get(0).getDeltadeaths()+"]");
                lastUpdate.setText(statewiseList.get(0).getLastupdatedtime());

            }

            @Override
            public void onFailure(Call<AllData> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
