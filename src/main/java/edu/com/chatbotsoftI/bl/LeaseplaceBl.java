package edu.com.chatbotsoftI.bl;

import edu.com.chatbotsoftI.dao.EveAddressRepository;
import edu.com.chatbotsoftI.dao.EveLeasePlaceRepository;
import edu.com.chatbotsoftI.dto.LeasePlaceDto;
import edu.com.chatbotsoftI.entity.EveLeasePlaceEntity;
import edu.com.chatbotsoftI.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaseplaceBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(LeaseplaceBl.class);

    private EveAddressRepository addressRepository;
    private EveLeasePlaceRepository leaseplaceRepository;

    @Autowired
    public LeaseplaceBl (EveAddressRepository addressRepository, EveLeasePlaceRepository leaseplaceRepository){

        this.addressRepository= addressRepository;
        this.leaseplaceRepository= leaseplaceRepository;
    }

    public List<LeasePlaceDto> findAllLeaseplaceDto(){
        List<LeasePlaceDto> leaseplaceDtos= new ArrayList<>();

        for (EveLeasePlaceEntity leaseplace:
            leaseplaceRepository.findAllByStatus(Status.ACTIVE.getStatus())){
            leaseplaceDtos.add(new LeasePlaceDto(leaseplace));
        }
        return leaseplaceDtos;
    }
    public List<EveLeasePlaceEntity> findAllLeasePlace() {
        return leaseplaceRepository.findAllByStatus(Status.ACTIVE.getStatus());}

}
