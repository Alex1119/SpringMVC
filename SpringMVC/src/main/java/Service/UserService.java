package Service;

import Dao.SqlServer.UserRepository;
import Entity.SqlServer.UserDetailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service@Scope("request")
public class UserService {

    @Autowired
    UserRepository _UserRepository;

    public boolean AddUserDetailEntity(String UserId, String UserName){
       return _UserRepository.AddUserDetailEntity(UserId, UserName);
    }

    public UserDetailEntity GetUserDetail(String UserID){
      return _UserRepository.GetUserDetail(UserID);
    }

}
