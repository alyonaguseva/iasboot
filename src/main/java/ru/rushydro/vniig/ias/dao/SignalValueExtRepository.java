package ru.rushydro.vniig.ias.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.rushydro.vniig.ias.dao.entity.SignalValueExt;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by yazik on 04.11.2017.
 */
public interface SignalValueExtRepository extends JpaRepository<SignalValueExt, Long> {

    @Query("Select signalValueExt From SignalValueExt signalValueExt " +
            "where signalValueExt.signalId = ?1 and signalValueExt.calibrated = ?2 " +
            "order by signalValueExt.id desc")
    Page<SignalValueExt> findByIdSignal(Integer id, Integer calibrated, Pageable pageable);

    @Query("Select signalValueExt From SignalValueExt signalValueExt " +
            "where signalValueExt.signalId = ?1 " +
            "order by signalValueExt.id desc")
    Page<SignalValueExt> findByIdSignal(Integer id, Pageable pageable);

    @Query("Select signalValueExt From SignalValueExt signalValueExt " +
            "where signalValueExt.signalId = ?1 and signalValueExt.valueTime > ?2 " +
            "order by signalValueExt.id desc")
    Page<SignalValueExt> findByIdSignalAndValueTimeIsAfter(Integer id, LocalDateTime dateTime, Pageable pageable);

    @Query("select s1 from SignalValueExt s1 " +
            "where s1.id = (select max(s2.id) from SignalValueExt s2 where s1.signalId = s2.signalId) order by s1.signalId")
    List<SignalValueExt> findNew();
}
