package com.example.genesis.storage;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Data
@Table(name = "user")
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(length = 50, nullable = false, unique = true)
    private String personId;
    @JdbcTypeCode(Types.VARCHAR)
    @Column(nullable = false, unique = true)
    private UUID uuid;
}
