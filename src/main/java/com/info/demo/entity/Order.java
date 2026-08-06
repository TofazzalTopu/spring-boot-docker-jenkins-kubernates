package com.info.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRODUCT_ID")
    private String productID;

    @Column(name = "QUANTITY")
    private int quantity;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}