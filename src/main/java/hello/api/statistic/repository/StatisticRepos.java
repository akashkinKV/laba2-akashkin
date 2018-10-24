package hello.api.statistic.repository;

import hello.api.statistic.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface StatisticRepos
        extends JpaRepository<Statistic, Integer> {


    //   @Query("select * from Users p where p.nickname = 123 ")


   List<Statistic> findFirst7ByUid(String uuid);
   List<Statistic> findFirst30ByUid(String uuid);
   List<Statistic> findAllByUid(String uuid);

}