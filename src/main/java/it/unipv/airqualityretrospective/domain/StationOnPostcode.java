package it.unipv.airqualityretrospective.domain;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "station_on_postcode", schema = "data")
public class StationOnPostcode implements Serializable {


    @Id
    @Column(name = "id_station", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "station_code", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String stationCode;

    @NotNull
    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @NotNull
    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "postcode_id", nullable = false)
    private PostcodeOfStation postcode;

/*
    TODO [JPA Buddy] create field to map the 'geom' column
     Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "geom", columnDefinition = "public.geometry")
    private java.lang.Object geom;
*/
}