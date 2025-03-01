package de.tum.cit.aet.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import de.tum.cit.aet.thesis.entity.UserOrganisationRole;

import java.util.UUID;

@Repository
public interface UserOrganisationRoleRepository extends JpaRepository<UserOrganisationRole, UserOrganisationRoleId> {
    void deleteByUserId(UUID id);
}
