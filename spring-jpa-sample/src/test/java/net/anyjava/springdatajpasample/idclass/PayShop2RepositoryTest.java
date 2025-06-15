package net.anyjava.springdatajpasample.idclass;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PayShop2RepositoryTest {

    @Autowired
    private PayShop2Repository payShop2Repository;

    @Autowired
    private PayDetail2Repository payDetail2Repository;

    @Autowired
    private TestEntityManager em;

    @Test
    public void testSRve() {
        // given
        Long payNumber = 1234555L;
        PayDetail2 payDetail2 = payDetail2Repository.save(new PayDetail2(payNumber, 1L, 1L));

        PayShop2 payShop2 = new PayShop2(payNumber, 1L, 1L, "12312", "자장면집");
        payShop2.setPayDetail2(payDetail2);

        // when
        PayShop2 savedPayShop2 = payShop2Repository.save(payShop2);

        // then
        em.flush();
        assertThat(savedPayShop2.getPayNumber()).isEqualTo(payNumber);
    }
}