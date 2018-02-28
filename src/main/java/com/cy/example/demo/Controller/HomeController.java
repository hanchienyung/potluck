
package com.cy.example.demo.Controller;


import com.cy.example.demo.Model.AppRoleRepository;
import com.cy.example.demo.Model.AppUser;
import com.cy.example.demo.Model.AppUserRepository;
import com.cy.example.demo.Model.PledgedItemRepository;
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
    public String addpledgeditem(Model model)
    {
        return "addpledgeditem";
    }

    @RequestMapping("/loggedin")
    public String loggedIn(Model model)
    {
        return "loggedin";

    }

    @RequestMapping("/listpledgeitem")
    public String listpledgeditem(Model model)
    {
        return "listpledgeditem";
    }

    @GetMapping("/register")
    public String registerUser(Model model)
    {
        model.addAttribute("newuser",new AppUser());
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@Valid @ModelAttribute("newuser") AppUser user, BindingResult result, HttpServletRequest request)
    {
        if(result.hasErrors())
        {
            return "register";
        }

        userRepository.save(user);
        return "redirect:/";
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
