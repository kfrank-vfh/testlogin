package testlogin.mad.vfh.kfrank.de.testlogin.model;

import java.io.Serializable;

/**
 * Created by kfrank on 03.06.2017.
 */

public class TodoItem implements Serializable {

    private long id;

    private String name;

    private String description;

    private boolean done;

    private boolean favourite;

    private long dueDate;

    public TodoItem() {
    }

    public TodoItem(long id, String name, String description, boolean done, boolean favourite, long dueDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.done = done;
        this.favourite = favourite;
        this.dueDate = dueDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }
}
