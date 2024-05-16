package model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private String userId;
    private String userName;
    private GeoLocationData geoLocationData;
}
