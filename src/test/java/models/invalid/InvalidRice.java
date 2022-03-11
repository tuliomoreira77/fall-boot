package models.invalid;

import br.com.tm.fall.boot.annot.Injected;
import br.com.tm.fall.boot.annot.Rice;
import br.com.tm.fall.boot.core.InstanceUtils;

@Rice
public class InvalidRice {

    @Injected
    public InvalidRice(InstanceUtils instanceUtils) {

    }

}
