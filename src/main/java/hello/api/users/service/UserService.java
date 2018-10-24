package hello.api.users.service;

import hello.api.users.model.UserInfo;
import org.springframework.data.jpa.repository.Query;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public interface UserService {




    @Nonnull
    List<UserInfo> findAllUsers();

      UserInfo findUserByUid(@Nonnull UUID uuid);

    UserInfo findUserByEmail(@Nonnull String email);

    @Nullable
    void registrationUser(@Nonnull String email, @Nonnull String password);


    String loginUser (@Nonnull String email, @Nonnull String password);


     void updateUserVk ( @Nonnull String vk,@Nonnull UUID uuid);

    void updateUserIdentify( @Nonnull boolean identify,@Nonnull UUID uuid);

    void updateUserPassword( @Nonnull String password,@Nonnull UUID uuid);

     void deleteUser (@Nonnull UUID uuid);

}
