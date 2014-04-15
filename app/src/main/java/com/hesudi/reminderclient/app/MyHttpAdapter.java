package com.hesudi.reminderclient.app;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Martin on 15-4-2014.
 */
public class MyHttpAdapter {
    public static final String serverIp = "10.0.0.1:80/";
    public static String httpRequest(String url) {
        try {
            return EntityUtils.toString(
                    (new DefaultHttpClient())
                            .execute(new HttpGet(url))
                            .getEntity()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpRequest(url);
    }
}
