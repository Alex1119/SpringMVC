package Entity.SqlServer;

import javax.persistence.*;

@Entity
@Table(name = "UserDetail", schema = "dbo", catalog = "CodeFirstDB")
public class UserDetailEntity {
    private int Id;
    private String UserId;
    private String UserName;

    public UserDetailEntity(){}

    public UserDetailEntity(String UserId, String UserName){
        this.UserId = UserId;
        this.UserName = UserName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    @Basic
    @Column(name = "UserID", nullable = true, length = 50)
    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        this.UserId = userId;
    }

    @Basic
    @Column(name = "UserName", nullable = true, length = 50)
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        this.UserName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDetailEntity entity = (UserDetailEntity) o;

        if (Id != entity.Id) return false;
        if (UserId != null ? !UserId.equals(entity.UserId) : entity.UserId != null) return false;
        if (UserName != null ? !UserName.equals(entity.UserName) : entity.UserName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Id;
        result = 31 * result + (UserId != null ? UserId.hashCode() : 0);
        result = 31 * result + (UserName != null ? UserName.hashCode() : 0);
        return result;
    }
}
