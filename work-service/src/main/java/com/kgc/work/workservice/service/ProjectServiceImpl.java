package com.kgc.work.workservice.service;

import com.kgc.work.bean.Project;
import com.kgc.work.service.ProjectService;
import com.kgc.work.util.RedisUtil;
import com.kgc.work.workservice.mapper.ProjectMapper;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.apache.dubbo.config.annotation.Service;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.redisson.api.RedissonClient;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Resource
    ProjectMapper projectMapper;
    @Resource
    RedisUtil redisUtil;
    @Resource
    RedissonClient redissonClient;
    @Resource
    JestClient jestClient;

    @Override
    public List<Project> ProjectList() {
        List<Project> list=new ArrayList<>();
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder=new BoolQueryBuilder();
        String dsl=searchSourceBuilder.toString();
        Search search=new Search.Builder(dsl).addIndex("work").addType("project").build();
        try {
            SearchResult searchResult=jestClient.execute(search);
            List<SearchResult.Hit<Project,Void>> hits=searchResult.getHits(Project.class);
            for (SearchResult.Hit<Project,Void> hit: hits){

                Project pro=hit.source;
                list.add(pro);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
