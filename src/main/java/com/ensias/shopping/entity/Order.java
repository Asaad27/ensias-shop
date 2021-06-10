package com.ensias.shopping.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "`order`")
public class Order implements Serializable {
    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @Column
    private Double total;
    /**
     * Statut de la commande 1 : Non payée 2 : En attente de livraison 3 : En attente de réception 4 : Commande terminée
     */
    @Column
    private Integer state;

    @Column
    private Date orderTime;

    @Column(name = "`name`")
    private String name;

    @Column
    private String phone;

    @Column
    private String addr;

    @Column
    private Integer userId;

    public Order(Integer id, Double total, Integer state, Date orderTime, String name, String phone, String addr, Integer userId) {
        this.id = id;
        this.total = total;
        this.state = state;
        this.orderTime = orderTime;
        this.name = name;
        this.phone = phone;
        this.addr = addr;
        this.userId = userId;
    }

    public Order() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Order other = (Order) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTotal() == null ? other.getTotal() == null : this.getTotal().equals(other.getTotal()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getOrderTime() == null ? other.getOrderTime() == null : this.getOrderTime().equals(other.getOrderTime()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getAddr() == null ? other.getAddr() == null : this.getAddr().equals(other.getAddr()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()));
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", total=").append(total);
        sb.append(", state=").append(state);
        sb.append(", orderTime=").append(orderTime);
        sb.append(", name=").append(name);
        sb.append(", phone=").append(phone);
        sb.append(", addr=").append(addr);
        sb.append(", userId=").append(userId);
        sb.append("]");
        return sb.toString();
    }
}