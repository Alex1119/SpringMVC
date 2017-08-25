package Controller;

import Dao.UserRepository;
import Entity.UserDetailEntity;
import Model.ViewModel.Response;
import Model.ViewModel.DTO_Input_Register;
import Model.ViewModel.DTO_Output_UserDetail;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public Response<DTO_Output_UserDetail> GetUserDetail(@ApiParam(value = "参数名") @RequestBody String viewModel){
        UserDetailEntity entity = _UserRepository.GetUserDetail(viewModel);
        DTO_Output_UserDetail result = DTO_Output_UserDetail.ConvertFrom(_Mapper, entity);
        return ResponseData(result);
    }

}
