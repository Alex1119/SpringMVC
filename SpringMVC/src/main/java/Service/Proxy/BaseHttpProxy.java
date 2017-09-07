package Service.Proxy;

import Model.Response;
import Utils.GsonUtil;
import Utils.HttpClientUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class BaseHttpProxy {

//    @Autowired
//    private CloseableHttpClient httpClient;

    @Autowired
    private RequestConfig requestConfig;

    public <T> Response<T> DoPost(String url, String paramString, Type type){
        Response<T> result = null;
        Map<String, String> headerParams =  new HashMap(){{
            put("Content-Type","application/json; charset=UTF-8" );
            put("Accept","application/json" );
            put("Authorization", CreateSign(paramString) );
        }};
        String postResult = httpPost(url, paramString, headerParams);
        try{
            result = GsonUtil.fromJson(postResult, type);
        }catch(Exception ex){
            //TODO: JSON反序列化异常
        }
        return result;
    }

    private String CreateSign(String paramString){
        return "Sign";
    }

    public String httpPost(String url, String paramsJson, Map<String, String> headMap) {
        String responseContent = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            setPostHead(httpPost, headMap);
            setPostParams(httpPost, paramsJson);
            httpPost.setConfig(requestConfig);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                System.out.println(response.getStatusLine());
                HttpEntity entity = response.getEntity();
                responseContent = getRespString(entity);
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }

    public void setPostHead(HttpPost httpPost, Map<String, String> headMap) {
        if (headMap != null && headMap.size() > 0) {
            Set<String> keySet = headMap.keySet();
            for (String key : keySet) {
                httpPost.addHeader(key, headMap.get(key));
            }
        }
    }

    private void setGetHead(HttpGet httpGet, Map<String, String> headMap) {
        if (headMap != null && headMap.size() > 0) {
            Set<String> keySet = headMap.keySet();
            for (String key : keySet) {
                httpGet.addHeader(key, headMap.get(key));
            }
        }
    }

    public void setPostParams(HttpPost httpPost, String paramsJson)
            throws Exception {
        HttpEntity entity = new StringEntity(paramsJson, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
    }

    public String getRespString(HttpEntity entity) throws Exception {
        if (entity == null) {
            return null;
        }
        InputStream is = entity.getContent();
        StringBuffer strBuf = new StringBuffer();
        byte[] buffer = new byte[4096];
        int r = 0;
        while ((r = is.read(buffer)) > 0) {
            strBuf.append(new String(buffer, 0, r, HTTP.UTF_8));
        }
        return strBuf.toString();
    }

}
