package com.info.demo.service.impl.common;

import com.info.demo.entity.MbkBrn;
import com.info.demo.repository.MbkBrnRepository;
import com.info.demo.service.common.MbkBrnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MbkBrnServiceImpl implements MbkBrnService {


    @Autowired
    private MbkBrnRepository mbkBrnRepository;

    @Override
    public List<MbkBrn> findAll() {
        return mbkBrnRepository.findAll();
    }




}
