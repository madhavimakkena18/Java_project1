package JavaProject1;
import java.util.ArrayList;
import java.util.List;
public class Hospital {
   private List<MedicalResource> resources = new ArrayList<>();
   public void addResource(MedicalResource resource) {
      resources.add(resource);
   }
   public MedicalResource findAvailableResource(ResourceType resourcetype) {
    for(MedicalResource resource: resources) {
        if(resource.getResourceType() == resourcetype && resource.isAvailable()) {
            return resource;
        }
    }
     return null;
   }
}