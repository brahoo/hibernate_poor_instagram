package pl.edu.agh.mwo.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class Main {

    Session session;

    public static void main(String[] args) {
        Main main = new Main();

        main.addDemoStructureUser1();
        main.addDemoStructureUser2();
        main.addDemoStructureUser3();

        //main.deleteUser(1);
        //main.deleteAlbum(2);
        //main.deletePhoto(1);

        //main.printAlbums()
        //main.printPhotos();

        main.close();
    }

    public Main() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public void close() {
        session.close();
        HibernateUtil.shutdown();
    }

    public void addDemoStructureUser1() {

        User user1 = new User();
        user1.setUsername("Piotrek");
        user1.setJoinDate("2022-02-08");

        Album album1 = new Album();
        album1.setName("Wakacje");
        album1.setDescription("Lipiec nad morzem");

        Album album2 = new Album();
        album2.setName("Narty");
        album2.setDescription("Białka w styczniu");

        Photo photo1 = new Photo();
        photo1.setName("duże fale");
        photo1.setDate("2021-07-22");

        Photo photo2 = new Photo();
        photo2.setName("mewy");
        photo2.setDate("2021-07-22");

        Photo photo3 = new Photo();
        photo3.setName("góry");
        photo3.setDate("2022-01-10");

        user1.addAlbum(album1);
        album1.addPhoto(photo1);
        album1.addPhoto(photo2);

        user1.addAlbum(album2);
        album2.addPhoto(photo3);


        Transaction transaction = session.beginTransaction();
        session.save(user1);
        transaction.commit();
    }

    public void addDemoStructureUser2() {

        User user2 = new User();
        user2.setUsername("Basia");
        user2.setJoinDate("2022-02-08");

        Album album3 = new Album();
        album3.setName("Beskid Wyspowy");
        album3.setDescription("Wycieczki w Beskid Wyspowy");

        Photo photo4 = new Photo();
        photo4.setName("widok na szczyt");
        photo4.setDate("2021-10-19");

        user2.addAlbum(album3);
        album3.addPhoto(photo4);


        Transaction transaction = session.beginTransaction();
        session.save(user2);
        transaction.commit();
    }

    public void addDemoStructureUser3() {

        User user3 = new User();
        user3.setUsername("Bartek");
        user3.setJoinDate("2022-02-12");

        Album album4 = new Album();
        album4.setName("Chorwacja");
        album4.setDescription("Dubrownik i pozostałe");

        Photo photo5 = new Photo();
        photo5.setName("rejs");
        photo5.setDate("2021-06-22");

        user3.addAlbum(album4);
        album4.addPhoto(photo5);


        Transaction transaction = session.beginTransaction();
        session.save(user3);
        transaction.commit();
    }

    public void deleteUser(long id) {
        String hql = "from User where id = :id";
        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("id", id);
        User foundUser = query.uniqueResult();

        Transaction transaction = session.beginTransaction();
        session.delete(foundUser);
        transaction.commit();
    }

    public void deleteAlbum(long id) {
        String hql = "from Album where id = :id";
        Query<Album> query = session.createQuery(hql, Album.class);
        query.setParameter("id", id);
        Album foundAlbum = query.uniqueResult();

        Transaction transaction = session.beginTransaction();
        session.delete(foundAlbum);
        transaction.commit();
    }

    public void deletePhoto(long id) {
        String hql = "from Photo where id = :id";
        Query<Photo> query = session.createQuery(hql, Photo.class);
        query.setParameter("id", id);
        Photo foundPhoto = query.uniqueResult();

        Transaction transaction = session.beginTransaction();
        session.delete(foundPhoto);
        transaction.commit();
    }

    public void printPhotos() {
        String hql = "from Photo";

        org.hibernate.query.Query<Photo> query = session.createQuery(hql, Photo.class);
        List<Photo> results = query.list();
        System.out.println(results);
    }

    public void printAlbums() {
        String hql = "from Album";

        org.hibernate.query.Query<Album> query = session.createQuery(hql, Album.class);
        List<Album> results = query.list();
        System.out.println(results);
    }
}
