package com.temperature.myweather;

import com.google.gson.annotations.SerializedName;

public class temperature {
   @SerializedName("main")
    Main main;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
