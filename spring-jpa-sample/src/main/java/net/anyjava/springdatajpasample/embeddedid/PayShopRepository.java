package net.anyjava.springdatajpasample.embeddedid;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PayShopRepository extends JpaRepository<PayShop, PayShopId> {
}
