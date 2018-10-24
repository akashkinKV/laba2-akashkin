package hello.api.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import hello.api.users.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

public interface UserRepos
        extends JpaRepository<User, Integer> {


    //   @Query("select * from Users p where p.nickname = 123 ")

    User findUsersByUid(UUID uuid) ;
    User findUsersByEmail(String email) ;
    @Transactional
    void deleteByUid(UUID uuid);
}