package models.valid;

import br.com.tm.fall.boot.annot.Injected;
import br.com.tm.fall.boot.annot.Rice;

@Rice
public class RiceThatReceiveRice {

    private BaseRice baseRice;

    @Injected
    public RiceThatReceiveRice(BaseRice baseRice) {
        this.baseRice = baseRice;
    }

    public BaseRice getBaseRice() {
        return this.baseRice;
    }
}
