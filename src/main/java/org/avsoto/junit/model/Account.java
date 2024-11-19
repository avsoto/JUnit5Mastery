package org.avsoto.junit.model;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.*;

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

}
