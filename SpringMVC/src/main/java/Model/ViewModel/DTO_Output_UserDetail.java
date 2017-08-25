package Model.ViewModel;

import Entity.UserDetailEntity;
import org.dozer.Mapper;

public class DTO_Output_UserDetail{

    public int id;
    public String userId;
    public String userName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static DTO_Output_UserDetail ConvertFrom(Mapper Mapper, UserDetailEntity DataModel){
        return Mapper.map(DataModel, DTO_Output_UserDetail.class);
    }

}
