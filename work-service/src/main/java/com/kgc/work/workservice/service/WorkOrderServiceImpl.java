package com.kgc.work.workservice.service;

import com.kgc.work.bean.WorkOrder;
import com.kgc.work.service.WorkOrderService;
import com.kgc.work.util.RedisUtil;
import com.kgc.work.workservice.mapper.WorkOrderMapper;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.apache.dubbo.config.annotation.Service;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WorkOrderServiceImpl implements WorkOrderService {
    @Resource
    WorkOrderMapper workOrderMapper;
    @Resource
    RedisUtil redisUtil;
    @Resource
    RedissonClient redissonClient;
    @Resource
    JestClient jestClient;
    
    @Override
    public List<WorkOrder> WorkOrderByProId(Integer proid) {
        List<WorkOrder> list=new ArrayList<>();

        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder=new BoolQueryBuilder();
            MatchQueryBuilder matchQueryBuilder=new MatchQueryBuilder("projectid",proid);
            boolQueryBuilder.must(matchQueryBuilder);
        String dsl=searchSourceBuilder.toString();
        Search search=new Search.Builder(dsl).addIndex("work").addType("workorder").build();
        try {
            SearchResult searchResult=jestClient.execute(search);
            List<SearchResult.Hit<WorkOrder,Void>> hits=searchResult.getHits(WorkOrder.class);
            for (SearchResult.Hit<WorkOrder,Void> hit: hits){
                list.add(hit.source);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int WorkAdd(WorkOrder workOrder) {
        int result=workOrderMapper.insertSelective(workOrder);
        if(result>0){
            this.setEs();
        }
        return result;
    }

    public void setEs(){
        List<WorkOrder> allwork = workOrderMapper.selectByExample(null);
        System.out.println("workorderlist:"+allwork);
        List<WorkOrder> assetsInfos=new ArrayList<>();
        for (WorkOrder work : allwork) {
            WorkOrder workOrder = new WorkOrder();
            BeanUtils.copyProperties(work,workOrder);
            assetsInfos.add(workOrder);
        }
        System.out.println(assetsInfos);
        for (WorkOrder work : assetsInfos) {
            Index index=new Index.Builder(work).index("work").type("workorder").id(work.getId()+"").build();
            try {
                jestClient.execute(index);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
