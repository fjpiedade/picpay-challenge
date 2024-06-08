package phi.fjpiedade.picpay.service;

import org.springframework.stereotype.Service;

import phi.fjpiedade.picpay.dto.CreateWalletDto;
import phi.fjpiedade.picpay.entity.Wallet;
import phi.fjpiedade.picpay.exception.WalletDataAlreadyExistsException;
import phi.fjpiedade.picpay.repository.WalletRepository;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(CreateWalletDto walletDTO) {
        var walletDB = walletRepository.findByCpfCnpjOrEmail(walletDTO.cpfCnpj(), walletDTO.email());
        if (walletDB.isPresent()) {
            throw new WalletDataAlreadyExistsException("CPFCNPJ or Email Already Exist!");
        }
        return walletRepository.save(walletDTO.toWallet());
    }

}
