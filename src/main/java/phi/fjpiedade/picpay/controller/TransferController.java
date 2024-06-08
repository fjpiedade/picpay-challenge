package phi.fjpiedade.picpay.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import phi.fjpiedade.picpay.client.dto.TransferDTO;
import phi.fjpiedade.picpay.entity.Transfer;
import phi.fjpiedade.picpay.service.TransferService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/tranfer")
    public ResponseEntity<Transfer> transfer(@RequestBody @Valid TransferDTO transferDTO) {
        var resp = transferService.transfer(transferDTO);
        return ResponseEntity.ok(resp);
    }

}
