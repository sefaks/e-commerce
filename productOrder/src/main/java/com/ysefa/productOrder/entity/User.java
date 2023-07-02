package com.ysefa.productOrder.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
@Getter
@Setter
public class User {

    @Id
    @SequenceGenerator(name = "s_id", allocationSize = 1)
    @GeneratedValue(generator = "s_id", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

}
