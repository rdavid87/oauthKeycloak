package com.msbills.controller;

import com.msbills.api.model.UserDTO;
import com.msbills.models.Bill;
import com.msbills.models.BillDTO;
import com.msbills.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
public class BillController {

  private final BillService service;


  @GetMapping("/info")
  public String getInfo() {
    return "Se ha conectado al microservicio de Bills!";
  }

  @GetMapping("/all")
  public ResponseEntity<List<Bill>> getAll() {
    return ResponseEntity.ok().body(service.getAllBill());
  }


  @PostMapping()
  public ResponseEntity<Bill> saveBill(@RequestBody Bill bill) {
    return ResponseEntity.ok().body(service.saveBill(bill));
  }

  @GetMapping("/findBy")
  public List<Bill> findByCustomer(@RequestParam String customer) {
    List<Bill> bills = service.findByCustomer(customer);
    return bills;
  }

  @GetMapping("/userFindById/{userId}")
  public UserDTO userFindById(@PathVariable String userId){
    return service.userFindById(userId);
  }

  @GetMapping("/detail/{username}")
  public List<BillDTO> userFindByUsername(@PathVariable String username){
    return service.billsByUsername(username);
  }

}
