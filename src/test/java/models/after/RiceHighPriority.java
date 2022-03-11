package models.after;

import br.com.tm.fall.boot.annot.AfterBuild;
import br.com.tm.fall.boot.annot.Injected;
import br.com.tm.fall.boot.annot.Rice;

@Rice
public class RiceHighPriority {

    private final RiceRegister riceRegister;

    @Injected
    public RiceHighPriority(RiceRegister riceRegister) {
        this.riceRegister = riceRegister;
    }

    @AfterBuild(priority = 1)
    public void setup() {
        riceRegister.registerRice(this.getClass().getSimpleName());
    }
}
