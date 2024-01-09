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
        Optional<Account> fromAcc = getById(fromId);
        Optional<Account> toAcc = getById(toId);
        if (
                fromAcc.isPresent() && toAcc.isPresent()
                        && amount <= accounts.get(fromId).amount()
        ) {

            int fromAccAmount = fromAcc.get().amount() - amount;
            int toAccAmount = toAcc.get().amount() + amount;
            rsl = update(new Account(fromId, fromAccAmount))
                    && update(new Account(toId, toAccAmount));
        }
        return rsl;
    }
}
