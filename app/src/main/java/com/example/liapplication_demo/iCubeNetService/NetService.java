package com.example.liapplication_demo.iCubeNetService;

import android.util.Log;

import com.example.liapplication_demo.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import retrofit2.http.HTTP;

public class NetService {
    final private String TAG = "iCube";

    private String connectURl;

    private BufferedReader bufferedReader;
    private InputStreamReader inputStreamReader;
    private InputStream inputStream;

    private String JsonData;

    /**
     * 构造函数传入地址
     *
     * @param connectURL
     */
    public NetService(String connectURL) {
        this.connectURl = connectURL;
    }

    /**
     * Get data
     *
     * @return
     * @throws IOException
     */
    public String requestData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(connectURl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(10000);
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Accept-Language", "zh-CN,zh,q=0.9");
                    connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
                    connection.setRequestProperty("Accept", "*/*");
                    connection.connect();

                    // 结果码
                    int responsecode = connection.getResponseCode();
                    if (responsecode == 200) {
                        Map<String, List<String>> headerFields = connection.getHeaderFields();
                        Set<Map.Entry<String, List<String>>> entries = headerFields.entrySet();
                        for(Map.Entry<String, List<String>> entry : entries) {
                            Log.d(TAG, entry.getKey() + "==" + entry.getValue());
                        }

                        InputStream inputStream = connection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                        String line =  bufferedReader.readLine();
                        Log.d(TAG, line);

                        JsonData = line;
                    }
                } catch (Exception e) {
                    Log.d(TAG, e.getMessage());
                } finally {

                }
            }
        });

        return this.JsonData;
    }

    /**
     * post data
     *
     * @return
     */
    public String postData() {


        return this.JsonData;
    }
}
