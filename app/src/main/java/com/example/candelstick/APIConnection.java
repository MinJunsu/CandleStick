package com.example.candelstick;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class APIConnection extends AsyncTask<String, Void, String>
{
    private static String domain = "https://www.bitmex.com";
    private String APIKey, APISecret;

    public APIConnection(String APIKey, String APISecret)
    {
        this.APIKey = APIKey;
        this.APISecret = APISecret;
    }

    public APIConnection()
    {

    }

    public String get_data_set()
    {
        String result = "";
        try
        {
            result = execute("GET", "/api/v1/trade/bucketed?binSize=1h&partial=true&symbol=XBTUSD&start=0&count=20&reverse=true", "").get();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public void create_order(String data)
    {
        try
        {
            String result = execute("POST", "/api/v1/order", data).get();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void get_wallet(String data)
    {
        String result = "";
        try
        {
            result = execute("GET", "/api/v1/user/walletSummary",data.equals("") ? "" : data).get();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Log.d("APIConnectionResult", result);
    }

    private String make_signature(String message)
    {
        StringBuffer buffer = new StringBuffer();
        try
        {
            // hash 알고리즘과 암호화 key 적용
            Mac hasher = Mac.getInstance("HmacSHA256");
            hasher.init(new SecretKeySpec(this.APISecret.getBytes(), "HmacSHA256"));

            // messages를 암호화 적용 후 byte 배열 형태의 결과 리턴
            byte[] hash = hasher.doFinal(message.getBytes());
            for (int i = 0; i < hash.length; i++)
            {
                int d = hash[i];
                d += (d < 0)? 256 : 0;
                if (d < 16)
                {
                    buffer.append("0");
                }
                buffer.append(Integer.toString(d, 16));
            }
        }

        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        catch (InvalidKeyException e)
        {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    @Override
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String... params)
    {
        String expires = String.valueOf(System.currentTimeMillis()+100);
        String message = params[0] + params[1] + expires + params[2];
        String signature = make_signature(message);
        try
        {
            URL url = new URL("https://www.bitmex.com" + params[1]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(params[0]);
            con.setRequestProperty("api-expires", expires);
            con.setRequestProperty("api-key", this.APIKey);
            con.setRequestProperty("api-signature", signature);
            con.setRequestProperty("Content-type", "application/json");
            con.setRequestProperty("Accept", "application/json");

            if(params[0].equals("POST"))
            {
                OutputStream os = con.getOutputStream();
                os.write(params[2].getBytes("UTF-8"));
            }

            int responseStatusCode = con.getResponseCode();
            InputStream inputStream;

            if (responseStatusCode == HttpURLConnection.HTTP_OK)
            {
                inputStream = con.getInputStream();
            }
            else
            {
                inputStream = con.getErrorStream();
            }


            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = bufferedReader.readLine()) != null)
            {
                sb.append(line);
            }

            return sb.toString();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;


    }
}
