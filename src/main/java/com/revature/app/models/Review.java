package com.revature.app.models;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Review {
    private String id;
    private int rating;
    private String comment;
    private String user_id;
    private String product_id;

    public Review(String user_Id, String prod_id)
    {
        this.id = UUID.randomUUID().toString();
        this.product_id = prod_id;
        this.user_id = user_Id;
    }
}
