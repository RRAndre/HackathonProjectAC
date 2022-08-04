package org.academiadecodigo.ferramisto.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Letters")
public class Letter {
    @Id
    private int id;

    private String content;
    @OneToOne
    private User user;


}
