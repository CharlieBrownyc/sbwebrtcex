package net.anyjava.springdatajpasample.embeddedid;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static net.anyjava.springdatajpasample.embeddedid.QPay.pay;
import static net.anyjava.springdatajpasample.embeddedid.QPayShop.payShop;

@Repository
public class PayRepositoryImpl extends QuerydslRepositorySupport implements PayRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public PayRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Pay.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Pay> findAllByShopName(String shopName) {
        return jpaQueryFactory.from(pay)
                .innerJoin(pay.payShops, payShop)
                .where(payShop.shopName.contains(shopName))
                .select(pay)
                .fetch();
    }
}
