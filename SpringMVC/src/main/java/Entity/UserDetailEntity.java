package Entity;

import javax.persistence.*;

@Entity
@Table(name = "UserDetail", schema = "dbo", catalog = "CodeFirstDB")
public class UserDetailEntity {
    private int id;
    private String userId;
    private String userName;

    public UserDetailEntity(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public UserDetailEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = new Integer(id);
    }

    @Basic
    @Column(name = "UserID", nullable = true, length = 50)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "UserName", nullable = true, length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDetailEntity entity = (UserDetailEntity) o;

        if (id != entity.id) return false;
        if (userId != null ? !userId.equals(entity.userId) : entity.userId != null) return false;
        if (userName != null ? !userName.equals(entity.userName) : entity.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        return result;
    }
}
