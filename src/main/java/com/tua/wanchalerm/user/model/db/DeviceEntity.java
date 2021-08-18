package com.tua.wanchalerm.user.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author wanchalermyuphasuk
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "device")
public class DeviceEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(nullable = false, length = 256)
    private String id;

    @Column(nullable = false, length = 64)
    private String passcode;

    @Column(name = "push_notification_token", length = 256)
    private String pushNotificationToken;

    @Column(nullable = false, length = 20)
    private String os;

    @Column(name = "os_version", nullable = false, length = 5)
    private String osVersion;

    @Column(nullable = false, length = 50)
    private String brand;

    @Column(nullable = false, length = 50)
    private String model;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;

    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserEntity userEntity;

    public DeviceEntity(String id) {
        this.id = id;
    }

}
