package vn.edu.iuh.fit.labweek_05.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.labweek_05.backend.models.Address;
import vn.edu.iuh.fit.labweek_05.backend.repositories.AddressRepository;

@Service
public class AddressServices {
    @Autowired
    private AddressRepository addressRepository;

    public Address save(Address address) {
        return addressRepository.save(address);
    }
}
