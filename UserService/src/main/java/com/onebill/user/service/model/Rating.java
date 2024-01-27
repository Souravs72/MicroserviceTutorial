package com.onebill.user.service.model;

import jakarta.persistence.Id;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {

    @Id
    private String ratingId;
    private String userId;
    private String hotelId;
    private Integer rating;
    private String remark;
    private Hotel hotel;
}
