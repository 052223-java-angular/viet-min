package com.revature.app.services;

import com.revature.app.models.Product;
import com.revature.app.models.Session;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReviewService {
    private final RoleService roleService;
    private Product product;
    private Session session;
}
