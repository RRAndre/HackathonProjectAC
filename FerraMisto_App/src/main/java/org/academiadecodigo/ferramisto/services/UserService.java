package org.academiadecodigo.ferramisto.services;

import org.academiadecodigo.ferramisto.exceptions.UserNotFoundException;
import org.academiadecodigo.ferramisto.persistence.models.Supporters;
import org.academiadecodigo.ferramisto.persistence.models.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {

    User get(Integer id);

    User save(User user);

    void delete(Integer id);

    List<User> list();

    @Transactional
    Supporters addSupporter(Integer id, Supporters supporter) throws UserNotFoundException;
}
