package Service;

import Entity.HealthTraceEntity;
import Entity.HealthTraceList;
import Entity.UserDetailEntity;
import Model.Response;
import Utils.GsonUtil;
import Utils.HttpClientUtil;
import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

@Service
public class ThirdPartService{

    public List<HealthTraceEntity> GetHealthTraceList(){
        Map params = new TreeMap<String, Object>();
        params.put("AccountId", "26d60997-f9b5-42fb-b36d-8a44a430ac8a");
        String paramsString = GsonUtil.toJson(params);
        Type type = new TypeToken<Response<ArrayList<HealthTraceEntity>>>(){}.getRawType();
        Response<List<HealthTraceEntity>> result = DoPost("http://hzd6tx24d2/ZSTJ/api/V3/HealthTrace/HealthTraceList", paramsString, type);
        return result.Data;
    }

    private <T> Response<T> DoPost(String url, String paramString, Type type){
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
