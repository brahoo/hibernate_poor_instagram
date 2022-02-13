package pl.edu.agh.mwo.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class Main {

    Session session;

    public static void main(String[] args) {
        Main main = new Main();

//        main.deleteDemoStructure();
//        main.addDemoStructure();
//
//        main.likePhoto(1,1);
//        main.likePhoto(1,4);
//        main.likePhoto(1,5);
//        main.likePhoto(2,2);
//        main.likePhoto(2,1);
//        main.likePhoto(2,5);

        main.makeFriendship(1,2);

//        main.demoDeletePhoto();

//        main.unlikePhoto(1,1);
//        main.deletePhoto(5);
//        main.deleteAlbum(1);
//        main.deleteUser(1);

        main.close();
    }

    public Main() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public void close() {
        session.close();
        HibernateUtil.shutdown();
    }

    public void addDemoStructure() {
        User user1 = new User("Piotrek", "2022-02-08");

        Album album1 = new Album("Wakacje", "Lipiec nad morzem");
        user1.addAlbum(album1);

        Photo photo1 = new Photo("duże fale", "2021-07-22");
        album1.addPhoto(photo1);
        Photo photo2 = new Photo("mewy", "2021-07-22");
        album1.addPhoto(photo2);

        Album album2 = new Album("Narty", "Białka w styczniu");
        user1.addAlbum(album2);

        Photo photo3 = new Photo("góry", "2022-01-10");
        album2.addPhoto(photo3);


        User user2 = new User("Basia", "2022-02-08");

        Album album3 = new Album("Beskid Wyspowy", "Wycieczki w Beskid Wyspowy");
        user2.addAlbum(album3);

        Photo photo4 = new Photo("widok na szczyt", "2021-10-19");
        album3.addPhoto(photo4);


        User user3 = new User("Bartek", "2022-02-12");

        Album album4 = new Album("Chorwacja", "Dubrownik i pozostałe");
        user3.addAlbum(album4);

        Photo photo5 = new Photo("rejs", "2021-06-22");
        album4.addPhoto(photo5);


        User user4 = new User("Krzysiek", "1980-09-07");


        Transaction transaction = session.beginTransaction();
        session.save(user1);
        session.save(user2);
        session.save(user3);
        session.save(user4);
        transaction.commit();
    }

    public void deleteDemoStructure() {
        Query<User> query = session.createQuery("from User", User.class);
        List<User> users = query.list();

        Transaction transaction = session.beginTransaction();
        for (User user : users) {
            session.delete(user);
        }
        transaction.commit();
    }

    public void deleteUser(long id) {
        User user = getUser(id);

        Transaction transaction = session.beginTransaction();
        for (Photo photo : user.getLikedPhotos()) {
            photo.removeLikingUser(user);
            session.save(photo);
        }
        session.delete(user);
        transaction.commit();
    }

    public void deleteAlbum(long id) {
        Album album = getAlbum(id);
        User user = getUserByAlbum(id);

        user.removeAlbum(album);

        Transaction transaction = session.beginTransaction();
        session.delete(album);
        transaction.commit();
    }

    public void deletePhoto(long id) {
        Photo photo = getPhoto(id);
        Album album = getAlbumByPhoto(id);

        album.removePhoto(photo);

        Transaction transaction = session.beginTransaction();
        session.delete(photo);
        transaction.commit();
    }

//    public void printPhotos() {
//        String hql = "from Photo";
//
//        org.hibernate.query.Query<Photo> query = session.createQuery(hql, Photo.class);
//        List<Photo> results = query.list();
//        System.out.println(results);
//    }
//
//    public void printAlbums() {
//        String hql = "from Album";
//
//        org.hibernate.query.Query<Album> query = session.createQuery(hql, Album.class);
//        List<Album> results = query.list();
//        System.out.println(results);
//    }

    public List<User> getDemoStructure() {
        Query<User> query = session.createQuery("from User", User.class);
        return query.list();
    }

    public User getUser(long id) {
        String hql = "from User where id = :id";
        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    public Album getAlbum(long id) {
        String hql = "from Album where id = :id";
        Query<Album> query = session.createQuery(hql, Album.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    public Photo getPhoto(long id) {
        String hql = "from Photo where id = :id";
        Query<Photo> query = session.createQuery(hql, Photo.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    public void makeFriendship(long userId, long friendId) {
        User user = getUser(userId);
        User friend = getUser(friendId);

        user.addFriend(friend);
        friend.addFriend(user);

        Transaction transaction = session.beginTransaction();
        session.save(user);
        session.save(friend);
        transaction.commit();
    }

    public void likePhoto(long userId, long photoId) {
        User user = getUser(userId);
        Photo photo = getPhoto(photoId);

        user.addLikedPhoto(photo);
        photo.addLikingUser(user);

        Transaction transaction = session.beginTransaction();
        session.save(photo);
        transaction.commit();
    }

    public void unlikePhoto(long userId, long photoId) {
        User user = getUser(userId);
        Photo photo = getPhoto(photoId);

        user.removeLikedPhoto(photo);
        photo.removeLikingUser(user);

        Transaction transaction = session.beginTransaction();
        session.save(photo);
        transaction.commit();
    }

    public User getUserByAlbum(long id) {
        String hql = "select user from User as user inner join user.albums as album where album.id = :id";
        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    public Album getAlbumByPhoto(long id) {
        String hql = "select album from Album as album inner join album.photos as photo where photo.id = :id";
        Query<Album> query = session.createQuery(hql, Album.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    public void demoDeletePhoto() {
        Album album = getAlbum(1);
        Photo photo = null;

        for (Photo somePhoto : album.getPhotos()) {
            System.out.println(somePhoto.getName());
            if (somePhoto.getName().equals("mewy")) {photo=somePhoto;};
        }

        System.out.println(photo.getName());

        //album.removePhoto(photo);
        deletePhoto(photo.getId());


        System.out.println(album.getPhotos());
    }

}
