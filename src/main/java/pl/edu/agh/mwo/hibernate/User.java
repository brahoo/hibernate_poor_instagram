package pl.edu.agh.mwo.hibernate;

import java.util.Set;

public class User {

    private long id;
    private String username;
    private String email;
    private Set <Album> albums;

    public User() {

    }

    public User(long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.albums = new HashSet<Album>();
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
