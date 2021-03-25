package com.sparta.eng80.pressplay.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VisitorEntity {

    private transient String role = "VISITOR_USER";
    private int id = -1;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
