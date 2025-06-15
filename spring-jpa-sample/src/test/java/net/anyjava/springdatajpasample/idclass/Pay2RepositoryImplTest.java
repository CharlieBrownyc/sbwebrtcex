package net.anyjava.springdatajpasample.idclass;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class Pay2RepositoryImplTest {

    @Autowired
    private Pay2Repository pay2Repository;

    @Test
    public void test() {
        pay2Repository.findAllByShopName("가게");
    }
}