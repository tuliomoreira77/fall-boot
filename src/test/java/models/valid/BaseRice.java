package models.valid;

import br.com.tm.fall.boot.annot.Injected;
import br.com.tm.fall.boot.annot.Rice;

import java.util.UUID;

@Rice
public class BaseRice {

    private boolean instantiated = false;
    private UUID id;

    @Injected
    public BaseRice() {
        instantiated = true;
        id = UUID.randomUUID();
    }

    public boolean isInstantiated() {
        return instantiated;
    }

    public UUID getId() {
        return id;
    }
}
