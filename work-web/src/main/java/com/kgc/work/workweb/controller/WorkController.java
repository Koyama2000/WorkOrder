package com.kgc.work.workweb.controller;

import com.kgc.work.bean.Project;
import com.kgc.work.bean.WorkOrder;
import com.kgc.work.service.ProjectService;
import com.kgc.work.service.WorkOrderService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class WorkController {
    @Reference
    WorkOrderService workOrderService;
    @Reference
    ProjectService projectService;

    @RequestMapping("/project/list")
    @ResponseBody
    public List<Project> projects(){
        List<Project> projects=projectService.ProjectList();
        return projects;
    }

    @RequestMapping("/work/list")
    @ResponseBody
    public List<WorkOrder> WorkOrderList(Integer projectid){
        List<WorkOrder> list=workOrderService.WorkOrderByProId(projectid);
        return list;
    }

    @RequestMapping("/work/add")
    @ResponseBody
    public int WorkAdd(@RequestBody WorkOrder workOrder){
        workOrder.setCreatedate(new Date());
        int result=workOrderService.WorkAdd(workOrder);
        return result;
    }
}
