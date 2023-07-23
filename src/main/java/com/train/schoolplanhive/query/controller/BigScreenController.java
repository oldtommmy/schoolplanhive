package com.train.schoolplanhive.query.controller;


import com.train.schoolplanhive.query.model.AreaPlan;
import com.train.schoolplanhive.query.model.ProfessPlan;
import com.train.schoolplanhive.query.model.ProvincePlan;
import com.train.schoolplanhive.query.service.BigScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * create by xiuzhong.li at 2022/4/7
 *
 * @Description:文件描述：展示大屏控制器
 */
@Controller
@RequestMapping("bigscreen")
public class BigScreenController {
    @Autowired
    private BigScreenService bigScreenService;

    @RequestMapping("index.html")
    public String showBigScreen(Model model, HttpSession session){

        List<ProfessPlan> professPlanList = bigScreenService.getHotMajors(10);
        List<ProfessPlan> coolProfessPlanList = bigScreenService.getCoolMajors(10);

        StringBuffer hotmajorPlans = new StringBuffer("[");
        StringBuffer hotmajorNames = new StringBuffer("[");
        for (int i = 0; i < professPlanList.size(); i++) {
            ProfessPlan professPlan = professPlanList.get(i);

            hotmajorNames.append("\"");
            hotmajorNames.append(professPlan.getProfess());
            hotmajorNames.append("\",");

            hotmajorPlans.append(professPlan.getPlan());
            hotmajorPlans.append(",");


        }
        hotmajorNames.append("]");
        hotmajorPlans.append("]");

        StringBuffer coolmajorPlans = new StringBuffer("[");
        StringBuffer coolmajorNames = new StringBuffer("[");

        for (int i = 0; i < coolProfessPlanList.size(); i++) {
            ProfessPlan professPlan = coolProfessPlanList.get(i);

            coolmajorNames.append("\"");
            coolmajorNames.append(professPlan.getProfess());
            coolmajorNames.append("\",");

            coolmajorPlans.append(professPlan.getPlan());
            coolmajorPlans.append(",");


        }
        coolmajorNames.append("]");
        coolmajorPlans.append("]");



        List<AreaPlan> areaPlans = bigScreenService.getAllAreaPlan();
        StringBuffer areaPlanData = new StringBuffer("[");

        for (int i = 0; i < areaPlans.size(); i++) {

            AreaPlan areaPlan = areaPlans.get(i);
            areaPlanData.append("{value: ");
            areaPlanData.append(areaPlan.getPlan());
            areaPlanData.append(", name: '");
            areaPlanData.append(areaPlan.getArea());
            areaPlanData.append("'},");
            
        }
        areaPlanData.append("]");



        List<ProvincePlan> provincePlans = bigScreenService.getAllProvincePlan();
        StringBuffer provincePlanData = new StringBuffer("[");
        for (int i = 0; i < provincePlans.size(); i++) {
            ProvincePlan provincePlan = provincePlans.get(i);
            provincePlanData.append("{name:\"");
            provincePlanData.append(provincePlan.getProvince());
            provincePlanData.append("\",value:");
            provincePlanData.append(provincePlan.getPlan());
            provincePlanData.append("},");


        }
        provincePlanData.append("]");

        model.addAttribute("hotmajorplanArr",hotmajorPlans.toString());
        model.addAttribute("hotmajorNameArr",hotmajorNames.toString());

        model.addAttribute("coolmajorplanArr",coolmajorPlans.toString());
        model.addAttribute("coolmajorNameArr",coolmajorNames.toString());
        model.addAttribute("areaPlanData",areaPlanData.toString());
        model.addAttribute("provincePlanData",provincePlanData);
        model.addAttribute("user", session.getAttribute("user"));
        return "index";
    }


}
