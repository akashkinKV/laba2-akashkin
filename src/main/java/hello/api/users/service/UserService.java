package hello.api.users.service;

import hello.api.users.model.UserInfo;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public interface UserService {


    @Nonnull
    List<UserInfo> findAllUsers();

    UserInfo findUserByUid(@Nonnull UUID uuid);

    @Nullable
    void registrationUser(@Nonnull UserInfo userinfo);


    String loginUser(@Nonnull UserInfo userinfo);


    void updateUser(@Nonnull UserInfo userinfo);


    void deleteUser(@Nonnull UUID uuid);

}
