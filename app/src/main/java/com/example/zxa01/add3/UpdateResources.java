package com.example.zxa01.add3;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import java.util.Locale;

public class UpdateResources {

    public void update(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if(context.getResources().getConfiguration().getLocales().indexOf(Locale.ENGLISH) != -1){
                Locale.setDefault(Locale.TAIWAN);
            } else {
                Locale.setDefault(Locale.ENGLISH);
            }
            Resources res = context.getResources();
            Configuration config = new Configuration(res.getConfiguration());
            config.setLocale(Locale.getDefault());
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
    }

}
