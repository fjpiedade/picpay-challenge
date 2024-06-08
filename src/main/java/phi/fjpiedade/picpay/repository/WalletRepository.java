package phi.fjpiedade.picpay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import phi.fjpiedade.picpay.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByCpfCnpjOrEmail(String cpfCnpj, String email);

}
