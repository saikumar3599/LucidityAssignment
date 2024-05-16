package model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class GeoLocationData implements Serializable {
    private double latitude;
    private double longitude;
}
