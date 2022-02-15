package pt.sensei.bayonne.customercare.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pt.sensei.bayonne.customercare.domain.Customer;

@Repository
public interface CustomerRepository extends R2dbcRepository<Customer, Long> {
}
