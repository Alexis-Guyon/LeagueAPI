package fr.bxcchus.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MucJwtDto {
    private String channelClaim;
    private String domain;
    private String jwt;
    private String targetRegion;

}
