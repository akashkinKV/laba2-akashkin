package hello.api.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import hello.api.users.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface UserRepos
        extends JpaRepository<User, Integer> {



    User findUsersByUid(UUID uuid);

    User findUsersByEmail(String email);

    @Transactional
    void deleteByUid(UUID uuid);

    @Transactional
    @Modifying
    @Query("update User t set t.email = :email,t.vk=:vk,t.password=:password where t.uid = :uid")
    void updateUser(@Param("email") String email, @Param("vk") String vk,
                    @Param("password") String password, @Param("uid") UUID uid);

}