package Controller;

import Common.Authorirze.AuthorizeAnnotation;
import Dao.UserRepository;
import Entity.UserDetailEntity;
import Model.Response;
import Model.DTO_Input_Register;
import Model.DTO_Output_UserDetail;
import Utils.HttpClientUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@AuthorizeAnnotation
@Controller
public class MainController extends BaseController{

    @Autowired
    public UserRepository _UserRepository;

    @Autowired
    public Mapper _Mapper;

    @ApiOperation(value = "添加用户信息", notes = "添加用户信息接口的注释")
    @ResponseBody
    @RequestMapping(value = "/Add", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8;" })
    public Response<Boolean> AddUserDetail(@ApiParam(value = "参数名") @RequestBody DTO_Input_Register viewModel){
        boolean result = _UserRepository.AddUserDetailEntity(viewModel.UserId, viewModel.UserName);
        return ResponseData(result);
    }

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息接口的注释")
    @ResponseBody
    @RequestMapping(value = "/Detail", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8;" })
    public Response<DTO_Output_UserDetail> GetUserDetail(HttpServletRequest request, HttpServletResponse response,
                                                         @ApiParam(value = "参数名") @RequestBody String viewModel){
        UserDetailEntity entity = _UserRepository.GetUserDetail(viewModel);
        DTO_Output_UserDetail result = DTO_Output_UserDetail.ConvertFrom(_Mapper, entity);

        Map params = new TreeMap<String, Object>();
        params.put("AccountId", "26d60997-f9b5-42fb-b36d-8a44a430ac8a");
        Gson gson = new GsonBuilder().serializeNulls().enableComplexMapKeySerialization().create();

        String postResult = HttpClientUtil.getInstance().httpPost("http://hzd6tx24d2/ZSTJ/api/V3/HealthTrace/HealthTraceList",
                gson.toJson(params),
                new HashMap<String, String>(){{
                    put("Content-Type","application/json; charset=UTF-8" );
                    put("Accept","application/json" );
                    put("Authorization","123" );
                }});
        return ResponseData(result);
    }

}
