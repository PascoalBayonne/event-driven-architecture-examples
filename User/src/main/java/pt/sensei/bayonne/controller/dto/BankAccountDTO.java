package pt.sensei.bayonne.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public record BankAccountDTO(@NotBlank(message = "user first name cannot be blank") String firstname,
                             @NotBlank String lastname, @Positive Integer age) {
}
