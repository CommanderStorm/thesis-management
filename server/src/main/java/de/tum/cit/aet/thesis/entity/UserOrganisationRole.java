package de.tum.cit.aet.thesis.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import de.tum.cit.aet.thesis.entity.key.UserOrganisationRoleId;

@Getter
@Setter
@Entity
@Table(name = "user_organisation_role")
public class UserOrganisationRole {
    @EmbeddedId
    private UserOrganisationRoleId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @MapsId("organisationId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organisation_id", nullable = false)
    private Organisation organisation;
}