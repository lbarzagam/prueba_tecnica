package com.pruebatecnica.wastemanagerservice.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pruebatecnica.wastemanagerservice.infra.util.json.GlobalJsonLocalDateDeserializer;
import com.pruebatecnica.wastemanagerservice.infra.util.json.GlobalJsonLocalDateSerializer;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "manager")
@Entity
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class WasteManagerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String nif;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_address_id", referencedColumnName = "id")
    private WasteManagerAddressEntity wasteManagerAddressEntity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private List<WasteCenterAuthorizationEntity> listOfWasteCenterAuthorization;

    private Boolean isEnabled;
    private Long version;
    private Date createdDate;
    private Date lastModifiedDate;
}
