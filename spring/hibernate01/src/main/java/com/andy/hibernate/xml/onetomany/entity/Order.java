package com.andy.hibernate.xml.onetomany.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

/**
 * @NAME : bootweb com.andy.hibernate.domain
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/28 八月 14:34
 * @DESC : 订单
 */
@Data
@NoArgsConstructor
public class Order {

    private Integer id;
    private String orderName;
    private String productName;
    private Date createDate;
    private Customer customer;
    public Order(String orderName, String productName) {
        this.orderName = orderName;
        this.productName = productName;
    }
    public Order(String orderName, String productName, Date createDate, Customer customer) {
        this.orderName = orderName;
        this.productName = productName;
        this.createDate = createDate;
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(orderName, order.orderName) &&
                Objects.equals(productName, order.productName) &&
                Objects.equals(createDate, order.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderName, productName, createDate);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderName='" + orderName + '\'' +
                ", productName='" + productName + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
