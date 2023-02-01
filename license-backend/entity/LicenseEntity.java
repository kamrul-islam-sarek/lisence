package net.spectrum.api.caams.license.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
 * A License.
 */
@Entity
@Table(name = "license")
@Getter
@Setter
public class LicenseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
//    @SequenceGenerator(name = "sequenceGenerator")
//    @Column(name = "id")
//    private Long id;

    @NotNull
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "license_oid")
    private String licenseOid;

    @NotNull
    @Size(max = 128)
    @Column(name = "organization_oid", length = 128, nullable = false)
    private String organizationOid;

    @NotNull
    @Size(max = 128)
    @Column(name = "manufacturer_oid", length = 128, nullable = false)
    private String manufacturerOid;

    @Size(max = 128)
    @Column(name = "vendor_oid", length = 128)
    private String vendorOid;

    @NotNull
    @Size(max = 128)
    @Column(name = "asset_category_oid", length = 128, nullable = false)
    private String  assetCategoryOid;

    @Size(max = 128)
    @Column(name = "license_name", length = 128)
    private String licenseName;

    @Size(max = 128)
    @Column(name = "license_contact", length = 128)
    private String licenseContact;

    @Size(max = 128)
    @Column(name = "license_serial", length = 128)
    private String licenseSerial;

    @NotNull
    @Column(name = "license_purchased_seats", nullable = false)
    private Long licensePurchasedSeats;


    @Column(name = "license_available_seats")
    private Long licenseAvailableSeats;

    @Size(max = 128)
    @Column(name = "license_purchase_number", length = 128)
    private String licensePurchaseNumber;

    @NotNull
    @Column(name = "license_purchase_cost", precision = 21, scale = 2, nullable = false)
    private BigDecimal licensePurchaseCost;

    @Column(name = "license_purchase_date")
    private Date licensePurchaseDate;


    @Column(name = "license_remarks", length = 128)
    private String licenseRemarks;

    @NotNull
    @Size(max = 128)
    @Column(name = "created_by", length = 128, nullable = false)
    private String createdBy;

    @Column(name = "created_on")
    private Timestamp createdOn;

    @Size(max = 128)
    @Column(name = "updated_by", length = 128)
    private String updatedBy;

    @Column(name = "updated_on")
    private Timestamp updatedOn;

    // jhipster-needle-entity-add-field - JHipster will add fields here
}
