package Dao.SqlServer;

import Entity.SqlServer.UserDetailEntity;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.function.Predicate;


@Transactional
@Repository@Scope("request")
public class UserRepository extends BaseRepository{

    @Autowired
    public SessionFactory _SessionFactory;

    @Override
    public SessionFactory getSessionFactory() {
        return _SessionFactory;
    }

    public boolean AddUserDetailEntity(String UserId, String UserName){
        try{
            _SessionFactory.getCurrentSession().save(new UserDetailEntity(UserId, UserName));
        }catch(Exception ex){
            System.out.println(ex);
            return false;
        }
        return true;
    }

    public UserDetailEntity GetUserDetailEntity(int UserID){
        UserDetailEntity userDetailEntity = (UserDetailEntity)_SessionFactory.getCurrentSession()
                .get(UserDetailEntity.class, UserID);
        return userDetailEntity;
    }

    public UserDetailEntity GetUserDetail(String UserID){
        UserDetailEntity entity = null;
        try{
            entity= (UserDetailEntity)_SessionFactory.getCurrentSession()
                    .createCriteria(UserDetailEntity.class)
                    .add(Restrictions.eq("userId", UserID))
                    .addOrder(Order.desc("id"))
                    .setMaxResults(1).uniqueResult();
        }catch(Exception ex){
            System.out.println(ex);
        }
        return entity;
    }

    public static void filter(List names, Predicate condition) {
        names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
            System.out.println(name + " ");
        });
    }

}
