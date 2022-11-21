package com.msbills.service;

import com.msbills.api.model.UserDTO;
import com.msbills.api.service.UserService;
import com.msbills.models.Bill;
import com.msbills.models.BillDTO;
import com.msbills.repositories.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {

  private final BillRepository repository;
  private final UserService userService;

  public List<Bill> getAllBill() {
    return repository.findAll();
  }

  public Bill saveBill(Bill bill) {
    return repository.save(bill);
  }

  public List<Bill> findByCustomer(String customer) {
    return repository.findByCustomerBill(customer);
  }

  public UserDTO userFindById(String userId){
    return userService.findById(userId);
  }

  public List<BillDTO> billsByUsername(String username){

    List<Bill> billList = findByCustomer(username);
    List<BillDTO> billDTOList = new ArrayList<>();
    billList.stream().iterator().forEachRemaining(
            bill -> billDTOList.add(billToDTO(bill))
    );
    return billDTOList;
  }

  public BillDTO billToDTO(Bill bill){
    BillDTO billDTO = new BillDTO();
    billDTO.setBillId(bill.getIdBill());
    billDTO.setBillingDate(bill.getBillingDate());
    billDTO.setTotalPrice(bill.getTotalPrice());
    try {

      billDTO.setUser(userService.findByUsername(bill.getCustomerBill()));
    }catch (Exception e){
      billDTO.setUser(null);
    }
    return billDTO;
  }


}
