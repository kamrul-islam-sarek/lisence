package net.spectrum.api.caams.license.repository;

import net.spectrum.api.caams.license.entity.LicenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data SQL repository for the License entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LicenseRepository extends JpaRepository<LicenseEntity, String> {
    Optional<LicenseEntity> findByLicenseOid(String licenseOid);

//    boolean existsByid(String id);
}
