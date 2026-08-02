package com.info.demo.service.impl.common;

import com.info.demo.entity.MbkBrn;
import com.info.demo.repository.MbkBrnRepository;
import com.info.demo.service.common.MbkBrnService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MbkBrnServiceImpl implements MbkBrnService {


    private final MbkBrnRepository mbkBrnRepository;

    public MbkBrnServiceImpl(MbkBrnRepository mbkBrnRepository) {
        this.mbkBrnRepository = mbkBrnRepository;
    }

    @Override
    public List<MbkBrn> findAll() {
        return mbkBrnRepository.findAll();
    }




}
