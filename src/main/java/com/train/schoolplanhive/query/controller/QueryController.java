package com.train.schoolplanhive.query.controller;

import com.github.pagehelper.PageInfo;
import com.train.schoolplanhive.query.model.EnrollPlanStatis;
import com.train.schoolplanhive.query.model.EnrollmentPlan;
import com.train.schoolplanhive.query.service.EnrollmentPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * create by xiuzhong.li at 2022/4/1
 *
 * @Description: 文件描述
 */
@Controller
@RequestMapping("query")
public class QueryController {

    @Autowired
    private EnrollmentPlanService enrollmentPlanService;

    @RequestMapping("myquery.html")
    public String toEnrollMentPlanQuery(@RequestParam(value = "pageNo",defaultValue = "1") int pageNo,
                                        @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                        @RequestParam(value = "requery",defaultValue = "0") String requery,
                                        EnrollmentPlan enrollmentPlan,
                                        Model model, HttpSession session){


        PageInfo<EnrollmentPlan> pageInfo = null;

        System.out.println("queryCondition="+enrollmentPlan);
        System.out.println(pageNo);

        if(requery.equals("1")){
            session.removeAttribute("queryCondition");
        }
        EnrollmentPlan queryCondition = (EnrollmentPlan) session.getAttribute("queryCondition");

        System.out.println("session:querycondition="+queryCondition);

        if(queryCondition == null){

            if((enrollmentPlan.getProfess() == null || enrollmentPlan.getProfess().equals(""))
                    &&(enrollmentPlan.getProvince() == null || enrollmentPlan.getProvince().equals(""))
                    &&(enrollmentPlan.getSchool() == null || enrollmentPlan.getSchool().equals(""))){
                pageInfo = enrollmentPlanService.getEnrollmentPlanList(pageNo,pageSize);

            }else{
                queryCondition = enrollmentPlan;
                pageInfo = enrollmentPlanService.getEnrollmentPlanList(pageNo,pageSize,queryCondition);
                session.setAttribute("queryCondition",queryCondition);
            }
        }else{
            pageInfo = enrollmentPlanService.getEnrollmentPlanList(pageNo,pageSize,queryCondition);
            session.setAttribute("queryCondition",queryCondition);
        }


        model.addAttribute("pageInfo",pageInfo);
        //session.setAttribute("queryCondition",queryCondition);


        return "myquery";

    }

    @RequestMapping("topnmax-index.html")
    public String hotTopNQuery(String school,String province,Model model){
        System.out.println("to hot top n query>>>>>");
        System.out.println("school=" +school);
        System.out.println("province="+province);
        if(school != null && school.trim().equals("")){
            school = null;
        }
        if(province != null && province.trim().equals("")){
            province = null;
        }

        List<EnrollPlanStatis> enrollPlanStatisList = enrollmentPlanService.getMajorStatis(school,province,true,10);

        System.out.println(enrollPlanStatisList);
        model.addAttribute("enrollPlanStatisList",enrollPlanStatisList);

        return "topnmax-index";
    }
    @RequestMapping("topnmin-index.html")
    public String topnCoolNQuery(String school,String province,Model model){
        System.out.println("to cool top n query>>>>>");
        System.out.println("school=" +school);
        System.out.println("province="+province);
        if(school != null && school.trim().equals("")){
            school = null;
        }
        if(province != null && province.trim().equals("")){
            province = null;
        }

        List<EnrollPlanStatis> enrollPlanStatisList = enrollmentPlanService.getMajorStatis(school,province,false,10);

        System.out.println(enrollPlanStatisList);
        model.addAttribute("enrollPlanStatisList",enrollPlanStatisList);

        return "topnmin-index";
    }
}
