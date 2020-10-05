package com.kgc.work;

import com.kgc.work.bean.Project;
import com.kgc.work.bean.WorkOrder;
import com.kgc.work.workservice.mapper.ProjectMapper;
import com.kgc.work.workservice.mapper.WorkOrderMapper;
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class WorkServiceApplicationTests {
    @Resource
    WorkOrderMapper workOrderMapper;
    @Resource
    ProjectMapper projectMapper;
    @Resource
    JestClient jestClient;
    @Test
    void contextLoads() {
        List<WorkOrder> allwork = workOrderMapper.selectByExample(null);
        System.out.println("assetslist:"+allwork);
        List<WorkOrder> workorderInfos=new ArrayList<>();
        for (WorkOrder work : allwork) {
            WorkOrder works = new WorkOrder();
            BeanUtils.copyProperties(work,works);
            workorderInfos.add(works);
        }
        for (WorkOrder work : workorderInfos) {
            Index index=new Index.Builder(work).index("worko").type("workorder").id(work.getId()+"").build();
            try {
                jestClient.execute(index);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void contextLoads2() {
        List<Project> allpro = projectMapper.selectByExample(null);
        System.out.println("projectlist:"+allpro);
        List<Project> projectinfos=new ArrayList<>();
        for (Project project : allpro) {
            Project projects = new Project();
            BeanUtils.copyProperties(project,projects);
            projectinfos.add(projects);
        }
        for (Project project : projectinfos) {
            Index index=new Index.Builder(project).index("work").type("project").id(project.getId()+"").build();
            try {
                jestClient.execute(index);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
