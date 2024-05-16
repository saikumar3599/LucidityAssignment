package model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Edge implements Serializable {

    double timeTakenToReachNode;
    Node node;
}
