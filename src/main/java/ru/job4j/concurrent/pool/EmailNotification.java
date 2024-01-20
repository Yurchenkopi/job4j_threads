package ru.job4j.concurrent.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        pool.submit(() -> {
            String subject = String.format("Notification {%s} to email {%s}.",
                    user.username(), user.email());
            String body = String.format("Add a new event to {%s}",
                    user.username());
            send(subject, body, user.email());
        });
    }

    public void send(String subject, String body, String email) {
        System.out.println(subject);
        System.out.println(body);
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        EmailNotification en = new EmailNotification();
        User u1 = new User("Ivan", "ivan@ivan.com");
        User u2 = new User("Lev", "lev@lev.com");
        User u3 = new User("Boris", "boris@boris.com");
        en.emailTo(u1);
        en.emailTo(u2);
        en.emailTo(u3);
        en.close();
    }
}
