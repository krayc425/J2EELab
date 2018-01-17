package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "`order`")
public class Order implements Serializable {

    @Id
    private int oid;
    private Date ordertime;
    private String ordername;
    private int ordercount;
    private double orderprice;
    private String username;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isoutofstock;

    public int getOid() {
        return oid;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public String getOrdername() {
        return ordername;
    }

    public int getOrdercount() {
        return ordercount;
    }

    public double getOrderprice() {
        return orderprice;
    }

    public String getUsername() {
        return username;
    }

    public boolean isIsoutofstock() {
        return isoutofstock;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return oid == order.oid &&
                ordercount == order.ordercount &&
                Double.compare(order.orderprice, orderprice) == 0 &&
                isoutofstock == order.isoutofstock &&
                Objects.equals(ordertime, order.ordertime) &&
                Objects.equals(ordername, order.ordername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oid, ordertime, ordername, ordercount, orderprice, isoutofstock);
    }

}
