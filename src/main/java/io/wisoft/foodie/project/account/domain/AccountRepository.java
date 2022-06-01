package io.wisoft.foodie.project.account.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,Long> {

    Optional<AccountEntity> findByEmail(final String email);

}
