package hello.api.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hello.api.users.entity.User;
import hello.api.users.model.UserInfo;
import hello.api.users.repository.UserRepos;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl
        implements UserService {

    @Autowired
    private UserRepos userRepos;


    @Nullable
    @Override
    @Transactional(readOnly = true)
    public UserInfo findUserByEmail(@Nonnull String email) {
        return    createUserInfo(userRepos.findUsersByEmail(email));
    }



    @Nullable
    @Override
    @Transactional(readOnly = true)
    public UserInfo findUserByUid(@Nonnull UUID uuid) {
        return    createUserInfo(userRepos.findUsersByUid(uuid));
    }

    @Nullable
    @Override
    public void registrationUser(@Nonnull String email, @Nonnull String password) {


        userRepos.saveAndFlush(createUser(email,password,"No",false));
    }

    @Nullable
    @Override
    public String loginUser(@Nonnull String email, @Nonnull String password) {
        User user= userRepos.findUsersByEmail(email);
        if(user.getPassword().equals(password)) {
            return "Success";
        }
        else
        {
            return "Error password";

        }
    }
    @Nullable
    @Override
    public void updateUserVk(@Nonnull String vk,@Nonnull UUID uuid) {
      User user=  userRepos.findUsersByUid(uuid);
      user.setVk(vk);
        userRepos.save(user);
    }

    @Nullable
    @Override
    public void updateUserIdentify( @Nonnull boolean identify,@Nonnull UUID uuid) {
        User user=  userRepos.findUsersByUid(uuid);
        user.setIdentify(identify);
        userRepos.save(user);
    }

    @Nullable
    @Override
    public  void updateUserPassword( @Nonnull String password,@Nonnull UUID uuid) {
        User user=  userRepos.findUsersByUid(uuid);
        user.setPassword(password);
        userRepos.save(user);
    }

    @Nullable
    @Override
    public void deleteUser(@Nonnull UUID uuid) {
        userRepos.deleteByUid(uuid);
    }

    @Nonnull
    @Override
    public List<UserInfo> findAllUsers() {
        return      userRepos.findAll()
                .stream()
                .map(this::createUserInfo)
                .collect(Collectors.toList());
    }

    @Nonnull
    private User createUser(@Nonnull String email, @Nonnull String password,
                            @Nonnull String vk,@Nonnull boolean identify) {

        User user = new User();

        user.setEmail(email);
        user.setPassword(password);
        user.setIdentify(identify);
        user.setUid(UUID.randomUUID());
        user.setVk(vk);
        return user;
    }

    @Nonnull
    private UserInfo createUserInfo(@Nonnull User user) {

        UserInfo userInfo = new UserInfo();
        userInfo.setVk(user.getVk());
        userInfo.setEmail(user.getEmail());
        userInfo.setPassword(user.getPassword());
        userInfo.setUid(user.getUid());
        return userInfo;
    }
}
