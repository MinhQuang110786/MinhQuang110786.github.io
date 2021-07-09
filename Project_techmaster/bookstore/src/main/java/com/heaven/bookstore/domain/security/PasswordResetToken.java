package com.heaven.bookstore.domain.security;

import com.heaven.bookstore.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class PasswordResetToken {
    private static final int EXPIRATION = 60*24;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name="user_id")
    private User user;

    private Date expireDate;



    public PasswordResetToken(final String token, User user) {
        this.token = token;
        this.user = user;
        this.expireDate = calculateExpireDate(EXPIRATION);
    }

    private Date calculateExpireDate(final int expiration) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE,expiration);
        return new Date(cal.getTime().getTime());
    }

    public void updateToken(final String token){
        this.token = token;
        this.expireDate = calculateExpireDate(EXPIRATION);
    }

    @Override
    public String toString() {
        return "PasswordResetToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", user=" + user +
                ", expireDate=" + expireDate +
                '}';
    }
}
