package com.train.schoolplanhive.query.controller;


import com.train.schoolplanhive.query.model.AreaPlan;
import com.train.schoolplanhive.query.model.EnrollPlanStatis;
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
    public String showBigScreen(Model model, HttpSession session) throws InterruptedException {


        List<EnrollPlanStatis> professPlanList = bigScreenService.getHotMajors(10);
        List<EnrollPlanStatis> coolProfessPlanList = bigScreenService.getCoolMajors(10);

        model.addAttribute("hotPlan", professPlanList);
        model.addAttribute("coolPlan", coolProfessPlanList);

        StringBuffer hotmajorPlans = new StringBuffer("[");
        StringBuffer hotmajorNames = new StringBuffer("[");
//        for (int i = 0; i < professPlanList.size(); i++) {
//            ProfessPlan professPlan = professPlanList.get(i);
//
//            hotmajorNames.append("\"");
//            hotmajorNames.append(professPlan.getProfess());
//            hotmajorNames.append("\",");
//
//            hotmajorPlans.append(professPlan.getPlan());
//            hotmajorPlans.append(",");
//
//
//        }
//        hotmajorNames.append("]");
//        hotmajorPlans.append("]");

        StringBuffer coolmajorPlans = new StringBuffer("[");
        StringBuffer coolmajorNames = new StringBuffer("[");
//        for (int i = 0; i < coolProfessPlanList.size(); i++) {
//            ProfessPlan professPlan = coolProfessPlanList.get(i);
//
//            coolmajorNames.append("\"");
//            coolmajorNames.append(professPlan.getProfess());
//            coolmajorNames.append("\",");
//
//            coolmajorPlans.append(professPlan.getPlan());
//            coolmajorPlans.append(",");
//
//
//        }
//        coolmajorNames.append("]");
//        coolmajorPlans.append("]");

/*
        组装区域计划数据：
        [
                    {value: 297541, name: '东北'},
                    {value: 1292768, name: '华东'},
                    {value: 687938, name: '华中'},
                    {value: 514528, name: '华北'},
                    {value: 534479, name: '华南'},
                    {value: 363941, name: '西北'},
                    {value: 637507, name: '西南'}

                ]
         */


        List<AreaPlan> areaPlans = bigScreenService.getAllAreaPlan();
        StringBuffer areaPlanData = new StringBuffer("[");

        for (int i = 0; i < areaPlans.size() - 1; i++) {

            AreaPlan areaPlan = areaPlans.get(i);
            areaPlanData.append("{value: ");
            areaPlanData.append(areaPlan.getPlan());
            areaPlanData.append(", name: '");
            areaPlanData.append(areaPlan.getArea());
            areaPlanData.append("'},");
            
        }
        AreaPlan areaPlan = areaPlans.get(areaPlans.size() - 1);
        areaPlanData.append("{value: ");
        areaPlanData.append(areaPlan.getPlan());
        areaPlanData.append(", name: '");
        areaPlanData.append(areaPlan.getArea());
        areaPlanData.append("'}");
        areaPlanData.append("]");

        /**
         * 组装省份招生数据
         *
         [
         {name:"北京",value:35215},
         {name:"天津",value:42196},
         {name:"河北",value:182752},
         {name:"山西",value:152778},
         {name:"内蒙古",value:101587},
         {name:"辽宁",value:103951},
         {name:"吉林",value:84272},
         {name:"黑龙江",value:109318},
         {name:"上海",value:16676},
         {name:"江苏",value:195147},
         {name:"浙江",value:204328},
         {name:"安徽",value:359881},
         {name:"福建",value:107600},
         {name:"江西",value:99041},
         {name:"山东",value:310095},
         {name:"河南",value:307224},
         {name:"湖北",value:205796},
         {name:"湖南",value:174918},
         {name:"重庆",value:105275},
         {name:"四川",value:260928},
         {name:"贵州",value:163191},
         {name:"云南",value:108113},
         {name:"西藏",value:0},
         {name:"陕西",value:150579},
         {name:"甘肃",value:106798},
         {name:"青海",value:19532},
         {name:"宁夏",value:40113},
         {name:"新疆",value:46919},
         {name:"广东",value:345315},
         {name:"广西",value:152496},
         {name:"海南",value:36668}
         ]
         */

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
        System.out.println(provincePlans);
        provincePlanData.append("]");

        model.addAttribute("provincePlans", provincePlans);
        model.addAttribute("areaPlanList", areaPlans);
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
