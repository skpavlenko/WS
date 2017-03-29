package com.ccentre.service;

import java.util.List;
import com.ccentre.entity.*;

public interface PartnerService {
    Partner getPartnerById(long id);
    void addPartner(Partner partner);
    void updatePartner(Partner partner);
    void deletePartnerById(long[] ids);
    List<Partner> listPartner();
}
