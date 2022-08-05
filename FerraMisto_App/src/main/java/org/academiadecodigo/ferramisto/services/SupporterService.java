package org.academiadecodigo.ferramisto.services;

import org.academiadecodigo.ferramisto.persistence.models.Supporters;
import org.academiadecodigo.ferramisto.persistence.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SupporterService {
    Supporters get(Integer id);

    Supporters save(Supporters supporter);

    void delete(Integer id);

    List<Supporters> list();
}
