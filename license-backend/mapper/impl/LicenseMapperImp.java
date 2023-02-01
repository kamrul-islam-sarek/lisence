package net.spectrum.api.caams.license.mapper.impl;

import net.spectrum.api.caams.license.dto.LicenseDto;
import net.spectrum.api.caams.license.entity.LicenseEntity;
import net.spectrum.api.caams.license.mapper.LicenseMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LicenseMapperImp implements LicenseMapper {

    @Override
    public LicenseEntity toEntity(LicenseDto dto) {
        if (dto == null) {
            return null;
        }

        LicenseEntity licenseEntity = new LicenseEntity();

        licenseEntity.setLicenseOid(dto.getLicenseOid());
        licenseEntity.setOrganizationOid(dto.getOrganizationOid());
        licenseEntity.setManufacturerOid(dto.getManufacturerOid());
        licenseEntity.setVendorOid(dto.getVendorOid());
        licenseEntity.setAssetCategoryOid(dto.getAssetCategoryOid());
        licenseEntity.setLicenseName(dto.getLicenseName());
        licenseEntity.setLicenseContact(dto.getLicenseContact());
        licenseEntity.setLicenseSerial(dto.getLicenseSerial());
        licenseEntity.setLicensePurchasedSeats(dto.getLicensePurchasedSeats());
        licenseEntity.setLicenseAvailableSeats(dto.getLicenseAvailableSeats());
        licenseEntity.setLicensePurchaseNumber(dto.getLicensePurchaseNumber());
        licenseEntity.setLicensePurchaseCost(dto.getLicensePurchaseCost());
        licenseEntity.setLicensePurchaseDate(dto.getLicensePurchaseDate());
        licenseEntity.setLicenseRemarks(dto.getLicenseRemarks());
        return licenseEntity;
    }

    @Override
    public LicenseDto toDto(LicenseEntity entity) {
        if (entity == null) {
            return null;
        }

        LicenseDto licenseDTO = new LicenseDto();

        licenseDTO.setLicenseOid(entity.getLicenseOid());
        licenseDTO.setOrganizationOid(entity.getOrganizationOid());
        licenseDTO.setManufacturerOid(entity.getManufacturerOid());
        licenseDTO.setVendorOid(entity.getVendorOid());
        licenseDTO.setAssetCategoryOid(entity.getAssetCategoryOid());
        licenseDTO.setLicenseName(entity.getLicenseName());
        licenseDTO.setLicenseContact(entity.getLicenseContact());
        licenseDTO.setLicenseSerial(entity.getLicenseSerial());
        licenseDTO.setLicensePurchasedSeats(entity.getLicensePurchasedSeats());
        licenseDTO.setLicenseAvailableSeats(entity.getLicenseAvailableSeats());
        licenseDTO.setLicensePurchaseNumber(entity.getLicensePurchaseNumber());
        licenseDTO.setLicensePurchaseCost(entity.getLicensePurchaseCost());
        licenseDTO.setLicensePurchaseDate(entity.getLicensePurchaseDate());
        licenseDTO.setLicenseRemarks(entity.getLicenseRemarks());

        return licenseDTO;
    }

    @Override
    public List<LicenseEntity> toEntity(List<LicenseDto> dtoList) {
        if (dtoList == null) {
            return null;
        }

        List<LicenseEntity> list = new ArrayList<LicenseEntity>(dtoList.size());
        for (LicenseDto licenseDTO : dtoList) {
            list.add(toEntity(licenseDTO));
        }

        return list;
    }

    @Override
    public List<LicenseDto> toDto(List<LicenseEntity> entityList) {
        if (entityList == null) {
            return null;
        }

        List<LicenseDto> list = new ArrayList<LicenseDto>(entityList.size());
        for (LicenseEntity licenseEntity : entityList) {
            list.add(toDto(licenseEntity));
        }

        return list;
    }

    @Override
    public void partialUpdate(LicenseEntity entity, LicenseDto dto) {
        if (dto == null) {
            return;
        }

        if (dto.getLicenseOid() != null) {
            entity.setLicenseOid(dto.getLicenseOid());
        }
        if (dto.getOrganizationOid() != null) {
            entity.setOrganizationOid(dto.getOrganizationOid());
        }
        if (dto.getManufacturerOid() != null) {
            entity.setManufacturerOid(dto.getManufacturerOid());
        }
        if (dto.getVendorOid() != null) {
            entity.setVendorOid(dto.getVendorOid());
        }
        if (dto.getAssetCategoryOid() != null) {
            entity.setAssetCategoryOid(dto.getAssetCategoryOid());
        }
        if (dto.getLicenseName() != null) {
            entity.setLicenseName(dto.getLicenseName());
        }
        if (dto.getLicenseContact() != null) {
            entity.setLicenseContact(dto.getLicenseContact());
        }
        if (dto.getLicenseSerial() != null) {
            entity.setLicenseSerial(dto.getLicenseSerial());
        }
        if (dto.getLicensePurchasedSeats() != null) {
            entity.setLicensePurchasedSeats(dto.getLicensePurchasedSeats());
        }
        if (dto.getLicenseAvailableSeats() != null) {
            entity.setLicenseAvailableSeats(dto.getLicenseAvailableSeats());
        }
        if (dto.getLicensePurchaseNumber() != null) {
            entity.setLicensePurchaseNumber(dto.getLicensePurchaseNumber());
        }
        if (dto.getLicensePurchaseCost() != null) {
            entity.setLicensePurchaseCost(dto.getLicensePurchaseCost());
        }
        if (dto.getLicensePurchaseDate() != null) {
            entity.setLicensePurchaseDate(dto.getLicensePurchaseDate());
        }
        if (dto.getLicenseRemarks() != null) {
            entity.setLicenseRemarks(dto.getLicenseRemarks());
        }
    }
}
