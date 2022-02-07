package pl.edu.agh.mwo.hibernate;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class User {

    private long id;
    private String username;
    private LocalDate joinDate;
    private Set<Album> albums = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public Set<Album> getAlbums() {
        return this.albums;
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public void removeAlbum(Album album) {
        albums.remove(album);
    }
}
