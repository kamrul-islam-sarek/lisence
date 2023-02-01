package net.spectrum.api.caams.license.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * A DTO for the {@link net.spectrum.api.caams.license} entity.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LicenseDto implements Serializable {

    private String licenseOid;

    private String organizationOid;

    private String manufacturerOid;

    private String vendorOid;

   private String assetCategoryOid;

  private String licenseName;

  private String licenseContact;

  private String licenseSerial;

  private Long licensePurchasedSeats;

  private Long licenseAvailableSeats;

    private String licensePurchaseNumber;

    private BigDecimal licensePurchaseCost;

    private Date licensePurchaseDate;

    private String licenseRemarks;

}
