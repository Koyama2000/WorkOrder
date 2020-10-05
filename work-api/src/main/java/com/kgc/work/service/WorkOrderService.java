package com.kgc.work.service;

import com.kgc.work.bean.WorkOrder;

import java.util.List;

public interface WorkOrderService {
    public List<WorkOrder> WorkOrderByProId(Integer proid);

    public int WorkAdd(WorkOrder workOrder);
}
