package com.ensias.shopping.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @Column
    private String title;

    @Column
    private Double marketPrice;

    @Column
    private Double shopPrice;

    @Column
    private String image;

    @Column(name = "`desc`", columnDefinition = "text")
    private String desc;

    @Column
    private Integer isHot;

    @Column
    private Integer csid;

    @Column
    private Date pdate;

    @Transient
    private Classification categorySec;

    public Classification getCategorySec() {
        return categorySec;
    }

    public void setCategorySec(Classification categorySec) {
        this.categorySec = categorySec;
    }


    public Product(Integer id, String title, Double marketPrice, Double shopPrice, String image, String desc, Integer isHot, Integer csid, Date pdate) {
        this.id = id;
        this.title = title;
        this.marketPrice = marketPrice;
        this.shopPrice = shopPrice;
        this.image = image;
        this.desc = desc;
        this.isHot = isHot;
        this.csid = csid;
        this.pdate = pdate;
    }

    public Product() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Double getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(Double shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Integer getCsid() {
        return csid;
    }

    public void setCsid(Integer csid) {
        this.csid = csid;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
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
        Product other = (Product) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getMarketPrice() == null ? other.getMarketPrice() == null : this.getMarketPrice().equals(other.getMarketPrice()))
            && (this.getShopPrice() == null ? other.getShopPrice() == null : this.getShopPrice().equals(other.getShopPrice()))
            && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
            && (this.getDesc() == null ? other.getDesc() == null : this.getDesc().equals(other.getDesc()))
            && (this.getIsHot() == null ? other.getIsHot() == null : this.getIsHot().equals(other.getIsHot()))
            && (this.getCsid() == null ? other.getCsid() == null : this.getCsid().equals(other.getCsid()))
            && (this.getPdate() == null ? other.getPdate() == null : this.getPdate().equals(other.getPdate()));
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", marketPrice=").append(marketPrice);
        sb.append(", shopPrice=").append(shopPrice);
        sb.append(", image=").append(image);
        sb.append(", desc=").append(desc);
        sb.append(", isHot=").append(isHot);
        sb.append(", csid=").append(csid);
        sb.append(", pdate=").append(pdate);
        sb.append("]");
        return sb.toString();
    }
}