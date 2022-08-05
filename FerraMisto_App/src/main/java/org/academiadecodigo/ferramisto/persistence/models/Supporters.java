package org.academiadecodigo.ferramisto.persistence.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Supporters")
public class Supporters extends AbstractModel {

    @ManyToOne //TODO os nossos users é que são many to one supporter. Esta anotação deveria ser OneToMany
    private User user;



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
