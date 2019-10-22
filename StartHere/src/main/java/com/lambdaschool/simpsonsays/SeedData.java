package com.lambdaschool.simpsonsays;

import com.lambdaschool.simpsonsays.models.Role;
import com.lambdaschool.simpsonsays.models.User;
import com.lambdaschool.simpsonsays.models.UserRoles;
import com.lambdaschool.simpsonsays.models.Useremail;
import com.lambdaschool.simpsonsays.services.RoleService;
import com.lambdaschool.simpsonsays.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;


    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        roleService.save(r1);
        roleService.save(r2);
        roleService.save(r3);

        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(),
                                 r1));
        admins.add(new UserRoles(new User(),
                                 r2));
        admins.add(new UserRoles(new User(),
                                 r3));
        User u1 = new User("admin",
                           "password",
                           "admin@admin.net",
                           admins);
        u1.getUseremails()
          .add(new Useremail(u1,
                             "admin@admin.edu"));
        u1.getUseremails()
          .add(new Useremail(u1,
                             "admin@yahoo.com"));
        u1.getUseremails()
          .add(new Useremail(u1,
                            "admin@admin.org"));

        userService.save(u1);

        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(),
                                r3));
        datas.add(new UserRoles(new User(),
                                r2));
        User u2 = new User("cinnamontoast",
                           "crunch",
                           "ctc@yum.com",
                           datas);
        u2.getUseremails()
          .add(new Useremail(u2,
                             "cinnamon@yum.org"));
        u2.getUseremails()
          .add(new Useremail(u2,
                             "cinnamon@yum.net"));
        u2.getUseremails()
          .add(new Useremail(u2,
                             "cinnamon@yum.edu"));
        userService.save(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(),
                                r2));
        User u3 = new User("barn",
                           "silo",
                           "barn@silo.com",
                           users);
        u3.getUseremails()
          .add(new Useremail(u3,
                             "barnbarn@email.local"));
        userService.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(),
                                r2));
        User u4 = new User("puttputt",
                           "atari",
                           "puttputtatari@atari.com",
                           users);
        userService.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(),
                                r2));
        User u5 = new User("hellokitty",
                           "kittywhite",
                           "hellokitty@kittywhite.com",
                           users);
        userService.save(u5);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(),
                r2));
        User u6 = new User("nicholasinterest1",
                "postgres",
                "nickinterest@banana.com",
                users);
        userService.save(u6);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(),
                r2));
        User u7 = new User("bananadancer",
                "pb&j",
                "peanutbutterjelly@pb&j.com",
                users);
        userService.save(u7);
    }
}