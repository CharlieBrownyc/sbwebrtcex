package net.anyjava.springdatajpasample.embeddedid;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
@NoArgsConstructor
public class PayId implements Serializable {

    /**
     * 결제번호 (비지니스적으로 유의미한 번호)
     */
    @EqualsAndHashCode.Include
    @Column
    private Long payNumber;

    @EqualsAndHashCode.Include
    @Column
    private Long paySeq;

    public PayId(Long payNumber,
                 Long paySeq) {
        this.payNumber = payNumber;
        this.paySeq = paySeq;
    }
}
