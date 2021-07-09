package com.heaven.bookstore.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class BillingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String BillingAddressName;
    private String BillingAddressStreet1;
    private String BillingAddressStreet2;
    private String BillingAddressCity;
    private String BillingAddressState;
    private String BillingAddressCountry;
    private String BillingAddressZipcode;

    @OneToOne
    private Order order;
}
