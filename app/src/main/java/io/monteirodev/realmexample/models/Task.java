package io.monteirodev.realmexample.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Task extends RealmObject {

    // Auto creates a primary key and a index
    @PrimaryKey
    private String id;
    private String title;
    private String description;

    // refractor.Encapsulate fields...
    // IN REALM YOU CANNOT OVERRIDE GETTERS AND SETTERS !!!
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
