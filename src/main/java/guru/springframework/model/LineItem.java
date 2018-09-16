package guru.springframework.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "LINE_ITEM")
public class LineItem {

    private Long id;
    private Long quantity;
    private String description;
    private BigDecimal unitPrice;

    public LineItem() {
    }

    public LineItem(Long id, Long quantity, String description, BigDecimal unitPrice) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.unitPrice = unitPrice;
    }

    @Id
    @Column(name ="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Basic
    @Column(name = "QUANTITY")
    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "UNIT_PRICE")
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }


    @Transient
    @JsonIgnore
    public BigDecimal getLineItemTotal(){
        Double lineTotal = 0.0;

        lineTotal = getUnitPrice().doubleValue() * getQuantity();
        return new BigDecimal(lineTotal).setScale(2,BigDecimal.ROUND_HALF_UP);
    }

}
