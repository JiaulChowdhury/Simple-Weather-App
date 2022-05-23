package com.temperature.myweather;

import androidx.appcompat.app.AppCompatActivity;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText et;
    TextView tv;
    String url = "api.openweathermap.org/data/2.5/forecast?q={city name}&appid={API key}";
    String apikey = "1f5fd6ccfd4682612605db4d0760bf82";
    LocationManager manager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.et);
        tv = findViewById(R.id.tv);

    }

    public void getweather(View v){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherapi myapi=retrofit.create(weatherapi.class);
        Call<temperature> examplecall=myapi.getweather(et.getText().toString().trim(),apikey);
        examplecall.enqueue(new Callback<temperature>() {
            @Override
            public void onResponse(Call<temperature> call, Response<temperature> response) {
                if(response.code()==404){
                    Toast.makeText(MainActivity.this,"Enter a City",Toast.LENGTH_LONG).show();
                }
                else if(!(response.isSuccessful())){
                    Toast.makeText(MainActivity.this,response.code()+" ",Toast.LENGTH_LONG).show();
                    return;
                }
                temperature mydata=response.body();
                Main main=mydata.getMain();
                Double temp=main.getTemp();
                Integer temperature=(int)(temp-273.15);
                tv.setText(String.valueOf(temperature)+"°C");
            }

            @Override
            public void onFailure(Call<temperature> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });



    }
}
