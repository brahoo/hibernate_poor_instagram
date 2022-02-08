package pl.edu.agh.mwo.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class Main {

    Session session;

    public static void main(String[] args) {
        Main main = new Main();

        main.addDemoStructure();

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

        User user1 = new User();
        user1.setUsername("Piotrek");
        user1.setJoinDate(LocalDate.parse("2022-02-08"));

        Album album1 = new Album();
        album1.setName("Wakacje");
        album1.setDescription("Lipiec nad morzem");

        Album album2 = new Album();
        album2.setName("Narty");
        album2.setDescription("Białka w styczniu");

        Photo photo1 = new Photo();
        photo1.setName("duże fale");
        photo1.setDate(LocalDate.parse("2021-07-22"));

        Photo photo2 = new Photo();
        photo2.setName("mewy");
        photo2.setDate(LocalDate.parse("2021-07-22"));

        Photo photo3 = new Photo();
        photo3.setName("góry");
        photo3.setDate(LocalDate.parse("2022-01-10"));

        user1.addAlbum(album1);
        album1.addPhoto(photo1);
        album1.addPhoto(photo2);

        user1.addAlbum(album2);
        album2.addPhoto(photo3);


        User user2 = new User();
        user2.setUsername("Basia");
        user2.setJoinDate(LocalDate.parse("2022-02-08"));

        Album album3 = new Album();
        album3.setName("Beskid Wyspowy");
        album3.setDescription("Wycieczki w Beskid Wyspowy");

        Photo photo4 = new Photo();
        photo4.setName("widok na szczyt");
        photo4.setDate(LocalDate.parse("2021-10-19"));

        user2.addAlbum(album3);
        album3.addPhoto(photo4);


        User user3 = new User();
        user3.setUsername("Bartek");
        user3.setJoinDate(LocalDate.parse("2022-02-12"));

        Album album4 = new Album();
        album4.setName("Chorwacja");
        album4.setDescription("Dubrownik i pozostałe");

        Photo photo5 = new Photo();
        photo5.setName("rejs");
        photo5.setDate(LocalDate.parse("2021-06-22"));

        user3.addAlbum(album4);
        album4.addPhoto(photo5);


        Transaction transaction = session.beginTransaction();
        session.save(user1);
        session.save(user2);
        session.save(user3);
        transaction.commit();
    }
}
