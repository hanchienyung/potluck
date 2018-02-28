/*
package com.cy.example.demo.Controller;


import com.cy.example.demo.Model.AppRoleRepository;
import com.cy.example.demo.Model.AppUser;
import com.cy.example.demo.Model.AppUserRepository;
import com.cy.example.demo.Model.PledgeditemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    AppRoleRepository roleRepository;

    @Autowired
    AppUserRepository userRepository;

    @Autowired
    PledgedItemRepository pledgeditemRepository;

    @RequestMapping("/")
    public String showIndex(Model model, Authentication auth)
    {
        if(auth!=null)
        {
            AppUser thisUser = AppUserRepository.findAppUserByUsername(auth.getName());
            if(thisUser!=null) {
                model.addAttribute("educationlist", thisUser.getMyEdus());
                model.addAttribute("skilllist", thisUser.getMySkills());
                model.addAttribute("experiencelist", thisUser.getMyExperience());
                System.out.println(auth.getName() + " authorities:" + auth.getAuthorities().toString());
                model.addAttribute("person", userRepository.findAppUserByUsername(auth.getName()));

                // TODO: 2/23/2018
                //If(thisUser.hasRole("RECRUITER"))
                */
/* Show the LIST JOBS page, otherwise show the index page.*//*


            }
        }
        else
        {
            if(auth!=null)
                model.addAttribute("inMemory",true);
            else
                model.addAttribute("inMemory",false);
        }

        return "index";
    }

    @RequestMapping("/loggedin")
    public String loggedIn(Model model)
    {

        return "loggedin";
        //return authentication.getName()+" Authorities: "+authentication.getAuthorities().toString()+"<a href='/roles'>Roles</a>";
    }

    @RequestMapping("/showresume")
    public String showResume(Model model,Authentication auth)
    {
        //This shows a list of all items regardless of current user. Compare with the default route to see how to customise the view
        //for the currently logged in user

        model.addAttribute("educationlist",educationRepository.findAll());
        model.addAttribute("skilllist",skillRepository.findAll());
        model.addAttribute("experiencelist",experienceRepository.findAll());
        AppUser currentUser = userRepository.findAppUserByUsername(auth.getName());
        if(auth!=null && currentUser!=null)
        {
            System.out.println(auth.getName()+" authorities:"+auth.getAuthorities().toString());
            model.addAttribute("person",currentUser);
        }
        else
            model.addAttribute("inMemory",true);
        return "showresume";
    }
    @RequestMapping("/roles")
    public @ResponseBody String showRoles()
    {

        return "Roles available:"+roleRepository.findAll().toString();
    }

    @GetMapping("/register")
    public String registerUser(Model model)
    {
        model.addAttribute("newuser",new AppUser());
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@Valid @ModelAttribute("newuser") AppUser user, BindingResult result,HttpServletRequest request)
    {
        if(result.hasErrors())
        {
            return "register";
        }

        if(request.getParameter("isEmployer")!=null)
            user.addRole(roleRepository.findAppRoleByRoleName("EMPLOYER"));
        else
            user.addRole(roleRepository.findAppRoleByRoleName("APPLICANT"));
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String showProfile(Model model, Authentication authentication) {

        AppUser getCurrentUser = userRepository.findAppUserByUsername(authentication.getName());
        if (getCurrentUser == null)
            model.addAttribute("inMemory", true);
        else
        {       model.addAttribute("inMemory", false);
            model.addAttribute("currentuser", getCurrentUser);
        }
        return "profile";
    }

    @PostMapping("/profile")
    public String saveProfile(@Valid @ModelAttribute("currentuser") AppUser user, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "profile";
        }
        userRepository.save(user);
        return "redirect:/";
    }

    //Replicate the profile code for Skill
    @GetMapping("/skill")
    public String addSkill(Model model)
    {
        model.addAttribute("aSkill",new Skill());
        return "addskill";
    }

    @PostMapping("/skill")
    public String saveSkill(@Valid @ModelAttribute("aSkill") Skill skill, BindingResult result, Authentication auth)
    {
        if(result.hasErrors())
        {
            return "addskill";
        }
        skillRepository.save(skill);
        AppUser currentUser =  userRepository.findAppUserByUsername(auth.getName());
        currentUser.getMySkills().toString();
        currentUser.addSkill(skill);
        userRepository.save(currentUser);

        return "redirect:/";
    }

    @GetMapping("/listskills")
    public @ResponseBody String listSkills(Authentication auth)
    {
        return userRepository.findAppUserByUsername(auth.getName()).getMySkills().toString();
    }



    @GetMapping("/addjob")
    public String addJob(Model model)
    {

        model.addAttribute("organizations",organizationRepository.findAll());
        model.addAttribute("newjob",new AppJob());
        return "addjob";
    }

    @PostMapping("/addjob")
    public String saveJob(@Valid @ModelAttribute("newjob") AppJob job, BindingResult result, Model model)
    {
        if(result.hasErrors())
        {
            return "addjob";
        }
        jobRepository.save(job);
        model.addAttribute("joblist",jobRepository.findAll());
        return "listjobs";
    }


    @PostMapping("/update/job")
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
}*/
