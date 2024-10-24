package fr.bxcchus.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatDetail {
    private MucJwtDto mucJwtDto;
    private String multiUserChatId;
    private String multiUserChatPassword;

}
