package models.after;

import br.com.tm.fall.boot.annot.AfterBuild;
import br.com.tm.fall.boot.annot.Injected;
import br.com.tm.fall.boot.annot.Rice;

@Rice
public class RiceWithPostConstruct {

    private final RiceRegister riceRegister;

    @Injected
    public RiceWithPostConstruct(RiceRegister riceRegister) {
        this.riceRegister = riceRegister;
    }

    @AfterBuild
    public void postConstruct() {
        riceRegister.registerRice(this.getClass().getSimpleName());
    }

}
