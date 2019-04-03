package org.udg.pds.springtodo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.udg.pds.springtodo.serializer.JsonTagSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;


@Entity(name="usergroup")
public class Group implements Serializable {

    private static final long serialVersionUID = 1L;

    public Group() {
    }

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // This tells JAXB that this field can be used as ID
    // Since XmlID can only be used on Strings, we need to use LongAdapter to transform Long <-> String

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    // Don't forget to use the extra argument "strategy = GenerationType.IDENTITY" to get AUTO_INCREMENT
    @NotNull
    private String name;

    @NotNull
    private String description;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_user")
    private User user;

    @Column(name = "fk_user", insertable = false, updatable = false)
    private long userId;



    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }


}
