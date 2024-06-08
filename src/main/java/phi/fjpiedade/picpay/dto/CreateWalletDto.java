package phi.fjpiedade.picpay.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import phi.fjpiedade.picpay.entity.Wallet;
import phi.fjpiedade.picpay.entity.WalletType;

public record CreateWalletDto(
        @NotBlank String fullname,
        @NotBlank String cpfCnpj,
        @NotBlank String email,
        @NotBlank String password,
        @NotNull WalletType.Enum walletType) {

    public Wallet toWallet() {
        return new Wallet(
                fullname,
                cpfCnpj,
                email,
                password,
                walletType.get());
    }
}
