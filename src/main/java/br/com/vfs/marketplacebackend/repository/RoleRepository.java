package br.com.vfs.marketplacebackend.repository;

import br.com.vfs.marketplacebackend.entity.RoleEntity;
import br.com.vfs.marketplacebackend.entity.RoleEntity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(RoleName roleName);
}
