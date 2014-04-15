package com.hesudi.reminderclient.app;

import android.net.Uri;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Martin on 15-4-2014.
 */
public class MyHttpAdapter {
    public static final String serverIp = "192.168.11.4";
    public static final Integer port = 3000;

    public static String httpRequest(String path) {
        return httpRequest(path, null);
    }

    public static String httpRequest(String path, String post) {
        try {
            return EntityUtils.toString(
                    (new DefaultHttpClient())
                            .execute(new HttpGet(new URI(
                                    "http", null,
                                    serverIp, port,
                                    path, post, null
                            ))).getEntity()
            );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return httpRequest(path, post);
    }

    public static String httpPut(String path) {
        try {
            return EntityUtils.toString(
                    (new DefaultHttpClient())
                            .execute(new HttpPut(new URI(
                                    "http", null,
                                    serverIp, port,
                                    path, null, null
                            ))).getEntity()
            );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return httpPut(path);
    }
}
