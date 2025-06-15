package net.anyjava.springdatajpasample.idclass;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static net.anyjava.springdatajpasample.idclass.QPay2.pay2;
import static net.anyjava.springdatajpasample.idclass.QPayShop2.payShop2;

@Repository
public class Pay2RepositoryImpl extends QuerydslRepositorySupport implements Pay2RepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public Pay2RepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Pay2.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Pay2> findAllByShopName(String shopName) {
        return jpaQueryFactory.from(pay2)
                .innerJoin(pay2.payShop2List, payShop2)
                .where(payShop2.shopName.contains(shopName))
                .select(pay2)
                .fetch();
    }
}
