package net.anyjava.springdatajpasample.embeddedid;

import com.google.common.base.Preconditions;
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
public class PayShopId implements Serializable {

    /**
     * 테이블에서 정의하는 컬럼 사이즈
     */
    public static final int SHOP_NUMBER_SIZE = 12;

    @EqualsAndHashCode.Include
    private PayDetailId payDetailId;

    @EqualsAndHashCode.Include
    @Column
    private String shopNumber;

    public PayShopId(PayDetailId payDetailId,
                     String shopNumber) {
        Preconditions.checkArgument(shopNumber.length() <= SHOP_NUMBER_SIZE);

        this.payDetailId = payDetailId;
        this.shopNumber = shopNumber;
    }
}
