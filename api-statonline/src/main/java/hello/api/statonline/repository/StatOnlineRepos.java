package hello.api.statonline.repository;


import hello.api.statonline.entity.StatOnline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface StatOnlineRepos extends JpaRepository<StatOnline, Integer> {


    List<StatOnline> findFirst720ByUid(UUID uuid);

    List<StatOnline> findFirst5040ByUid(UUID uuid);

    List<StatOnline> findFirst21600ByUid(UUID uuid);

    List<StatOnline> findAllByUid(UUID uuid);

    @Transactional
    void deleteByUid(UUID uuid);
}
