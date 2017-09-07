package Service.Proxy;

import Entity.SqlServer.HealthTraceEntity;
import Model.Response;
import Utils.GsonUtil;
import com.google.gson.reflect.TypeToken;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;

@Service@Scope("request")
public class HealthTraceProxy extends BaseHttpProxy{

    @Autowired
    private CloseableHttpClient httpClient;

    public List<HealthTraceEntity> GetHealthTraceList(){
        Map params = new TreeMap<String, Object>();
        params.put("AccountId", "26d60997-f9b5-42fb-b36d-8a44a430ac8a");
        String paramsString = GsonUtil.toJson(params);
        Type type = new TypeToken<Response<ArrayList<HealthTraceEntity>>>(){}.getType();
        Response<List<HealthTraceEntity>> result = DoPost("http://hzd6tx24d2/ZSTJ/api/V3/HealthTrace/HealthTraceList",
                paramsString, type);
        return result.Data;
    }

}
