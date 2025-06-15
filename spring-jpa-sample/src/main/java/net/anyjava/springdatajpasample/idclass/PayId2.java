package net.anyjava.springdatajpasample.idclass;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class PayId2 implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    private Long payNumber;

    @EqualsAndHashCode.Include
    @Id
    private Long paySeq;

    public PayId2(Long payNumber,
                  Long paySeq) {
        this.payNumber = payNumber;
        this.paySeq = paySeq;
    }
}
