package org.avsoto.junit.model;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.*;
import org.avsoto.junit.exceptions.InsufficientBalanceException;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Account {

    private Integer id;
    private String accountNumber = UUID.randomUUID().toString();
    private String firstName;
    private String lastName;
    private BigDecimal balance;
    private Bank bank;

    @Override
    public boolean equals(Object obj) {
        Account a = (Account) obj;

        if(obj == null){
            return false;
        }

        if(this.firstName == null || this.lastName == null || this.balance == null){
            return false;
        }

        return this.firstName.equals(a.getFirstName()) && this.lastName.equals(a.getLastName()) && this.balance.equals(a.getBalance());
    }

    public void debit(BigDecimal amount){
        BigDecimal newBalance = this.balance.subtract(amount);
        if(newBalance.compareTo(BigDecimal.ZERO) < 0){
            throw new InsufficientBalanceException("Insufficient Balance.");
        }
        this.balance = newBalance;
    }

    public void credit(BigDecimal amount){
        this.balance = this.balance.add(amount);
    }
}
