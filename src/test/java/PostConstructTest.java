import br.com.tm.fall.boot.container.DependencyContainer;
import br.com.tm.fall.boot.container.DependencyContainerImpl;
import br.com.tm.fall.boot.core.PostConstructImpl;
import br.com.tm.fall.boot.core.RiceFactoryImpl;
import models.after.RiceHighPriority;
import models.after.RiceRegister;
import models.after.RiceWithPostConstruct;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PostConstructTest {

    @Test
    public void testPostConstruct() {
        DependencyContainer dependencyContainer = new DependencyContainerImpl();
        Set<Class<?>> classes = new HashSet<>(Arrays.asList(RiceWithPostConstruct.class, RiceRegister.class, RiceHighPriority.class));
        RiceFactoryImpl factory = new RiceFactoryImpl(dependencyContainer);
        factory.createDependencyContainer(classes);
        new PostConstructImpl(dependencyContainer, classes).execute();

        RiceWithPostConstruct riceWithPostConstruct = dependencyContainer.getRice(RiceWithPostConstruct.class);
        RiceRegister riceRegister = dependencyContainer.getRice(RiceRegister.class);
        RiceHighPriority riceHighPriority = dependencyContainer.getRice(RiceHighPriority.class);

        assertNotNull(riceWithPostConstruct);
        assertNotNull(riceRegister);
        assertNotNull(riceHighPriority);

        assertEquals("RiceHighPriority", riceRegister.getRiceList().get(0));
        assertTrue(riceRegister.getRiceList().containsAll(Arrays.asList("RiceHighPriority", "RiceWithPostConstruct")));
    }

}
