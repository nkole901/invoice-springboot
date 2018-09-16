package guru.springframework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "INVOICE")
public class Invoice {

    private Long id;
    private String client;
    private Long vatRate;
    private Date invoiceDate;

    List<LineItem> lineItem;


    public Invoice(Long id, String client, Long vatRate, Date invoiceDate, List<LineItem> lineItem) {
        this.id = id;
        this.client = client;
        this.vatRate = vatRate;
        this.invoiceDate = invoiceDate;
        this.lineItem = lineItem;
    }

    public Invoice() {
    }


    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CLIENT")
    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    @Basic
    @Column(name = "VAT_RATE")
    public Long getVatRate() {
        return vatRate;
    }

    public void setVatRate(Long vatRate) {
        this.vatRate = vatRate;
    }

    @Basic
    @Column(name = "INVOICE_DATE")
    public Date getInvoiceDate() {
        return invoiceDate;
    }


    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "INVOICE_ID",nullable = false)
    public List<LineItem> getLineItem() {
        return lineItem;
    }

    public void setLineItem(List<LineItem> lineItem) {
        this.lineItem = lineItem;
    }

    @Transient
    @JsonIgnore
    public BigDecimal getSubTotal(){
        Double subTotal = 0.0;

        for(LineItem item : getLineItem()){
            subTotal = subTotal + item.getLineItemTotal().doubleValue();
        }
        return new BigDecimal(subTotal).setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    @Transient
    @JsonIgnore
    public BigDecimal getVat(){
        Double vatAmount = 0.0;

        vatAmount = getSubTotal().doubleValue() / 100;
        vatAmount = vatAmount * getVatRate();
        return new BigDecimal(vatAmount).setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    @Transient
    @JsonIgnore
    public BigDecimal getTotal(){
        Double invoiceTotal = 0.0;

        invoiceTotal = getVat().doubleValue()+ getSubTotal().doubleValue();
        return new BigDecimal(invoiceTotal).setScale(2,BigDecimal.ROUND_HALF_UP);
    }

}
