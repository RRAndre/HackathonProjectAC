package org.academiadecodigo.ferramisto.model;

import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends AbstractModel{
    @OneToMany(
            cascade = {CascadeType.ALL},

            // make sure to remove accounts if unlinked from customer
            orphanRemoval = true,

            // user customer foreign key on account table to establish
            // the many-to-one relationship instead of a join table
            mappedBy = "user",

            // fetch accounts from database together with user
            fetch = FetchType.EAGER
    )
    private List<Supporters> supportersList = new ArrayList<>();

    public void addSupporter(Supporters supporters){
        supportersList.add(supporters);
        supporters.setUser(this);
    }
    public void removeSupporter(Supporters supporters){
        supportersList.remove(supporters);
        supporters.setUser(null);
    }

    @Override
    public String toString() {
        return "User{" +
                "supportersList=" + supportersList +
                '}';
    }
}
