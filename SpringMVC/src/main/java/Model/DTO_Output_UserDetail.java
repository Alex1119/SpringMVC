package Model;

import Entity.UserDetailEntity;
import org.dozer.Mapper;
import org.jetbrains.annotations.Contract;

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

    @Contract("_, null -> null")
    public static DTO_Output_UserDetail ConvertFrom(Mapper Mapper, UserDetailEntity DataModel){
        return DataModel == null? null :Mapper.map(DataModel, DTO_Output_UserDetail.class);
    }

}
