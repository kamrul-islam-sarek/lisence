package net.spectrum.api.caams.license.controller;

import net.spectrum.api.caams.assetcategory.dto.AssetCategoryDto;
import net.spectrum.api.caams.license.dto.LicenseDto;
import net.spectrum.api.caams.license.repository.LicenseRepository;
import net.spectrum.api.caams.license.service.LicenseService;
import net.spectrum.api.caams.util.ExceptionHandlerUtil;
import net.spectrum.api.caams.web.rest.error.BadRequestAlertException;
import net.spectrum.api.caams.web.util.HeaderUtil;
import net.spectrum.api.caams.web.util.PaginationUtil;
import net.spectrum.api.caams.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.*;


/**
 * REST controller for managing {@link net.spectrum.api.caams.license.entity.LicenseEntity}.
 */
@RestController
@RequestMapping("/v1/licenses")
public class LicenseController {

    private static final String ENTITY_NAME = "license";
    private final Logger log = LoggerFactory.getLogger(LicenseController.class);
    private final LicenseService licenseService;
    private final LicenseRepository licenseRepository;
    @Value("caams")
    private String applicationName;

    public LicenseController(LicenseService licenseService, LicenseRepository licenseRepository) {
        this.licenseService = licenseService;
        this.licenseRepository = licenseRepository;
    }

    /**
     * {@code POST  /licenses} : Create a new license.
     *
     * @param licenseDTO the licenseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new licenseDTO, or with status {@code 400 (Bad Request)} if the license has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<LicenseDto> createLicense(@Valid @RequestBody LicenseDto licenseDTO) throws URISyntaxException {
        log.debug("REST request to save License : {}", licenseDTO);
        if (!licenseDTO.getLicenseOid().equals("")) {
            throw new BadRequestAlertException("A new license cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LicenseDto result = licenseService.save(licenseDTO);
        return ResponseEntity
                .created(new URI("/api/licenses/" + result.getLicenseOid()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getLicenseOid()))
                .body(result);
    }

//    /**
//     * {@code PUT  /licenses/:id} : Updates an existing license.
//     *
//     * @param licenseOid the id of the licenseDTO to save.
//     * @param licenseDTO the licenseDTO to update.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated licenseDTO,
//     * or with status {@code 400 (Bad Request)} if the licenseDTO is not valid,
//     * or with status {@code 500 (Internal Server Error)} if the licenseDTO couldn't be updated.
//     * @throws URISyntaxException if the Location URI syntax is incorrect.
//     */
    @PutMapping("/{licenseOid}")
    public ResponseEntity<LicenseDto> updateLicense(
            @PathVariable(value = "licenseOid", required = false) final String id,
            @Valid @RequestBody LicenseDto licenseDTO
    ) throws URISyntaxException {
        log.debug("REST request to update License : {}, {}", id, licenseDTO);
        if (licenseDTO.getLicenseOid() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, licenseDTO.getLicenseOid())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }
//        if (!licenseRepository.existsByid(id)) {
//            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
//        }

        LicenseDto result = licenseService.update(licenseDTO);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, licenseDTO.getLicenseOid()))
                .body(result);
    }
//
//    /**
//     * {@code GET  /licenses} : get all the licenses.
//     *
//     * @param pageable the pagination information.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of licenses in body.
//     */
    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getlicenseList(
            @RequestHeader(name = "Authorization") String token,
            @RequestHeader(name = "Request-Id", required = true) @NotEmpty String requestId,
            @RequestHeader(name = "Request-Timeout-In-Seconds", required = true) @NotEmpty String requestTimeoutInSeconds,
            @RequestHeader(name = "Request-Time", required = true) @NotEmpty @Pattern(regexp = "(19|20)[0-9][0-9]-(0[0-9]|1[0-2])-(0[1-9]|([12][0-9]|3[01]))T([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9].([0-9]{3,6})Z", message = "must match yyyy-MM-ddTHH:mm:ss.SSSSSSZ") String requestTime,
            @RequestParam(name = "searchText") String searchText,
            @RequestParam(name = "offset", required = true, defaultValue = "0") int offset,
            @RequestParam(name = "limit", required = true, defaultValue = "20") int limit,
            @AuthenticationPrincipal Principal principal
    ) {
        try {
            log.info("Request received for application list by: {}", principal.getName());
            ResponseEntity<Map<String, Object>> response = licenseService.getLicense(principal.getName(), limit, offset, searchText);
            log.info("Response sent for e-kyc profile list : {}", response);
            return response;
        } catch (ExceptionHandlerUtil ex) {
            log.error(ex.getMessage(), ex);
            throw new ResponseStatusException(ex.getCode(), ex.getMessage(), ex);
        }
    }
//    @GetMapping("/licenses")
//    public ResponseEntity<List<LicenseDto>> getAllLicenses(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
//        log.debug("REST request to get a page of Licenses");
//        Page<LicenseDto> page = licenseService.findAll(pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
//        Map<String, Object> response = new HashMap<>();
//        response.put("data", page);
//        response.put("totalRecords", licenseRepository.count());
//        return ResponseEntity.ok().headers(headers).body(page.getContent());
//    }
    @GetMapping("/licenses")
    public ResponseEntity<Map<String, Object>>getAlllicenseDropdown() {
        log.debug("REST request to get License on Dropdown");
        List<LicenseDto> licenseDto = licenseService.getAlllicenseDropdown() ;
        Map<String, Object> response = new HashMap<>();
        response.put("data", licenseDto);
        response.put("totalRecords", licenseRepository.count());
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    /**
     * {@code GET  /licenses/:id} : get the "id" license.
     *
     * @param id the id of the licenseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the licenseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LicenseDto> getLicense(@PathVariable String id) {
        log.debug("REST request to get License : {}", id);
        Optional<LicenseDto> licenseDTO = licenseService.findByLicenseOid(id);
        return ResponseUtil.wrapOrNotFound(licenseDTO);
    }

    /**
     * {@code DELETE  /licenses/:id} : delete the "id" license.
     *
     * @param id the id of the licenseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLicense(@PathVariable String id) {
        log.debug("REST request to delete License : {}", id);
        licenseService.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id))
                .build();
    }
}
