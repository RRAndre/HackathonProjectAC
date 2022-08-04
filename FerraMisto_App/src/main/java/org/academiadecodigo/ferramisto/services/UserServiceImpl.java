package org.academiadecodigo.ferramisto.services;

import org.academiadecodigo.ferramisto.persistence.dao.UserDao;
import org.academiadecodigo.ferramisto.persistence.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{

    private UserDao userDao;


    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;

    }
    @Override
    public User get(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public User save(User user) {
       return userDao.saveOrUpdate(user);
    }

    @Override
    public void delete(Integer id) {
        userDao.delete(id);

    }

    @Override
    public List<User> list() {
        return userDao.findAll();
    }
}
