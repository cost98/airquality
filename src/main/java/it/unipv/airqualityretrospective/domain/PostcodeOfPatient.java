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
@Table(name = "postcode_of_patient", schema = "data")
public class PostcodeOfPatient implements Serializable {

    @Id
    @Column(name = "id_postcode_of_patient", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "postcode", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String postcode;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country", nullable = false)
    private Country country;

    @Column(name = "hash_code")
    @Type(type = "org.hibernate.type.TextType")
    private String hashCode;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matching_post_code")
    private PostcodeOfStation matchingPostCode;

    @Column(name = "distance")
    private Double distance;


/*
    TODO [JPA Buddy] create field to map the 'geom' column
     Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "geom", columnDefinition = "public.geometry")
    private java.lang.Object geom;
*/
}