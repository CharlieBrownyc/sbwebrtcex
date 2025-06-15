package net.anyjava.springdatajpasample.idclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import net.anyjava.springdatajpasample.embeddedid.Pay;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "pay_shop_2")
@IdClass(PayShopId2.class)
@NoArgsConstructor
public class PayShop2 {

    @Id
    private Long payNumber;

    @Id
    private Long paySeq;

    @Id
    private Long payDetailId;

    @Id
    private String shopNumber;

    private String shopName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pay2 pay2;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumns(value = {
            @JoinColumn(name = "payNumber", updatable = false, insertable = false),
            @JoinColumn(name = "paySeq", updatable = false, insertable = false),
            @JoinColumn(name = "payDetailId", updatable = false, insertable = false)
    }, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private PayDetail2 payDetail2;

    public PayShop2(Long payNumber,
                    Long paySeq,
                    Long payDetailId,
                    String shopNumber,
                    String shopName) {
        this.payNumber = payNumber;
        this.paySeq = paySeq;
        this.payDetailId = payDetailId;
        this.shopNumber = shopNumber;
        this.shopName = shopName;
    }

    public void setPay2(Pay2 pay2) {
        if (pay2 != null) {
            pay2.getPayShop2List().remove(this);
        }
        this.pay2 = pay2;
        this.pay2.getPayShop2List().add(this);
    }

    public void setPayDetail2(PayDetail2 payDetail2) {
        if (payDetail2 != null) {
            payDetail2.getPayShops().remove(this);
        }
        this.payDetail2 = payDetail2;
        this.payDetail2.getPayShops().add(this);
    }
}
