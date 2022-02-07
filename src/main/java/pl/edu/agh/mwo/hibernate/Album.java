package pl.edu.agh.mwo.hibernate;

import java.util.HashSet;
import java.util.Set;

public class Album {

    private long id;
    private String name;
    private String description;
    private Set<Photo> photos = new HashSet<>();

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

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void addPhoto(Photo photo) {
        photos.add(photo);
    }

    public void removePhoto(Photo photo) {
        photos.remove(photo);
    }
}
