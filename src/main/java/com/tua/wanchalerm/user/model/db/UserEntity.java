package com.tua.wanchalerm.user.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author wanchalermyuphasuk
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email", "mobile_number"})})
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(name = "mobile_number", nullable = false, length = 10)
    private String mobileNumber;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(name = "is_active", nullable = false)
    private boolean active;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userEntity", fetch = FetchType.LAZY)
    private List<DeviceEntity> deviceEntityList;

    public UserEntity(Integer id) {
        this.id = id;
    }

}
