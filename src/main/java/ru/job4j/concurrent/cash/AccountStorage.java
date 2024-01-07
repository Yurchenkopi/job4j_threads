package ru.job4j.concurrent.cash;

import java.util.HashMap;
import java.util.Optional;

public class AccountStorage {

    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        accounts.put(account.id(), account);
        return accounts.containsKey(account.id());
    }

    public synchronized boolean update(Account account) {
        accounts.computeIfPresent(account.id(), (k, v) -> account);
        return accounts.get(account.id()).amount() == account.amount();
    }

    public synchronized void delete(int id) {
        if (accounts.containsKey(id)) {
            accounts.put(id, null);
            accounts.remove(id);
        }
    }

    public synchronized Optional<Account> getById(int id) {
        return accounts.values().stream()
                .filter(a -> a.id() == id)
                .findFirst();
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean rsl = false;
        if (
                accounts.containsKey(fromId)
                        && accounts.containsKey(toId)
                        && amount <= accounts.get(fromId).amount()
        ) {
            Account fromAcc = accounts.get(fromId);
            Account toAcc = accounts.get(toId);
            int fromAccAmount = fromAcc.amount() - amount;
            int toAccAmount = toAcc.amount() + amount;
            accounts.computeIfPresent(fromId, (k, v) -> new Account(fromId, fromAccAmount));
            accounts.computeIfPresent(toId, (k, v) -> new Account(toId, toAccAmount));
            rsl =
                    fromAcc.amount() - accounts.get(fromId).amount() == amount
                    &&
                    accounts.get(toId).amount() - toAcc.amount() == amount;
        }
        return rsl;
    }
}
