package net.anyjava.springdatajpasample.idclass;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "pay_detail_2")
@IdClass(PayDetailId2.class)
@NoArgsConstructor
public class PayDetail2 {

    @Id
    private Long payNumber;

    @Id
    private Long paySeq;

    @Id
    private Long payDetailId;

    @OneToMany(mappedBy = "payDetail2", fetch = FetchType.LAZY)
    @org.hibernate.annotations.ForeignKey(name = "none")
    private List<PayShop2> payShops = new ArrayList<>();

    public PayDetail2(Long payNumber,
                      Long paySeq,
                      Long payDetailId) {
        this.payNumber = payNumber;
        this.paySeq = paySeq;
        this.payDetailId = payDetailId;
    }
}
