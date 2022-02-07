package pl.edu.agh.mwo.hibernate;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Photo {

    private long id;
    private String name;
    private LocalDate date;
    private Set<User> likerUsers = new HashSet<>();

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<User> getLikerUsers() {
        return likerUsers;
    }

    public void addLikerUser(User user) {
        likerUsers.add(user);
    }

    public void removeLikerUser(User user) {
        likerUsers.remove(user);
    }

}
