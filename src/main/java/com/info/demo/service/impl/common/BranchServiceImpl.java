package com.info.demo.service.impl.common;

import com.info.demo.entity.Branch;
import com.info.demo.repository.BranchRepository;
import com.info.demo.service.common.BranchService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }


    @Override
    public List<Branch> findAll() {
        return branchRepository.findAll();
    }

    @Override
    public List<Integer> findAllBranchRoutingNumbers() {
        return findAll().stream().map(Branch::getRoutingNumber).collect(Collectors.toList());
    }
}
