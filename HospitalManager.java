package JavaProject1;

import java.util.ArrayList;
import java.util.List;

public class HospitalManager {
    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<MedicalResource> resources;
    private List<EmergencyCase> activeCases;
    public HospitalManager() {
        patients = new ArrayList<>();
        doctors = new ArrayList<>();
        resources = new ArrayList<>();
        activeCases = new ArrayList<>();
    }
    public void addPatient(Patient patient) {
        patients.add(patient);
    }
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }
    public void addResource(MedicalResource resource) {
        resources.add(resource);
    }
    public Patient getPatientById(String patientId) {
        for (Patient patient : patients) {
            if (patient.getId().equals(patientId)) {
                return patient;
            }
        }
        return null;
    }
    public boolean allocateResources(EmergencyCase emergencyCase) {
        Doctor selectedDoctor = null;
        MedicalResource selectedResource = null;
        for (Doctor doctor : doctors) {
            if (doctor.getSpecialization() == emergencyCase.getRequiredSpecialization() && doctor.isAvailable()) {
                selectedDoctor = doctor;
                break;
            }
        }
        for (MedicalResource resource : resources) {
            if (resource.getResourceType() == emergencyCase.getRequiredResourceType() && resource.isAvailable()) {
                selectedResource = resource;
                break;
            }
        }
        if (selectedDoctor != null && selectedResource != null) {
            selectedDoctor.assign();
            selectedResource.allocate();
            emergencyCase.assignResources(selectedDoctor, selectedResource);
            emergencyCase.getPatient().activate();
            activeCases.add(emergencyCase);
            return true;
        }
        return false;
    }
    public void dischargeCase(EmergencyCase emergencyCase) {
        emergencyCase.getAssignedDoctor().release();
        emergencyCase.getAssignedResource().release();
        emergencyCase.getPatient().discharge();
        emergencyCase.markResolved();
        activeCases.remove(emergencyCase);
    }
    public List<EmergencyCase> getActiveCases() {
        return activeCases;
    }
}