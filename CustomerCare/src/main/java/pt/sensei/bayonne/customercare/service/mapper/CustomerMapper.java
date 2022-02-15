package pt.sensei.bayonne.customercare.service.mapper;

import org.mapstruct.Mapper;
import pt.sensei.bayonne.customercare.domain.Customer;
import pt.sensei.bayonne.customercare.messaging.dto.CustomerDTO;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer map(CustomerDTO customerDTO);
}
