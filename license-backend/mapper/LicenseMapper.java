package net.spectrum.api.caams.license.mapper;

import net.spectrum.api.caams.common.entitymapper.EntityMapper;
import net.spectrum.api.caams.license.dto.LicenseDto;
import net.spectrum.api.caams.license.entity.LicenseEntity;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link net.spectrum.api.caams.license.entity.LicenseEntity} and its DTO {@link net.spectrum.api.caams.license.dto.LicenseDto}.
 */
@Mapper(componentModel = "spring")
public interface LicenseMapper extends EntityMapper<LicenseDto, LicenseEntity> {
}
