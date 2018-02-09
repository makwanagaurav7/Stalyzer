package com.example.admin.stalyzer;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by RAJAT JADIA on 27-12-2017.
 */

public class Internet
{
    Context context;
    public Internet(Context context)
    {
        this.context = context;
    }
    public boolean isNetworkAvailable()
    {
        ConnectivityManager cm =(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}
