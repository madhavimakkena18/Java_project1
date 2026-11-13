package JavaProject1;

public class MedicalResource {
    private String resourceId;
    private ResourceType resourceType;
    private boolean available;
    public MedicalResource(String resourceId, ResourceType resourceType) {
        this.resourceId = resourceId;
        this.resourceType = resourceType;
        this.available = true;
    }
    public boolean allocate() {
        if (available) {
            available = false;
            return true;
        }
        return false;
    }
    public void release() {
        available = true;
    }
    public String getResourceId() {
        return resourceId;
    }
    public ResourceType getResourceType() {
        return resourceType;
    }
    public boolean isAvailable() {
        return available;
    }
}

