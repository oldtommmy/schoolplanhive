package com.train.schoolplanhive.query.controller;

import com.github.pagehelper.PageInfo;
import com.train.schoolplanhive.query.model.AreaPlan;
import com.train.schoolplanhive.query.model.EnrollPlanStatis;
import com.train.schoolplanhive.query.model.EnrollmentPlan;
import com.train.schoolplanhive.query.service.AreaPlanService;
import com.train.schoolplanhive.query.service.EnrollmentPlanService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * create by xiuzhong.li at 2022/4/1
 *
 * @Description: 文件描述
 */
@Controller
@RequestMapping("query")
public class QueryController {
    private final Logger logger = LoggerFactory.getLogger(QueryController.class);

    @Autowired
    private EnrollmentPlanService enrollmentPlanService;

    @Autowired
    private AreaPlanService areaPlanService;

    @RequestMapping("/byarea.html")
    public String toByArea(Model model) {
        List<AreaPlan> plan = areaPlanService.getAreaPlan();
        model.addAttribute("areaPlanList", plan);
        return "byarea";
    }


    @RequestMapping("/groupbyschool")
    public String groupBySchool(@RequestParam(value = "pageNo",defaultValue = "1") int pageNo,
                                @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                Model model, HttpSession session) {
        PageInfo<EnrollmentPlan> pageInfo = null;
        EnrollmentPlan queryCondition = (EnrollmentPlan) session.getAttribute("queryCondition");
        pageInfo = enrollmentPlanService.getEnrollmentPlanGroupBySchool(pageNo, pageSize, queryCondition);
        session.setAttribute("queryCondition", queryCondition);
        model.addAttribute("pageInfo", pageInfo);
        return "myquery";
    }

    @RequestMapping("/groupbysubject")
    public String groupBySubject(@RequestParam(value = "pageNo",defaultValue = "1") int pageNo,
                                @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                Model model, HttpSession session) {
        PageInfo<EnrollmentPlan> pageInfo = null;
        EnrollmentPlan queryCondition = (EnrollmentPlan) session.getAttribute("queryCondition");
        pageInfo = enrollmentPlanService.getEnrollmentPlanGroupBySubject(pageNo, pageSize, queryCondition);
        session.setAttribute("queryCondition", queryCondition);
        model.addAttribute("pageInfo", pageInfo);
        return "myquery";
    }

    @RequestMapping("/groupbyprovince")
    public String groupByProvince(@RequestParam(value = "pageNo",defaultValue = "1") int pageNo,
                                 @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                 Model model, HttpSession session) {
        PageInfo<EnrollmentPlan> pageInfo = null;
        EnrollmentPlan queryCondition = (EnrollmentPlan) session.getAttribute("queryCondition");
        pageInfo = enrollmentPlanService.getEnrollmentPlanGroupByProvince(pageNo, pageSize, queryCondition);
        session.setAttribute("queryCondition", queryCondition);
        model.addAttribute("pageInfo", pageInfo);
        return "myquery";
    }


    @RequestMapping("myquery.html")
    public String toEnrollMentPlanQuery(@RequestParam(value = "pageNo",defaultValue = "1") int pageNo,
                                        @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                        @RequestParam(value = "requery",defaultValue = "0") String requery,
                                        EnrollmentPlan enrollmentPlan,
                                        Model model, HttpSession session){

        PageInfo<EnrollmentPlan> pageInfo = null;

        System.out.println("queryCondition="+enrollmentPlan);
        System.out.println(pageNo);

        //if(requery.equals("1")){
        //    session.removeAttribute("queryCondition");
        //}
        if (enrollmentPlan.getSchool() != null) {
            session.setAttribute("queryCondition", enrollmentPlan);
        }
        EnrollmentPlan queryCondition = (EnrollmentPlan) session.getAttribute("queryCondition");

        System.out.println("session:querycondition="+queryCondition);

        if(queryCondition == null){

            if((enrollmentPlan.getSubject() == null || enrollmentPlan.getSubject().equals(""))
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


        model.addAttribute("pageInfo", pageInfo);
        //session.setAttribute("queryCondition",queryCondition);


        return "myquery";
    }



    @RequestMapping("topnmax-index.html")
    public String hotTopNQuery(String school, String province, Model model, HttpSession session){
        System.out.println("to hot top n query>>>>>");
        System.out.println("school=" + school);
        System.out.println("province="+ province);
        if(school != null && school.trim().equals("")){
            school = null;
        }
        if(province != null && province.trim().equals("")){
            province = null;
        }

        List<EnrollPlanStatis> enrollPlanStatisList = enrollmentPlanService.getMajorStatis(school, province,true,10);

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

    @RequestMapping("exportEnrollmentPlanExcel")
    public void exportEnrollmentPlanExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<EnrollmentPlan> list = enrollmentPlanService.getAllPlanList();
        HSSFWorkbook wb = new HSSFWorkbook();
        OutputStream output = response.getOutputStream();
        int total = list.size();// 获取List集合的size
        int mus = 65535;// ：excel表格一个工作表可以存储65536条）
        int avg = total / mus;
        for (int i = 0; i < avg + 1; i++) {
            HSSFSheet sheet = wb.createSheet("招生计划表" + (i + 1));
            HSSFRow row = sheet.createRow(0);
            // 第一行标题
            String[] head = new String[]{"序号", "学校", "省份", "专业名称", "专业代码", "专业大类", "录取批次",
                    "招生人数", "招生年份"};
            int headInt = 0;
            for (String title : head) {
                row.createCell(headInt++).setCellValue(title);
            }
            int num = i * mus;
            int index = 0;
            int rowInt = 1;
            for (int m = num; m < list.size(); m++) {
                if (index == mus) {// 判断index == mus的时候跳出当前for循环
                    break;
                }
                EnrollmentPlan plan = list.get(i);
                // 每列对应的字段
                row = sheet.createRow(rowInt++); // 创建行
                row.createCell(0).setCellValue(plan.getId());
                row.createCell(1).setCellValue(plan.getSchoolCode());
                row.createCell(2).setCellValue(plan.getProvince());
                row.createCell(3).setCellValue(plan.getSubject());
                row.createCell(4).setCellValue(plan.getProfess());
                row.createCell(5).setCellValue(plan.getMajorCode());
                row.createCell(6).setCellValue(plan.getBatch());
                row.createCell(8).setCellValue(plan.getYear());
                row.createCell(7).setCellValue(plan.getPlan());
                index++;
            }
        }
        response.setHeader("Content-Disposition", "attachment;filename=招生计划总表.xls");
        response.setContentType("application/ms-excel");
        wb.write(output);
        output.close();
    }


}
