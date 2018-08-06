package cn.qblank.springdemo.utils;


import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

@Slf4j
public class AuthUtil {
    public static final String APPID = "wx91e723c9f2bd26a9";
    public static final String APPSECRET = "9070cc74bc0fa1996d1b376d877af883";

    public static JSONObject getJson(String url) throws Exception{
        JSONObject jsonObject = null;
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse httpResponse = defaultHttpClient.execute(httpGet);
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null){
            String result = EntityUtils.toString(entity, "UTF-8");
            jsonObject = new JSONObject(result);
        }
        httpGet.releaseConnection();
        return jsonObject;
    }

}
