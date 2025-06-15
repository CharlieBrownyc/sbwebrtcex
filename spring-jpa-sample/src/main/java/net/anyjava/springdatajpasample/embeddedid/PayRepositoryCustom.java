package net.anyjava.springdatajpasample.embeddedid;

import java.util.List;

public interface PayRepositoryCustom {
    List<Pay> findAllByShopName(String shopNumber);
}
