package phi.fjpiedade.picpay.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import phi.fjpiedade.picpay.client.dto.TransferDTO;
import phi.fjpiedade.picpay.entity.Transfer;
import phi.fjpiedade.picpay.entity.Wallet;
import phi.fjpiedade.picpay.exception.InsufficientBalanceException;
import phi.fjpiedade.picpay.exception.TransferNotAllowedForWalletTypeException;
import phi.fjpiedade.picpay.exception.TransferNotAuthorizedException;
import phi.fjpiedade.picpay.exception.WalletNotFoundException;
import phi.fjpiedade.picpay.repository.TransferRepository;
import phi.fjpiedade.picpay.repository.WalletRepository;

@Service
public class TransferService {
    private final TransferRepository transferRepository;
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;
    private final WalletRepository walletRepository;

    public TransferService(
            TransferRepository transferRepository,
            AuthorizationService authorizationService,
            NotificationService notificationService,
            WalletRepository walletRepository) {
        this.transferRepository = transferRepository;
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
        this.walletRepository = walletRepository;
    }

    @Transactional
    public Transfer transfer(TransferDTO transferTDO) {
        var sender = walletRepository.findById(transferTDO.payer())
                .orElseThrow(() -> new WalletNotFoundException(transferTDO.payer()));

        var receiver = walletRepository.findById(transferTDO.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferTDO.payee()));

        validateTransfer(transferTDO, sender);

        // debit
        sender.debit(transferTDO.value());

        // credit
        receiver.credit(transferTDO.value());

        var transfer = new Transfer(sender, receiver, transferTDO.value());

        walletRepository.save(sender);
        walletRepository.save(receiver);
        var transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));

        return transferResult;
    }

    private void validateTransfer(TransferDTO transferTDO, Wallet sender) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'validateTransfer'");
        if (!sender.isTransferAllowedWalletType()) {
            throw new TransferNotAllowedForWalletTypeException();
        }

        if (!sender.isBalanceEqualOrGreaterThan(transferTDO.value())) {
            throw new InsufficientBalanceException(transferTDO.value());
        }

        if (!authorizationService.isAuthorized(transferTDO)) {
            throw new TransferNotAuthorizedException();
        }
    }
}
