package net.spectrum.api.caams.license.service;

import io.swagger.models.License;
import net.spectrum.api.caams.assetcategory.dto.AssetCategoryDto;
import net.spectrum.api.caams.assetcategory.entity.AssetCategoryEntity;
import net.spectrum.api.caams.assetcategory.repository.AssetCategoryCustomRepository;
import net.spectrum.api.caams.license.dto.LicenseDto;
import net.spectrum.api.caams.license.entity.LicenseEntity;
import net.spectrum.api.caams.license.mapper.LicenseMapper;
import net.spectrum.api.caams.license.repository.LicenseCustomRepository;
import net.spectrum.api.caams.license.repository.LicenseRepository;
import net.spectrum.api.caams.util.ExceptionHandlerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class LicenseService {

    private final Logger log = LoggerFactory.getLogger(LicenseService.class);

    @Autowired
    private LicenseCustomRepository licenseCustomRepository;
    @Autowired
     private LicenseRepository licenseRepository;

    private final LicenseMapper licenseMapper;

    public LicenseService(LicenseRepository licenseRepository, LicenseMapper licenseMapper) {
        this.licenseRepository = licenseRepository;
        this.licenseMapper = licenseMapper;
    }

    /**
     * Save a license.
     *
     * @param licenseDTO the entity to save.
     * @return the persisted entity.
     */
    public LicenseDto save(LicenseDto licenseDTO) {
        log.debug("Request to save License : {}", licenseDTO);
        LicenseEntity licenseEntity = licenseMapper.toEntity(licenseDTO);
        String requestedBy = SecurityContextHolder.getContext().getAuthentication().getName();
        licenseEntity.setCreatedBy(requestedBy);
        licenseEntity.setCreatedOn(new Timestamp(System.currentTimeMillis()));
        licenseEntity = licenseRepository.save(licenseEntity);
        return licenseMapper.toDto(licenseEntity);
    }

    /**
     * Update a license.
     *
     * @param licenseDTO the entity to save.
     * @return the persisted entity.
     */
    public LicenseDto update(LicenseDto licenseDTO) {
        log.debug("Request to update License : {}", licenseDTO);
        LicenseEntity licenseEntity = licenseMapper.toEntity(licenseDTO);
        licenseEntity.setCreatedOn(licenseRepository.findByLicenseOid(licenseDTO.getLicenseOid()).get().getCreatedOn());
        licenseEntity.setCreatedBy(licenseRepository.findByLicenseOid(licenseDTO.getLicenseOid()).get().getCreatedBy());
        licenseEntity.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
        licenseEntity.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        licenseEntity = licenseRepository.save(licenseEntity);
        licenseEntity = licenseRepository.save(licenseEntity);
        return licenseMapper.toDto(licenseEntity);
    }

//    /**
//     * Get all the licenses.
//     *
//     * @param pageable the pagination information.
//     * @return the list of entities.
//     */

    public ResponseEntity<Map<String, Object>> getLicense (String userId, int limit, int offset, String searchText) throws ExceptionHandlerUtil {
        List<LicenseDto> allList = licenseCustomRepository.getLicenseList(userId, limit, offset, searchText);
        int count = licenseCustomRepository.getAllListCount(userId);
        if (allList.size() == 0) {
            log.error("Error occurred during retrieving all list: all list not found");
            throw new ExceptionHandlerUtil(HttpStatus.NOT_FOUND, "Data not found");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("data", allList);
        response.put("totalRecords", count);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    public List<LicenseDto> getAlllicenseDropdown() {
        log.debug("Request to get all lICENSE");
        List<LicenseEntity> licenseEntities = licenseRepository.findAll();
        return licenseMapper.toDto(licenseEntities);
    }


    /**
     * Get one license by id.
     * @return the entity.
     */

    @Transactional(readOnly = true)
    public Optional<LicenseDto> findByLicenseOid(String licenseOid) {
        log.debug("Request to get License : {}", licenseOid);
        return licenseRepository.findByLicenseOid(licenseOid).map(licenseMapper::toDto);
    }

    /**
     * Delete the license by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete License : {}", id);
        licenseRepository.deleteById(id);
    }

}
