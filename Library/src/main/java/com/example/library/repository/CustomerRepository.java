package com.example.library.repository;

import com.example.library.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);

    Customer findById(long id);
//    @Query(value = "select * from cutomer where is_activated = true", nativeQuery = true)
//    List<Customer> findAllByActivatedTrue();

}
