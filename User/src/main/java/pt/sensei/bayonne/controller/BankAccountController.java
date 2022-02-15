package pt.sensei.bayonne.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.sensei.bayonne.controller.dto.BankAccountDTO;
import pt.sensei.bayonne.controller.mapper.BankAccountMapper;
import pt.sensei.bayonne.domain.User;
import pt.sensei.bayonne.service.BankAccountService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/account")
@Slf4j
@RequiredArgsConstructor
public class BankAccountController {
    private final BankAccountService bankAccountService;
    private final BankAccountMapper bankAccountMapper;

    @PostMapping
    public ResponseEntity<BankAccountDTO> create(@RequestBody @Valid final BankAccountDTO bankAccountDTO) {
        log.info(bankAccountDTO.toString());
        User user = bankAccountMapper.mapTo(bankAccountDTO);
        bankAccountService.create(user);
        return ResponseEntity.ok(bankAccountDTO);
    }
}
