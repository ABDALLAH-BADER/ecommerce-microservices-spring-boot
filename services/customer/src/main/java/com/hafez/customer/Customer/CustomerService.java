package com.hafez.customer.Customer;

import com.hafez.customer.exception.CustomerNotFoundException;
import io.micrometer.common.util.StringUtils;
import jdk.dynalink.linker.LinkerServices;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;
    public String createCustomer(CustomerRequest request) {
        var Customer = repository.save(mapper.toCustomer(request));
        return Customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {

        var customer = repository.findById(request.id())
                                .orElseThrow(() -> new CustomerNotFoundException(
                                format("Cannot update customer: no customer found with id " + request.id())));
        mergerCustomer(customer,request);
        repository.save(customer);
    }

    private void mergerCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstname())){
            customer.setFirstname(request.firstname());
        }
        if(StringUtils.isNotBlank(request.lastname())){
            customer.setLastname(request.lastname());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.address() != null){
            customer.setAddress(request.address());
        }

    }



    public List<CustomerResponse> findAllCustomers() {
        //****
        return repository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }
    public Boolean existsById(String customerId) {
        //****
        return repository.findById(customerId)
                .isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return repository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(()-> new CustomerNotFoundException(format("No customer found with the provided Id :: %s",customerId)));
    }

    public void deleteCustomer(String customerId) {
        repository.deleteById(customerId);
    }
}
