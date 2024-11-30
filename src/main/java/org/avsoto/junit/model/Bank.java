package org.avsoto.junit.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Bank {
    private String name;
    private List<Account> accounts;

    public Bank(){
        accounts = new ArrayList<>();
    }

    public void transfer(Account sourceAccount, Account destinationAccount, BigDecimal amount){
        sourceAccount.debit(amount);
        destinationAccount.credit(amount);
    }

    public void addAccount(Account account){
        accounts.add(account);
        account.setBank(this);
    }
}
