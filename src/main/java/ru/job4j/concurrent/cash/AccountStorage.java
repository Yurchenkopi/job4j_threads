package ru.job4j.concurrent.cash;

import java.util.HashMap;
import java.util.Optional;

public class AccountStorage {

    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return account != null && accounts.putIfAbsent(account.id(), account) == null;
    }

    public synchronized boolean update(Account account) {
        return accounts.replace(account.id(), accounts.get(account.id()), account);
    }

    public synchronized void delete(int id) {
        accounts.remove(id);
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean rsl = false;
        if (
                getById(fromId).isPresent() && getById(toId).isPresent()
                        && amount <= accounts.get(fromId).amount()
        ) {
            Account fromAcc = accounts.get(fromId);
            Account toAcc = accounts.get(toId);
            int fromAccAmount = fromAcc.amount() - amount;
            int toAccAmount = toAcc.amount() + amount;
            rsl = update(new Account(fromId, fromAccAmount))
                    && update(new Account(toId, toAccAmount));
        }
        return rsl;
    }
}
