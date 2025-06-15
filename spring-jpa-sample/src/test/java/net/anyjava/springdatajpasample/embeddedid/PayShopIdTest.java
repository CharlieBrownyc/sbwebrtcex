package net.anyjava.springdatajpasample.embeddedid;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class PayShopIdTest {

    @Test
    public void testCheckShopNumberColumnSize_SUCCESS() {
        // given
        String anyShopNumber = "123";

        // when
        PayShopId payShopId = new PayShopId(null, anyShopNumber);

        // then
        assertThat(payShopId.getShopNumber()).isEqualTo(anyShopNumber);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckShopNumberColumnSize_FAIL() {
        // given
        String anyShopNumber = "12345678901234";

        // when
        new PayShopId(null, anyShopNumber);

        // then
        fail("파라미터검증 테스트 실패");
    }
}