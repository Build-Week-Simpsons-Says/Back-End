package com.lambdaschool.simpsonsays;

import com.lambdaschool.simpsonsays.models.*;
import com.lambdaschool.simpsonsays.services.QuotesService;
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

    @Autowired
    QuotesService quotesService;


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

        User u1Saved = userService.save(u1);

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
        User u2Saved = userService.save(u2);

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
        User u3Saved = userService.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(),
                                r2));
        User u4 = new User("puttputt",
                           "atari",
                           "puttputtatari@atari.com",
                           users);
        User u4Saved = userService.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(),
                                r2));
        User u5 = new User("hellokitty",
                           "kittywhite",
                           "hellokitty@kittywhite.com",
                           users);
        User u5Saved = userService.save(u5);

        Quotes Q1 = new Quotes("This is the greatest case of false advertising I've seen since I sued the movie the never ending story.", u1Saved);
        Quotes Q2 = new Quotes("I'm normally not a praying man, but if you're up there, please save me superman.", u1Saved);
        Quotes Q3 = new Quotes("I don't like being outdoors, smithers. For one thing, there's too many fat children.", u1Saved);

        Quotes Q4 = new Quotes("Oh, so they have internet on computers now!", u2Saved);
        Quotes Q5 = new Quotes("Things aren't as happy as they used to be down here at the unemployment office. Joblessness is no longer just for philosophy majors. Useful people are starting to feel the pinch.", u2Saved);
        Quotes Q6 = new Quotes("I used to be with it. But then they changed what it was. Now what I'm with isn't it, and what's it seems scary and wierd. It'll happen to you.", u2Saved);

        Quotes Q7 = new Quotes("To alcohol! The cause of - and the solution to - all life's problems!", u3Saved);
        Quotes Q8 = new Quotes("Just once I'd like someone to call me 'sir' without adding 'you're making a scene.'", u3Saved);
        Quotes Q9 = new Quotes("Me fail english? That's unpossible!", u3Saved);

        Quotes Q10 = new Quotes("You'll have to speak up, I'm wearing a towel.", u4Saved);
        Quotes Q11 = new Quotes("Oh, people can come up with statistics to prove anything, kent. 14% of people know that", u4Saved);
        Quotes Q12 = new Quotes("Marge, don't discourage the boy! Weaseling out of things is important to learn. It's what separates us from the animals! Except the weasel.", u4Saved);

        Quotes Q13 = new Quotes("Well, I'm better than dirt. Well, most kinds of dirt. I mean not that fancy store bought dirt. That stuff's loaded with nutrients. I... I can't compete with that stuff.", u5Saved);
        Quotes Q14 = new Quotes("I used to be with it. But then they changed what it was. Now what I'm with isn't it, and what's it seems scary and wierd. It'll happen to you.", u5Saved);
        Quotes Q15 = new Quotes("Can't you people take the law into your own hands? I mean, we can't be policing the entire city!", u5Saved);

        u1Saved.getQuotes().add(Q1);
        u1Saved.getQuotes().add(Q2);
        u1Saved.getQuotes().add(Q3);

        u2Saved.getQuotes().add(Q4);
        u2Saved.getQuotes().add(Q5);
        u2Saved.getQuotes().add(Q6);

        u3Saved.getQuotes().add(Q7);
        u3Saved.getQuotes().add(Q8);
        u3Saved.getQuotes().add(Q9);

        u4Saved.getQuotes().add(Q10);
        u4Saved.getQuotes().add(Q11);
        u4Saved.getQuotes().add(Q12);

        u5Saved.getQuotes().add(Q13);
        u5Saved.getQuotes().add(Q14);
        u5Saved.getQuotes().add(Q15);
    }
}