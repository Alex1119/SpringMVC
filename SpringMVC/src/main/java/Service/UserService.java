package Service;

import Dao.UserRepository;
import Entity.UserDetailEntity;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository _UserRepository;

    public boolean AddUserDetailEntity(String UserId, String UserName){
        try{
            _UserRepository.getCurrentSession().save(new UserDetailEntity(UserId, UserName));
        }catch(Exception ex){
            System.out.println(ex);
            return false;
        }
        return true;
    }

    public UserDetailEntity GetUserDetail(String UserID){
        UserDetailEntity entity = null;
        try{
            entity= (UserDetailEntity)_UserRepository.getCurrentSession()
                    .createCriteria(UserDetailEntity.class)
                    .add(Restrictions.eq("userId", UserID))
                    .addOrder(Order.desc("id"))
                    .setMaxResults(1).uniqueResult();
        }catch(Exception ex){
            System.out.println(ex);
        }
        return entity;
    }

}
