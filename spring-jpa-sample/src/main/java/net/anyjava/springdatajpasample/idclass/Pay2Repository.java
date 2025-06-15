package net.anyjava.springdatajpasample.idclass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Pay2Repository extends JpaRepository<Pay2, PayId2>, Pay2RepositoryCustom {
}
