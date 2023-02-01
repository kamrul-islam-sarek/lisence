package net.spectrum.api.caams.license.repository;

import com.google.common.base.Strings;
import net.spectrum.api.caams.assetcategory.dto.AssetCategoryDto;
import net.spectrum.api.caams.assetsubcategory.dto.AssetSubCategoryDto;
import net.spectrum.api.caams.license.dto.LicenseDto;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
public class LicenseCustomRepository {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    public List<LicenseDto> getLicenseList(String userId, int limit, int offset, String searchText) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String sql = "";

        sql = " select asct.licenseOid as licenseOid,";
        sql += " asct.assetCategoryOid as assetCategoryOid,";
        sql += " asct.licenseName as licenseName, asct.licenseAvailableSeats as licenseAvailableSeats, asct.licensePurchasedSeats as licensePurchasedSeats";
        sql += " from LicenseEntity asct";
        sql += " where 1=1";

        if (!Strings.isNullOrEmpty(searchText)
                && !searchText.equalsIgnoreCase("undefined")) {
            sql += " AND (LOWER(asct.licenseOid) like '%" + searchText.trim().toLowerCase()
                    + "%' OR" + " LOWER(asct.licenseName) like '%" + searchText.trim().toLowerCase()
                    + "%')";
        }
//        sql += " order by uh.createdOn asc";

        Query query = entityManager.createQuery(sql);
        query.setFirstResult(offset * limit);
        query.setMaxResults(limit);

        List<LicenseDto> list = query.unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(new AliasToBeanResultTransformer(LicenseDto.class)).list();

        entityManager.close();
        return list;
    }

    public int getAllListCount(String userId) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String sql = "";
        sql = " select count(asct.licenseOid) as count";
        sql += " from LicenseEntity asct";
        sql += " where 1=1";

        Query query = entityManager.createQuery(sql);
        Long count = (Long) query.getSingleResult();
        entityManager.close();
        return count.intValue();
    }

}
