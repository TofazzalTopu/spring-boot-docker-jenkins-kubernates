package com.info.demo.service.common;

import com.info.demo.entity.Branch;

import java.util.List;

public interface BranchService {

    List<Branch> findAll();
    List<Integer> findAllBranchRoutingNumbers();
}
