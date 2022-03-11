package models.after;


import br.com.tm.fall.boot.annot.Injected;
import br.com.tm.fall.boot.annot.Rice;

import java.util.ArrayList;
import java.util.List;

@Rice
public class RiceRegister {

    public List<String> riceList;

    @Injected
    public RiceRegister() {
        this.riceList = new ArrayList<>();
    }

    public void registerRice(String name) {
        riceList.add(name);
    }

    public List<String> getRiceList() {
        return riceList;
    }

}
