package org.academiadecodigo.ferramisto.persistence.dao;

import org.academiadecodigo.ferramisto.persistence.models.Supporters;

public class JpaSupportersDao extends GenericDao<Supporters> implements SupportersDao{

    public JpaSupportersDao() {
        super(Supporters.class);
    }
}
