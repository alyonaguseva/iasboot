package ru.rushydro.vniig.ias.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.rushydro.vniig.ias.dao.entity.SignalValueExt;

/**
 * Created by yazik on 04.11.2017.
 */
public interface SignalValueExtRepository extends JpaRepository<SignalValueExt, Long> {

    @Query("Select signalValueExt From SignalValueExt signalValueExt " +
            "where signalValueExt.signalId = ?1 and signalValueExt.calibrated = ?2 " +
            "order by signalValueExt.id desc")
    Page<SignalValueExt> findByIdSignal(Integer id, Integer calibrated, Pageable pageable);
}
