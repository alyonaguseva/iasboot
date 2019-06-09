package ru.rushydro.vniig.ias.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.rushydro.vniig.ias.dao.entity.Bing3Exchange;
import ru.rushydro.vniig.ias.dao.entity.SignalValueExt;

public interface Bing3ExchangeRepository extends CrudRepository<Bing3Exchange, Long> {

    @Query("Select bing3Exchange From Bing3Exchange bing3Exchange " +
            "where bing3Exchange.idExternalSignal = ?1 " +
            "order by bing3Exchange.id desc")
    Page<Bing3Exchange> findByIdExternalSignal(String id, Pageable pageable);
}
