package hello.api.users.web;

import hello.api.users.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import hello.api.users.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api-users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/login{email}{password}")
    public ResponseEntity<String> loginUserbyEmail (@RequestParam String email,@RequestParam String password) {
        try {


    return new ResponseEntity(userService.loginUser(email,password), HttpStatus.OK);

        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/findByEmail{email}")
    public ResponseEntity<UserInfo> findUserByEmail(@RequestParam String email) {
        try {


            return new ResponseEntity( userService.findUserByEmail(email),HttpStatus.OK);

        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }


    @GetMapping("/findByUID{uuid}")
    public ResponseEntity<UserInfo> findUserByUID (@RequestParam UUID uuid) {
        try {


            return new ResponseEntity( userService.findUserByUid(uuid),HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/registration{email}{password}")
    public ResponseEntity<String> registrationUser ( @RequestParam String email,@RequestParam String password) {
        try {


            userService.registrationUser(email,password);

            return new ResponseEntity("Success",HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/updateVK{vk}{uuid}")
    public ResponseEntity<String> updateVK ( @RequestParam String vk,@RequestParam UUID uuid) {
        try {


            userService.updateUserVk(vk, uuid);

            return new ResponseEntity("Success",HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("/updateIdentify{identify}{uuid}")
    public ResponseEntity<String> updateIdentify ( @RequestParam boolean identify,@RequestParam UUID uuid) {
        try {


            userService.updateUserIdentify(identify, uuid);

            return new ResponseEntity("Success",HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/updatePassword{password}{uuid}")
    public ResponseEntity<String> updateIdentify ( @RequestParam String password,@RequestParam UUID uuid) {
        try {


            userService.updateUserPassword(password, uuid);

            return new ResponseEntity("Success",HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserInfo>> findAllUsers() {

        List<UserInfo> users = userService.findAllUsers();

        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        else {
            return new ResponseEntity<List<UserInfo>>(users, HttpStatus.OK);
        }

    }

    @GetMapping()
    public ResponseEntity<String> getApi () {

        return new ResponseEntity("Hello this is API-USERS",HttpStatus.OK);
    }
    @GetMapping("/delete{uuid}")
    public ResponseEntity<String> deleteUser (@RequestParam UUID  uuid) {
        try {

            userService.deleteUser(uuid);
            return new ResponseEntity( "Success",HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }


}
