package com.onebill.user.service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "micro_users")
public class User {

    @Id
    private String userId;
    private String name;
    private String email;
    private String about;

    @Transient //does not stores in database
    private List<Rating> ratings = new ArrayList<>();
}
