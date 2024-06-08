package phi.fjpiedade.picpay.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import phi.fjpiedade.picpay.entity.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {

}
