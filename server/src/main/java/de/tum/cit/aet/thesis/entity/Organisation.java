package de.tum.cit.aet.thesis.entity;

import jakarta.mail.internet.InternetAddress;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "organisations")
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "organisation_id", nullable = false)
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "logo")
    private String logo;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    private Organisation parent;

    @UpdateTimestamp
    @NotNull
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @CreationTimestamp
    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public InternetAddress getEmail() {
        try {
            return new InternetAddress(email);
        } catch (Exception e) {
            return null;
        }
    }

    public String getAdjustedLogo() {
        if (logo != null && !logo.isBlank()) {
            return logo;
        }

        if (email == null) {
            return null;
        }

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] hashInBytes = md.digest(email.trim().toLowerCase().getBytes());

            StringBuilder sb = new StringBuilder();

            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }

            return "https://www.gravatar.com/avatar/" + sb + "?s=400";
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}