package org.academiadecodigo.ferramisto.persistence.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends AbstractModel {

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



    @OneToOne
    private Letter letter;

    public void addSupporter(Supporters supporters){
        supportersList.add(supporters);
        supporters.setUser(this);
    }
    public void removeSupporter(Supporters supporters){
        supportersList.remove(supporters);
        supporters.setUser(null);
    }

    public List<Supporters> getSupportersList() {
        return supportersList;
    }

    public void setSupportersList(List<Supporters> supportersList) {
        this.supportersList = supportersList;
    }

    public Letter getLetter() {
        return letter;
    }

    public void setLetter(Letter letter) {
        this.letter = letter;
    }

    @Override
    public String toString() {
        return "User{" +
                "supportersList=" + supportersList +
                '}';
    }
}
