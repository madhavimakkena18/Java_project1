package JavaProject1;

public class EmergencyCase {
    private String caseId;
    private Patient patient;
    private DoctorSpecialization requiredSpecialization;
    private ResourceType requiredResourceType;
    private boolean resolved;
    private Doctor assignedDoctor;
    private MedicalResource assignedResource;
    public EmergencyCase(String caseId, Patient patient, DoctorSpecialization requiredSpecialization, ResourceType requiredResourceType) {
        this.caseId = caseId;
        this.patient = patient;
        this.requiredSpecialization = requiredSpecialization;
        this.requiredResourceType = requiredResourceType;
        this.resolved = false;
    }
    public void assignResources(Doctor doctor, MedicalResource resource) {
    assignedDoctor = doctor;
    assignedResource = resource;
    }
    public Doctor getAssignedDoctor() {
    return assignedDoctor;
    }
    public MedicalResource getAssignedResource() {
    return assignedResource;
    }
    public String getCaseId() {
        return caseId;
    }
    public Patient getPatient() {
        return patient;
    }
    public DoctorSpecialization getRequiredSpecialization() {
        return requiredSpecialization;
    }
    public ResourceType getRequiredResourceType() {
        return requiredResourceType;
    }
    public boolean isResolved() {
        return resolved;
    }
    public void markResolved() {
        resolved = true;
    }
}