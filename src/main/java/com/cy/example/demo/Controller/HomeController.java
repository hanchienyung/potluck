
package com.cy.example.demo.Controller;


import com.cy.example.demo.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    AppRoleRepository roleRepository;

    @Autowired
    AppUserRepository userRepository;

    @Autowired
    PledgedItemRepository pledgeditemRepository;

    @RequestMapping("/")
    public String mainpage(Model model){
        return "mainpage";
    }

    @RequestMapping("/login")
    public String login(Model model)
    {
        return "login";

    }

    @RequestMapping("/logout")
    public String logout(Model model)
    {
        return "mainpage";

    }

    @RequestMapping("/listpledgeditem")
    public String listpledgeditem(Model model)
    {
        model.addAttribute("pledgeditems",pledgeditemRepository.findAll());
      //  model.addAttribute("users",userRepository.findAll());
        return "listpledgeditem";
    }

    @GetMapping("/register")
    public String registerUser(Model model)
    {
        model.addAttribute("user",new AppUser());
        return "registration";
    }

    @PostMapping("/register")
    public String saveUser(@Valid @ModelAttribute("user") AppUser user, BindingResult result, HttpServletRequest request)
    {
        if(result.hasErrors())
        {
            return "registration";
        }

        userRepository.save(user);
        return "redirect:/";
    }

    @RequestMapping("/addpledgeditem")
    public String addpledgeditem(Model model, PledgedItem pledgedItem)
    {
        model.addAttribute("users",userRepository.findAll());
        model.addAttribute("pledgeditem", new PledgedItem());
        return "addpledgeditem";
    }

    @PostMapping("/addpledgeditem")
    public String addpledgeditem(Model model, BindingResult result, PledgedItem pledgedItem)
    {
        if(result.hasErrors())
        {
            return "addpledgeditem";
        }
   /*     model.addAttribute("users",userRepository.findAll());*/
        pledgeditemRepository.save(pledgedItem);
        return "addpledgeditem";
    }

    @RequestMapping("/addusertopledg")
    public String addusertopledg(HttpServletRequest request, Model model)
    {
        String pledgid = request.getParameter("pledgid");
        model.addAttribute("newpledg", pledgeditemRepository.findOne(new Long(pledgid)));
        model.addAttribute("users",userRepository.findAll());

        return "addusertopledg";
    }

    @PostMapping("/savesusertopledg")
    public String saveusertopledg(HttpServletRequest request, @ModelAttribute("newpledg") PledgedItem pledgedItem)
    {
        String userid = request.getParameter("userid");
        System.out.println("Pledgeid from add user to pledge:"+pledgedItem.getId()+" User id:"+userid);
        pledgedItem.addUsertoPledge(userRepository.findOne(new Long(userid)));
        pledgeditemRepository.save(pledgedItem);
        return "redirect:/listpledgeditem";
    }


    @GetMapping("/search")
    public String getSearch(){
        return "searchform";
    }

   /*
    @PostMapping("/search")
    public String searchpledgeditem(HttpServletRequest request, Model model)
    {
        String searchItems = request.getParameter(("search");
        model.addAttribute("search", )

        model.addAttribute("pledgeditems",pledgeditemRepository.findAllByItemName());


        return "searchpledgeditem";
    }




    @PostMapping("/search")
    public String showSearchResults(HttpServletRequest request, Model model){
        String searchProducts = request.getParameter("search");
        model.addAttribute("search",searchProducts);
//

//        Expecting multiple parameters or else will throw No parameter available Need to pass as many as are in constructor in Entity.
        model.addAttribute("productsearch",productRepository.findAllByProductNameContainingIgnoreCase(searchProducts));
//
        return "searchproductlist";
    }


    /*

    @PostMapping("/update/pledgeditem")
    public String updateJob(HttpServletRequest request, Model model)
    {
        model.addAttribute("newjob",jobRepository.findOne(new Long(request.getParameter("id"))));
        return "addjob";
    }


    @RequestMapping("/listjobs")
    public String listJobs(Model model)
    {
        model.addAttribute("joblist",jobRepository.findAll());
        return "listjobs";
    }

    @PostMapping("/addskilltojob")
    public String showSkillsForJob(HttpServletRequest request, Model model)
    {
        String jobid = request.getParameter("jobid");
        model.addAttribute("newjob",jobRepository.findOne(new Long(jobid)));

        //Make skills disappear from add form when they are already included (Set already makes it impossible to add multiple)
        model.addAttribute("skillList",skillRepository.findAll());

        return "addskilltojob";
    }

    @PostMapping("/saveskilltojob")
    public String addSkilltoJob(HttpServletRequest request, @ModelAttribute("newjob") AppJob job)
    {
        String skillid = request.getParameter("skillid");
        System.out.println("Job id from add skill to job:"+job.getId()+" Skill id:"+skillid);
        job.addSkilltoJob(skillRepository.findOne(new Long(skillid)));
        jobRepository.save(job);
        return "redirect:/listjobs";
    }

    @PostMapping("/viewjobskills")
    public String viewJobSkills(HttpServletRequest request, Model model)
    {
        String jobid = request.getParameter("jobid");
        AppJob job = jobRepository.findOne(new Long(jobid));
        if(job.getJobSkills().size()<1)
            return "redirect:/listjobs";
        model.addAttribute("newjob",job);
        return "viewjobskills";
    }

    @RequestMapping("/getMyJobs")
    public String getJobsThatApply(Authentication auth, Model model)
    {
        HashSet <Skill> mySkills = new HashSet(userRepository.findAppUserByUsername(auth.getName()).getMySkills());
        HashSet <AppJob> matchingJobs = jobRepository.findAppJobsByJobSkillsIn(mySkills);

        System.out.println(matchingJobs.toString());
        model.addAttribute("joblist",matchingJobs);
        return "viewsuggestedjobs";
    }
    */
}
