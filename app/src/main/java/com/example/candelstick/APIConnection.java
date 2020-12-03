package com.example.candelstick;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

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


    public String getData()
    {
        return "[{\"timestamp\":\"2020-12-04T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":19221.5,\"high\":19310,\"low\":18956,\"close\":19098,\"trades\":43728,\"volume\":206088165,\"vwap\":19127.7735,\"lastSize\":2871,\"turnover\":1077663429289,\"homeNotional\":10776.63429289001,\"foreignNotional\":206088165},{\"timestamp\":\"2020-12-03T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":18793,\"high\":19351,\"low\":18334,\"close\":19221.5,\"trades\":370173,\"volume\":1729762100,\"vwap\":18939.3939,\"lastSize\":1200,\"turnover\":9134339757994,\"homeNotional\":91343.397579941,\"foreignNotional\":1729762100},{\"timestamp\":\"2020-12-02T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":19714.5,\"high\":19958,\"low\":18140,\"close\":18793,\"trades\":519659,\"volume\":3165344547,\"vwap\":19146.0846,\"lastSize\":1,\"turnover\":16533938043906,\"homeNotional\":165339.38043906112,\"foreignNotional\":3165344547},{\"timestamp\":\"2020-12-01T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":18195,\"high\":19949,\"low\":18195.5,\"close\":19714.5,\"trades\":433103,\"volume\":3107683217,\"vwap\":19157.0881,\"lastSize\":500,\"turnover\":16223554194812,\"homeNotional\":162235.54194811924,\"foreignNotional\":3107683217},{\"timestamp\":\"2020-11-30T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":17736.5,\"high\":18345,\"low\":17536.5,\"close\":18195,\"trades\":251109,\"volume\":1241460612,\"vwap\":18008.2838,\"lastSize\":12,\"turnover\":6894686127981,\"homeNotional\":68946.86127980931,\"foreignNotional\":1241460612},{\"timestamp\":\"2020-11-29T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":17153,\"high\":17874.5,\"low\":16898.5,\"close\":17736.5,\"trades\":258100,\"volume\":1356626364,\"vwap\":17364.1257,\"lastSize\":18677,\"turnover\":7813871671140,\"homeNotional\":78138.71671140012,\"foreignNotional\":1356626364},{\"timestamp\":\"2020-11-28T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":17165.5,\"high\":17477,\"low\":16460,\"close\":17153,\"trades\":372194,\"volume\":2299039122,\"vwap\":16949.1525,\"lastSize\":12,\"turnover\":13564995610756,\"homeNotional\":135649.95610756162,\"foreignNotional\":2299039122},{\"timestamp\":\"2020-11-27T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":18751,\"high\":18924.5,\"low\":16220,\"close\":17165.5,\"trades\":677659,\"volume\":4869547159,\"vwap\":17307.0267,\"lastSize\":12,\"turnover\":28140523172837,\"homeNotional\":281405.2317283741,\"foreignNotional\":4869547159},{\"timestamp\":\"2020-11-26T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":19193,\"high\":19546.5,\"low\":18490.5,\"close\":18751,\"trades\":391288,\"volume\":2400452266,\"vwap\":19051.2479,\"lastSize\":884,\"turnover\":12601192786155,\"homeNotional\":126011.92786155008,\"foreignNotional\":2400452266},{\"timestamp\":\"2020-11-25T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":18393.5,\"high\":19449,\"low\":18045,\"close\":19193,\"trades\":437723,\"volume\":2767550413,\"vwap\":18935.8076,\"lastSize\":109,\"turnover\":14616578141003,\"homeNotional\":146165.78141003122,\"foreignNotional\":2767550413},{\"timestamp\":\"2020-11-24T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":18464,\"high\":18785,\"low\":18045,\"close\":18393.5,\"trades\":373871,\"volume\":2156681517,\"vwap\":18446.781,\"lastSize\":11,\"turnover\":11693390327421,\"homeNotional\":116933.90327420986,\"foreignNotional\":2156681517},{\"timestamp\":\"2020-11-23T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":18725,\"high\":18781.5,\"low\":17656,\"close\":18464,\"trades\":387726,\"volume\":2186105725,\"vwap\":18341.8929,\"lastSize\":35,\"turnover\":11920159190551,\"homeNotional\":119201.59190550967,\"foreignNotional\":2186105725},{\"timestamp\":\"2020-11-22T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":18672.5,\"high\":18989.5,\"low\":18283.5,\"close\":18725,\"trades\":344309,\"volume\":1851016742,\"vwap\":18712.5749,\"lastSize\":10,\"turnover\":9893621495189,\"homeNotional\":98936.21495188968,\"foreignNotional\":1851016742},{\"timestamp\":\"2020-11-21T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":17824,\"high\":18837.5,\"low\":17763,\"close\":18672.5,\"trades\":378892,\"volume\":2331879627,\"vwap\":18362.1006,\"lastSize\":195,\"turnover\":12701640868931,\"homeNotional\":127016.40868930997,\"foreignNotional\":2331879627},{\"timestamp\":\"2020-11-20T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":17788.5,\"high\":18230.5,\"low\":17350,\"close\":17824,\"trades\":369721,\"volume\":2165844721,\"vwap\":17818.9594,\"lastSize\":13,\"turnover\":12156255424874,\"homeNotional\":121562.5542487406,\"foreignNotional\":2165844721},{\"timestamp\":\"2020-11-19T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":17683.5,\"high\":18500,\"low\":17221,\"close\":17788.5,\"trades\":556666,\"volume\":3789521364,\"vwap\":17892.2884,\"lastSize\":13,\"turnover\":21180301468354,\"homeNotional\":211803.0146835402,\"foreignNotional\":3789521364},{\"timestamp\":\"2020-11-18T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":16718,\"high\":17890,\"low\":16532.5,\"close\":17683.5,\"trades\":402901,\"volume\":2840549478,\"vwap\":17253.2781,\"lastSize\":1000,\"turnover\":16464222863714,\"homeNotional\":164642.22863714033,\"foreignNotional\":2840549478},{\"timestamp\":\"2020-11-17T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":15973,\"high\":16886,\"low\":15889.5,\"close\":16718,\"trades\":297433,\"volume\":1715669172,\"vwap\":16463.6154,\"lastSize\":10,\"turnover\":10421327913522,\"homeNotional\":104213.27913522076,\"foreignNotional\":1715669172},{\"timestamp\":\"2020-11-16T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":16083,\"high\":16169.5,\"low\":15795,\"close\":15973,\"trades\":200991,\"volume\":1008923756,\"vwap\":15994.8816,\"lastSize\":100,\"turnover\":6308422073643,\"homeNotional\":63084.22073643004,\"foreignNotional\":1008923756},{\"timestamp\":\"2020-11-15T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":16340,\"high\":16340,\"low\":15718,\"close\":16083,\"trades\":256364,\"volume\":1295478185,\"vwap\":15989.7665,\"lastSize\":13,\"turnover\":8103176497842,\"homeNotional\":81031.76497841942,\"foreignNotional\":1295478185},{\"timestamp\":\"2020-11-14T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":16316,\"high\":16499,\"low\":15965.5,\"close\":16340,\"trades\":291138,\"volume\":1579054970,\"vwap\":16278.6912,\"lastSize\":1,\"turnover\":9700513743322,\"homeNotional\":97005.1374332211,\"foreignNotional\":1579054970},{\"timestamp\":\"2020-11-13T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":15719,\"high\":16368.5,\"low\":15460,\"close\":16316,\"trades\":391874,\"volume\":2229996928,\"vwap\":15969.3389,\"lastSize\":1,\"turnover\":13965761752923,\"homeNotional\":139657.61752923034,\"foreignNotional\":2229996928},{\"timestamp\":\"2020-11-12T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":15309.5,\"high\":16067,\"low\":15288.5,\"close\":15719,\"trades\":296481,\"volume\":1559444291,\"vwap\":15661.7071,\"lastSize\":13,\"turnover\":9958232052844,\"homeNotional\":99582.32052843933,\"foreignNotional\":1559444291},{\"timestamp\":\"2020-11-11T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":15342.5,\"high\":15490,\"low\":15079.5,\"close\":15309.5,\"trades\":250864,\"volume\":1355001035,\"vwap\":15306.9034,\"lastSize\":1701,\"turnover\":8852487082345,\"homeNotional\":88524.87082345044,\"foreignNotional\":1355001035},{\"timestamp\":\"2020-11-10T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":15499,\"high\":15875.5,\"low\":14843,\"close\":15342.5,\"trades\":411544,\"volume\":2377187415,\"vwap\":15358.6239,\"lastSize\":10,\"turnover\":15479161556076,\"homeNotional\":154791.61556075845,\"foreignNotional\":2377187415},{\"timestamp\":\"2020-11-09T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":14838,\"high\":15670,\"low\":14738,\"close\":15499,\"trades\":279845,\"volume\":1455313545,\"vwap\":15229.9726,\"lastSize\":245,\"turnover\":9555954365046,\"homeNotional\":95559.54365045967,\"foreignNotional\":1455313545},{\"timestamp\":\"2020-11-08T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":15608.5,\"high\":15780,\"low\":14366.5,\"close\":14838,\"trades\":395384,\"volume\":2592662622,\"vwap\":15114.873,\"lastSize\":13,\"turnover\":17154333752544,\"homeNotional\":171543.3375254379,\"foreignNotional\":2592662622},{\"timestamp\":\"2020-11-07T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":15605,\"high\":15979,\"low\":15200.5,\"close\":15608.5,\"trades\":405357,\"volume\":2827497868,\"vwap\":15595.758,\"lastSize\":2000,\"turnover\":18131422734850,\"homeNotional\":181314.22734849955,\"foreignNotional\":2827497868},{\"timestamp\":\"2020-11-06T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":14151.5,\"high\":15791.5,\"low\":14114,\"close\":15605,\"trades\":491134,\"volume\":3180721468,\"vwap\":14898.6889,\"lastSize\":13,\"turnover\":21349993295845,\"homeNotional\":213499.93295845034,\"foreignNotional\":3180721468},{\"timestamp\":\"2020-11-05T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":14034,\"high\":14279,\"low\":13533.5,\"close\":14151.5,\"trades\":418403,\"volume\":2129841518,\"vwap\":13910.1405,\"lastSize\":40,\"turnover\":15312032933793,\"homeNotional\":153120.32933792952,\"foreignNotional\":2129841518},{\"timestamp\":\"2020-11-04T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":13566,\"high\":14077,\"low\":13288,\"close\":14034,\"trades\":329985,\"volume\":1673203841,\"vwap\":13620.267,\"lastSize\":1000,\"turnover\":12285360861518,\"homeNotional\":122853.60861518083,\"foreignNotional\":1673203841},{\"timestamp\":\"2020-11-03T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":13756.5,\"high\":13840,\"low\":13212.5,\"close\":13566,\"trades\":323653,\"volume\":1740227757,\"vwap\":13544.6296,\"lastSize\":3000,\"turnover\":12849612541975,\"homeNotional\":128496.12541975036,\"foreignNotional\":1740227757},{\"timestamp\":\"2020-11-02T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":13802.5,\"high\":13903,\"low\":13622.5,\"close\":13756.5,\"trades\":228598,\"volume\":922811251,\"vwap\":13758.9433,\"lastSize\":1201,\"turnover\":6707422632944,\"homeNotional\":67074.22632944012,\"foreignNotional\":922811251},{\"timestamp\":\"2020-11-01T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":13557.5,\"high\":14135,\"low\":13421,\"close\":13802.5,\"trades\":367719,\"volume\":1772871976,\"vwap\":13762.7305,\"lastSize\":10,\"turnover\":12881864819806,\"homeNotional\":128818.64819806023,\"foreignNotional\":1772871976},{\"timestamp\":\"2020-10-31T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":13461.5,\"high\":13675,\"low\":13119.5,\"close\":13557.5,\"trades\":366800,\"volume\":1884089370,\"vwap\":13401.2329,\"lastSize\":2879,\"turnover\":14059097898281,\"homeNotional\":140590.97898281037,\"foreignNotional\":1884089370},{\"timestamp\":\"2020-10-30T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":13268,\"high\":13638,\"low\":12983,\"close\":13461.5,\"trades\":348667,\"volume\":1960110483,\"vwap\":13328.0021,\"lastSize\":12,\"turnover\":14707378028352,\"homeNotional\":147073.7802835203,\"foreignNotional\":1960110483},{\"timestamp\":\"2020-10-29T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":13642.5,\"high\":13854.5,\"low\":12905.5,\"close\":13268,\"trades\":441363,\"volume\":2590188154,\"vwap\":13376.137,\"lastSize\":3017,\"turnover\":19365609030647,\"homeNotional\":193656.09030647,\"foreignNotional\":2590188154},{\"timestamp\":\"2020-10-28T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":13069,\"high\":13801.5,\"low\":13060,\"close\":13642.5,\"trades\":385202,\"volume\":2128904272,\"vwap\":13460.7619,\"lastSize\":1,\"turnover\":15817211614755,\"homeNotional\":158172.11614755122,\"foreignNotional\":2128904272},{\"timestamp\":\"2020-10-27T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":13038,\"high\":13270,\"low\":12776,\"close\":13069,\"trades\":303010,\"volume\":1610805969,\"vwap\":13036.11,\"lastSize\":100,\"turnover\":12357182058067,\"homeNotional\":123571.82058066997,\"foreignNotional\":1610805969},{\"timestamp\":\"2020-10-26T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":13129.5,\"high\":13377,\"low\":12855,\"close\":13038,\"trades\":230313,\"volume\":1196467410,\"vwap\":13082.1559,\"lastSize\":12,\"turnover\":9146821068639,\"homeNotional\":91468.2106863914,\"foreignNotional\":1196467410},{\"timestamp\":\"2020-10-25T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":12941,\"high\":13186,\"low\":12888,\"close\":13129.5,\"trades\":208678,\"volume\":903314606,\"vwap\":13051.4226,\"lastSize\":10053,\"turnover\":6921428839050,\"homeNotional\":69214.2883905001,\"foreignNotional\":903314606},{\"timestamp\":\"2020-10-24T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":12990.5,\"high\":13042.5,\"low\":12727.5,\"close\":12941,\"trades\":236503,\"volume\":1149209117,\"vwap\":12914.8909,\"lastSize\":10,\"turnover\":8899140484542,\"homeNotional\":88991.40484542045,\"foreignNotional\":1149209117},{\"timestamp\":\"2020-10-23T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":12814,\"high\":13219,\"low\":12665.5,\"close\":12990.5,\"trades\":375741,\"volume\":1976997877,\"vwap\":12948.3361,\"lastSize\":45034,\"turnover\":15269221233527,\"homeNotional\":152692.21233526978,\"foreignNotional\":1976997877},{\"timestamp\":\"2020-10-22T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":11914.5,\"high\":13239,\"low\":11895,\"close\":12814,\"trades\":555498,\"volume\":3417940359,\"vwap\":12578.6164,\"lastSize\":10000,\"turnover\":27173127296862,\"homeNotional\":271731.2729686206,\"foreignNotional\":3417940359},{\"timestamp\":\"2020-10-21T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":11752,\"high\":12059,\"low\":11675.5,\"close\":11914.5,\"trades\":325667,\"volume\":1654398028,\"vwap\":11869.4362,\"lastSize\":500,\"turnover\":13939581943518,\"homeNotional\":139395.81943518063,\"foreignNotional\":1654398028},{\"timestamp\":\"2020-10-20T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":11507,\"high\":11835,\"low\":11417,\"close\":11752,\"trades\":240326,\"volume\":1207957271,\"vwap\":11631.9646,\"lastSize\":59,\"turnover\":10385928412662,\"homeNotional\":103859.28412661995,\"foreignNotional\":1207957271},{\"timestamp\":\"2020-10-19T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":11364,\"high\":11509,\"low\":11347,\"close\":11507,\"trades\":115645,\"volume\":515252202,\"vwap\":11431.1843,\"lastSize\":500,\"turnover\":4507687821557,\"homeNotional\":45076.87821557011,\"foreignNotional\":515252202},{\"timestamp\":\"2020-10-18T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":11320,\"high\":11413.5,\"low\":11221.5,\"close\":11364,\"trades\":119530,\"volume\":525201699,\"vwap\":11335.2981,\"lastSize\":252,\"turnover\":4633400242588,\"homeNotional\":46334.00242588038,\"foreignNotional\":525201699},{\"timestamp\":\"2020-10-17T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":11510,\"high\":11549,\"low\":11212,\"close\":11320,\"trades\":221080,\"volume\":1084343083,\"vwap\":11332.7289,\"lastSize\":10000,\"turnover\":9568554353265,\"homeNotional\":95685.5435326514,\"foreignNotional\":1084343083},{\"timestamp\":\"2020-10-16T00:00:00.000Z\",\"symbol\":\"XBTUSD\",\"open\":11416.5,\"high\":11645,\"low\":11271.5,\"close\":11510,\"trades\":213864,\"volume\":1057990261,\"vwap\":11423.3493,\"lastSize\":1000,\"turnover\":9261720071787,\"homeNotional\":92617.20071787135,\"foreignNotional\":1057990261}]";
    }
    public ArrayList<DataVO> make_candle()
    {
        String result = getData();
        Log.d("Point", "123" + result);
        ArrayList<DataVO> dataVOS = new ArrayList<>();
        try
        {
            JSONArray jsonArray = new JSONArray(result);
            float high, low, open, close;
            String date;
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject tmp = jsonArray.getJSONObject(i);
                date = tmp.getString("timestamp");
                high = (float) tmp.getDouble("high");
                low = (float) tmp.getDouble("low");
                open = (float) tmp.getDouble("open");
                close = (float) tmp.getDouble("close");
                DataVO vo = new DataVO(date, "XBT", open, high, low, close);
                dataVOS.add(vo);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return dataVOS;
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
//        String expires = String.valueOf(System.currentTimeMillis()+100);
//        String message = params[0] + params[1] + expires + params[2];
//        String signature = make_signature(message);
        try
        {
            URL url = new URL("https://www.bitmex.com" + params[1]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(params[0]);
//            con.setRequestProperty("api-expires", expires);
//            con.setRequestProperty("api-key", this.APIKey);
//            con.setRequestProperty("api-signature", signature);
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
