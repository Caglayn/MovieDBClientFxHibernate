package controller;

import model.UserEntity;
import org.hibernate.Session;
import utils.HibernateUtil;
import utils.LogUtil;

import java.util.ArrayList;

public class UserEntityController implements Controllable<UserEntity>{
    @Override
    public void delete(UserEntity entity) {
        // Do nothing for now
    }

    @Override
    public void update(UserEntity entity) {
        // Do nothing for now
    }

    @Override
    public ArrayList<UserEntity> list() {
        // Do nothing for now
        return null;
    }

    @Override
    public UserEntity find(long id) {
        // Do nothing for now
        return null;
    }

    public void insertUsersToDB(int count){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        for (int i = 1; i<=count; i++){
            session.save(new UserEntity(i));
        }
        session.getTransaction().commit();
        LogUtil.getInstance().logInfo("Ekleme tamamlandi : >> " + this.getClass());
    }
}
