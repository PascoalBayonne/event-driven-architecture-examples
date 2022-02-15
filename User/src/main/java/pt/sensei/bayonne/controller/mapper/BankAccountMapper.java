package pt.sensei.bayonne.controller.mapper;


import org.mapstruct.Mapper;
import pt.sensei.bayonne.controller.dto.BankAccountDTO;
import pt.sensei.bayonne.domain.User;
import pt.sensei.bayonne.messaging.event.UserCreatedEvent;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {


    default User mapTo(BankAccountDTO bankAccountDTO) {
        User user = new User();
        if (Objects.nonNull(bankAccountDTO)) {
            user.setAge(bankAccountDTO.age());
            user.setFirstname(bankAccountDTO.firstname());
            user.setLastname(bankAccountDTO.lastname());
        }
        return user;
    }

    BankAccountDTO mapTo(User user);

    UserCreatedEvent mapToUserCreatedEvent(User user);
}
