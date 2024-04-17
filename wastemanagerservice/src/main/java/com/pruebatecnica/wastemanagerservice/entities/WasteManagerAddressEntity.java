package com.pruebatecnica.wastemanagerservice.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pruebatecnica.wastemanagerservice.infra.util.json.GlobalJsonLocalDateDeserializer;
import com.pruebatecnica.wastemanagerservice.infra.util.json.GlobalJsonLocalDateSerializer;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "manager_address")
@Entity
public class WasteManagerAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String direccion;
    private Boolean isEnabled;
    private Long version;
    private Date createdDate;
    private Date lastModifiedDate;
}
