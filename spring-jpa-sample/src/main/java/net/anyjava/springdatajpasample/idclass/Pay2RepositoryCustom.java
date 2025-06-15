package net.anyjava.springdatajpasample.idclass;

import java.util.List;

public interface Pay2RepositoryCustom {
    List<Pay2> findAllByShopName(String shopName);
}
