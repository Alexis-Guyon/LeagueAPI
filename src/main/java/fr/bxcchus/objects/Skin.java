package fr.bxcchus.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Skin {
    private int championId;
    private String chromaPath;
    private int id;
    private String name;
    private Ownership ownership;
    public boolean isOwned() {
        return ownership != null && ownership.isOwned();
    }
}
