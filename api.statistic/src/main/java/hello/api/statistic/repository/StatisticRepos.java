package hello.api.statistic.repository;

import hello.api.statistic.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface StatisticRepos
        extends JpaRepository<Statistic, Integer> {


    List<Statistic> findFirst7ByUid(UUID uuid);

    List<Statistic> findFirst30ByUid(UUID uuid);

    List<Statistic> findAllByUid(UUID uuid);

    @Transactional
    void deleteByUid(UUID uuid);
}