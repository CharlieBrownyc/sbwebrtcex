package net.anyjava.springdatajpasample.idclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import net.anyjava.springdatajpasample.embeddedid.PayId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@IdClass(PayId.class)
@Entity
@Table(name = "pay_2")
@NoArgsConstructor
public class Pay2 {

    @Id
    private Long payNumber;

    @Id
    private Long paySeq;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pay2")
    private List<PayShop2> payShop2List = new ArrayList<>();
}
