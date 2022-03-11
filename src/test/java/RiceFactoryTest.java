import br.com.tm.fall.boot.container.DependencyContainer;
import br.com.tm.fall.boot.container.DependencyContainerImpl;
import br.com.tm.fall.boot.core.PostConstructImpl;
import br.com.tm.fall.boot.core.RiceFactoryImpl;
import models.after.RiceHighPriority;
import models.after.RiceRegister;
import models.after.RiceWithPostConstruct;
import models.valid.BaseRice;
import models.valid.RiceThatReceiveRice;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class RiceFactoryTest {

    @Test
    public void testCreateRice() {
        DependencyContainer dependencyContainer = new DependencyContainerImpl();
        Set<Class<?>> classes = new HashSet<>(List.of(BaseRice.class));
        RiceFactoryImpl factory = new RiceFactoryImpl(dependencyContainer);
        factory.createDependencyContainer(classes);

        BaseRice baseRice = dependencyContainer.getRice(BaseRice.class);

        assertNotNull(baseRice);
        assertTrue(baseRice.isInstantiated());
    }

    @Test
    public void testCreateRiceThatReceiveRice() {
        DependencyContainer dependencyContainer = new DependencyContainerImpl();
        Set<Class<?>> classes = new HashSet<>(Arrays.asList(BaseRice.class, RiceThatReceiveRice.class));
        RiceFactoryImpl factory = new RiceFactoryImpl(dependencyContainer);
        factory.createDependencyContainer(classes);

        BaseRice baseRice = dependencyContainer.getRice(BaseRice.class);
        RiceThatReceiveRice riceThatReceiveRice = dependencyContainer.getRice(RiceThatReceiveRice.class);

        assertNotNull(baseRice);
        assertNotNull(riceThatReceiveRice);
        assertEquals(baseRice.getId(), riceThatReceiveRice.getBaseRice().getId());
    }

}
