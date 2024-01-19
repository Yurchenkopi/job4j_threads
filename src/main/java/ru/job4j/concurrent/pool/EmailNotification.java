package ru.job4j.concurrent.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public final List<String> notifications = new ArrayList<>();

    public void emailTo(User user) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                notifications.add(String.format(
                        "subject = Notification {%s} to email {%s}.%s"
                                + "body = Add a new event to {%s}",
                        user.username(), user.email(), System.lineSeparator(), user.username()
                ));
            }
        });
    }

    public void send(String subject, String body, String email) {

    }

    public void close() {
        pool.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        EmailNotification en = new EmailNotification();
        User u1 = new User("Ivan", "ivan@ivan.com");
        User u2 = new User("Lev", "lev@lev.com");
        User u3 = new User("Boris", "boris@boris.com");
        en.emailTo(u1);
        en.emailTo(u2);
        en.emailTo(u3);
        Thread.sleep(500);
        en.close();
        en.notifications.forEach(System.out::println);
    }
}
