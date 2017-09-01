package Entity.Mongo;

import com.google.gson.annotations.SerializedName;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="USER_DETAIL")
public class UserDetail {

    @SerializedName("_id")
    private String Id;
    private String ID;
    private String UserId;
    private String UserName;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
}
