package com.heaven.bookstore.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ShippingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ShippingAddressName;
    private String ShippingAddressStreet1;
    private String ShippingAddressStreet2;
    private String ShippingAddressCity;
    private String ShippingAddressState;
    private String ShippingAddressCountry;
    private String ShippingAddressZipcode;

    @OneToOne
    private Order order;

}
