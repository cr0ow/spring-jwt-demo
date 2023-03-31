package com.quicktodo.persistence.entity;

import com.quicktodo.persistence.domain.TokenType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TOKENS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Token {

    @Id
    @GeneratedValue
    @Column(name = "TOKEN_ID")
    public Long tokenId;

    @Column(name = "TOKEN", unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    @Column(name = "TOKEN_TYPE")
    public TokenType tokenType = TokenType.BEARER;

    @Column(name = "REVOKED")
    public boolean revoked;

    @Column(name = "EXPIRED")
    public boolean expired;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public User user;

}
