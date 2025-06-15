package net.anyjava.springdatajpasample.embeddedid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PayRepositoryTest {

    @Autowired
    private PayRepository payRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testFindById() {
        payRepository.findById(new PayId(1L, 2L));
    }

    @Test
    public void saveOne() {
        // given
        // PayId 를 왜 null ? @MapsId 로 할당됨
        PayShop payShop = new PayShop(new PayShopId(new PayDetailId(null, 1L), "9999"), "업소");
        testEntityManager.persist(payShop);

        Pay pay = new Pay(new PayId(123L, 1L));

        payShop.setPay(pay);

        // when
        Pay savedPay = payRepository.save(pay);

        // then
        assertThat(savedPay.getId().getPayNumber()).isEqualTo(123L);
        assertThat(payShop.getPay()).isEqualTo(savedPay);
    }
}