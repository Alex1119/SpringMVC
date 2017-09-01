package Controller;

import Common.Authorirze.AuthorizeAnnotation;
import Dao.Mongo.test;
import Entity.SqlServer.HealthTraceEntity;
import Entity.SqlServer.UserDetailEntity;
import Model.Response;
import Model.ViewModel.DTO_Input_Register;
import Model.ViewModel.DTO_Output_UserDetail;
import Service.HealthTraceProxy;
import Service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AuthorizeAnnotation
@Controller
public class MainController extends BaseController{

    @Autowired
    public UserService _UserService;

    @Autowired
    public HealthTraceProxy _ThirdPartService;

    @Autowired
    public Mapper _Mapper;

    @ApiOperation(value = "添加用户信息", notes = "添加用户信息接口的注释")
    @ResponseBody
    @RequestMapping(value = "/Add", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8;" })
    public Response<Boolean> AddUserDetail(@ApiParam(value = "参数名") @RequestBody DTO_Input_Register viewModel){
        boolean result = _UserService.AddUserDetailEntity(viewModel.UserId, viewModel.UserName);
        return ResponseData(result);
    }

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息接口的注释")
    @ResponseBody
    @RequestMapping(value = "/Detail", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8;" })
    public Response<DTO_Output_UserDetail> GetUserDetail(@ApiParam(value = "参数名") @RequestBody String viewModel){
        UserDetailEntity entity = _UserService.GetUserDetail(viewModel);
        DTO_Output_UserDetail result = DTO_Output_UserDetail.ConvertFrom(_Mapper, entity);
        return ResponseData(result);
    }

    @ApiOperation(value = "请求接口获取相关数据", notes = "请求接口获取相关数据的注释")
    @ResponseBody
    @RequestMapping(value = "/HealthTrace", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8;" })
    public Response<List<HealthTraceEntity>> GetHealthTraceList(@ApiParam(value = "参数名") @RequestBody String viewModel){
        List<HealthTraceEntity> result = _ThirdPartService.GetHealthTraceList();
        return ResponseData(result);
    }

}
