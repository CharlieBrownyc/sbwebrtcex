package net.anyjava.springdatajpasample.idclass;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.anyjava.springdatajpasample.embeddedid.PayId;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class PayDetailId2 implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    private Long payNumber;

    @EqualsAndHashCode.Include
    @Id
    private Long paySeq;

    @EqualsAndHashCode.Include
    @Id
    private Long payDetailId;
}
