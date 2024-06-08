package phi.fjpiedade.picpay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import phi.fjpiedade.picpay.dto.CreateWalletDto;
import phi.fjpiedade.picpay.entity.Wallet;
import phi.fjpiedade.picpay.service.WalletService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody @Valid CreateWalletDto walletDTO) {
        var wallet = walletService.createWallet(walletDTO);
        return ResponseEntity.ok(wallet);
    }
}
