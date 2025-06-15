package net.anyjava.springdatajpasample.embeddedid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PayShopRepositoryTest {

    @Autowired
    private PayShopRepository payShopRepository;

    @Test
    public void testFindById() {
        assertThat(payShopRepository.findById(new PayShopId(new PayDetailId(new PayId(1L, 2L), 2L), "1234"))).isNotPresent();
    }

    @Test
    public void testSave() {
        // given
        PayShopId payShopId = new PayShopId(
                new PayDetailId(new PayId(1L, 2L), 1L),
                "1234"
        );
        PayShop payShop = new PayShop(payShopId, "자장면집");

        // when
        PayShop savedPayShop = payShopRepository.save(payShop);

        // then
        assertThat(savedPayShop.getShopName()).isEqualTo("자장면집");
    }
}