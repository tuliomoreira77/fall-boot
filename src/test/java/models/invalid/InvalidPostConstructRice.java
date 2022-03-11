package models.invalid;


import br.com.tm.fall.boot.annot.AfterBuild;
import br.com.tm.fall.boot.annot.Injected;
import br.com.tm.fall.boot.annot.Rice;
import br.com.tm.fall.boot.core.InstanceUtils;

@Rice
public class InvalidPostConstructRice {

    @Injected
    public InvalidPostConstructRice() {}

    @AfterBuild
    public void invalid(InstanceUtils utils) {}

}
