package Controller;

import Dao.UserRepository;
import Entity.UserDetailEntity;
import Model.ViewModel.DTO_Input_Register;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController extends BaseController{

    @Autowired
    public UserRepository _UserRepository;

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
    public Response<UserDetailEntity> GetUserDetail(@ApiParam(value = "参数名") @RequestBody String viewModel){
        UserDetailEntity result = _UserRepository.GetUserDetail(viewModel);
        return ResponseData(result);
    }



}
