package Service;

import Model.Response;
import Utils.GsonUtil;
import Utils.HttpClientUtil;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Service
public abstract class BaseHttpProxy {

    public <T> Response<T> DoPost(String url, String paramString, Type type){
        Response<T> result = null;
        Map<String, String> headerParams =  new HashMap(){{
            put("Content-Type","application/json; charset=UTF-8" );
            put("Accept","application/json" );
            put("Authorization", CreateSign(paramString) );
        }};
        String postResult = HttpClientUtil.getInstance().httpPost(url, paramString, headerParams);
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

}
