package Service;

import Entity.SqlServer.HealthTraceEntity;
import Model.Response;
import Utils.GsonUtil;
import com.google.common.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;

@Service
public class HealthTraceProxy extends BaseHttpProxy{

    public List<HealthTraceEntity> GetHealthTraceList(){
        Map params = new TreeMap<String, Object>();
        params.put("AccountId", "26d60997-f9b5-42fb-b36d-8a44a430ac8a");
        String paramsString = GsonUtil.toJson(params);
        Type type = new TypeToken<Response<ArrayList<HealthTraceEntity>>>(){}.getType();
        Response<List<HealthTraceEntity>> result = DoPost("http://hzd6tx24d2/ZSTJ/api/V3/HealthTrace/HealthTraceList", paramsString, type);
        return result.Data;
    }

}
