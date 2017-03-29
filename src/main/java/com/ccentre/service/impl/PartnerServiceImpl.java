package com.ccentre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import com.ccentre.entity.*;
import com.ccentre.repository.*;
import com.ccentre.service.*;

@Service
public class PartnerServiceImpl implements PartnerService {
    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    @Transactional(readOnly = true)
    public Partner getPartnerById(long id) {
        return partnerRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Partner> listPartner() {
        return partnerRepository.findAll();
    }

    @Override
    @Transactional
    public void deletePartnerById(long[] ids) {
        for (long id:
             ids) {
            partnerRepository.delete(id);
        }
    }

    @Override
    @Transactional
    public void addPartner(Partner partner) {
        partnerRepository.save(partner);
    }

    @Override
    @Transactional
    public void updatePartner(Partner partner) {
        partnerRepository.save(partner);
    }
}
