package com.pasha.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class MatchMode implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    public MatchMode() {
    }

    public MatchMode(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
