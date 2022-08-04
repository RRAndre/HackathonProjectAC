package org.academiadecodigo.ferramisto.persistence.dao;

import org.academiadecodigo.ferramisto.persistence.models.User;
import org.springframework.stereotype.Repository;

@Repository
public class JpaUserDao extends GenericDao<User> implements UserDao {

    public JpaUserDao() {
        super(User.class);
    }

}

