package org.academiadecodigo.ferramisto.services;

import org.academiadecodigo.ferramisto.persistence.dao.SupportersDao;
import org.academiadecodigo.ferramisto.persistence.models.Supporters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class SupporterServiceImpl implements SupporterService {

    private SupportersDao supportersDao;

    @Autowired
    public void setSupportersDao(SupportersDao supportersDao) {
        this.supportersDao = supportersDao;
    }

    @Transactional
    @Override
    public Supporters get(Integer id) {
        return supportersDao.findById(id);
    }

    @Transactional
    @Override
    public Supporters save(Supporters supporter) {
        return supportersDao.saveOrUpdate(supporter);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        supportersDao.delete(id);
    }

    @Override
    public List<Supporters> list() {
        return supportersDao.findAll();
    }
}
