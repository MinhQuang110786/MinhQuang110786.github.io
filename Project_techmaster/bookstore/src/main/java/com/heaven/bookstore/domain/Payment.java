package com.heaven.bookstore.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private String cardName;
    private String cardNumber;
    private int expireMonth;
    private int expireYear;
    private int cvc;
    private String holderName;

    @OneToOne
    private Order order;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "userPayment")
    private UserBilling userBilling;
}
