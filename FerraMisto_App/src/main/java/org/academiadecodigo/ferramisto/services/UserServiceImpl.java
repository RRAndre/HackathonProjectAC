package org.academiadecodigo.ferramisto.services;

import org.academiadecodigo.ferramisto.exceptions.UserNotFoundException;
import org.academiadecodigo.ferramisto.persistence.dao.SupportersDao;
import org.academiadecodigo.ferramisto.persistence.dao.UserDao;
import org.academiadecodigo.ferramisto.persistence.models.Supporters;
import org.academiadecodigo.ferramisto.persistence.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserDao userDao;
    private SupportersDao supportersDao;
    @Autowired
    public void setSupportersDao(SupportersDao supportersDao) {
        this.supportersDao = supportersDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;

    }
    @Transactional
    @Override
    public User get(Integer id) {
        return userDao.findById(id);
    }

    @Transactional
    @Override
    public User save(User user) {
       return userDao.saveOrUpdate(user);
    }
    @Transactional
    @Override
    public void delete(Integer id) {
        userDao.delete(id);

    }

    @Override
    public List<User> list() {
        return userDao.findAll();
    }

    @Transactional
    @Override
    public Supporters addSupporter(Integer id, Supporters supporter) throws UserNotFoundException{
        User user = Optional.ofNullable(userDao.findById(id))
                .orElseThrow(UserNotFoundException::new);
        user.addSupporter(supporter);
        userDao.saveOrUpdate(user);
    return user.getSupportersList().get(user.getSupportersList().size() -1);
    }
}
