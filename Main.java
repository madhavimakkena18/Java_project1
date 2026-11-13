package JavaProject1;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HospitalManager hospitalManager = new HospitalManager();
        List<EmergencyCase> waitingCases = new ArrayList<>();
        hospitalManager.addDoctor(new Doctor("D001", "Dr. Smith", DoctorSpecialization.CARDIOLOGIST));
        hospitalManager.addDoctor(new Doctor("D002", "Dr. Johnson", DoctorSpecialization.NEUROLOGIST));
        hospitalManager.addDoctor(new Doctor("D003", "Dr. Brown", DoctorSpecialization.GENERAL_PHYSICIAN));
        hospitalManager.addResource(new MedicalResource("ICU01", ResourceType.ICU_BED));
        hospitalManager.addResource(new MedicalResource("ICU02", ResourceType.ICU_BED));
        hospitalManager.addResource(new MedicalResource("GB01", ResourceType.GENERAL_BED));
        hospitalManager.addResource(new MedicalResource("GB02", ResourceType.GENERAL_BED));
        hospitalManager.addResource(new MedicalResource("V01", ResourceType.VENTILATOR));
        while (true) {
            System.out.println("\n===== EMERGENCY RESOURCE ALLOCATION SYSTEM =====");
            System.out.println("1. Register Patient");
            System.out.println("2. Create Emergency Case");
            System.out.println("3. Process Next Emergency Case");
            System.out.println("4. View Waiting Cases");
            System.out.println("5. View Active Cases");
            System.out.println("6. Discharge Patient");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Patient ID: ");
                    String patientId = sc.next();
                    if (hospitalManager.getPatientById(patientId) != null) {
                        System.out.println("Patient ID already exists.");
                        break;
                    }
                    System.out.print("Enter Patient Name: ");
                    String name = sc.next();
                    System.out.print("Enter Gender (M/F): ");
                    char gender = sc.next().charAt(0);
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    System.out.print("Enter Severity (CRITICAL, HIGH, MEDIUM, LOW): ");
                    Severity severity = Severity.valueOf(sc.next().toUpperCase());
                    Patient patient = new Patient(patientId, name, gender, age, severity);
                    hospitalManager.addPatient(patient);
                    System.out.println("Patient registered successfully.");
                    break;
                case 2:
                    System.out.print("Enter Case ID: ");
                    String caseId = sc.next();
                    System.out.print("Enter Patient ID: ");
                    String patientIdForCase = sc.next();
                    Patient patientForCase = hospitalManager.getPatientById(patientIdForCase);
                    if (patientForCase == null) {
                        System.out.println("Patient not found. Register the patient first.");
                        break;
                    }
                    System.out.print("Enter Required Specialization: ");
                    DoctorSpecialization specialization = DoctorSpecialization.valueOf(sc.next().toUpperCase());
                    System.out.print("Enter Required Resource (ICU_BED, GENERAL_BED, VENTILATOR): ");
                    ResourceType resourceType = ResourceType.valueOf(sc.next().toUpperCase());
                    EmergencyCase emergencyCase = new EmergencyCase(caseId, patientForCase, specialization, resourceType);
                    waitingCases.add(emergencyCase);
                    System.out.println("Emergency case added to waiting list.");
                    break;
                case 3:
                    if (waitingCases.isEmpty()) {
                        System.out.println("No emergency cases waiting.");
                        break;
                    }
                    EmergencyCase nextCase = waitingCases.get(0);
                    waitingCases.remove(0);
                    boolean allocated = hospitalManager.allocateResources(nextCase);
                    if (allocated) {
                        System.out.println("Resources allocated for Case ID: " + nextCase.getCaseId());
                    } else {
                        System.out.println("Resources not available for Case ID: " + nextCase.getCaseId());
                        waitingCases.add(nextCase);
                    }
                    break;
                case 4:
                    if (waitingCases.isEmpty()) {
                        System.out.println("No waiting cases.");
                    } else {
                        System.out.println("Waiting Cases:");
                        for (EmergencyCase waitingCase : waitingCases) {
                            System.out.println("Case ID: " + waitingCase.getCaseId() + " | Patient ID: " + waitingCase.getPatient().getId() + " | Severity: " + waitingCase.getPatient().getSeverity());
                        }
                    }
                    break;
                case 5:
                    List<EmergencyCase> activeCases = hospitalManager.getActiveCases();
                    if (activeCases.isEmpty()) {
                        System.out.println("No active cases.");
                    } else {
                        System.out.println("Active Cases:");
                        for (EmergencyCase activeCase : activeCases) {
                            Patient activePatient = activeCase.getPatient();
                            System.out.println("Case ID: " + activeCase.getCaseId() + " | Patient ID: " + activePatient.getId() + " | Patient Name: " + activePatient.getName() + " | Severity: " + activePatient.getSeverity() + " | Status: " + activePatient.getStatus());
                        }
                    }
                    break;
                case 6:
                    System.out.print("Enter Case ID to discharge: ");
                    String dischargeCaseId = sc.next();
                    EmergencyCase caseToDischarge = null;
                    for (EmergencyCase activeCase : hospitalManager.getActiveCases()) {
                        if (activeCase.getCaseId().equals(dischargeCaseId)) {
                            caseToDischarge = activeCase;
                            break;
                        }
                    }
                    if (caseToDischarge != null) {
                        hospitalManager.dischargeCase(caseToDischarge);
                        System.out.println("Patient discharged successfully.");
                    } else {
                        System.out.println("Active case not found.");
                    }
                    break;
                case 7:
                    System.out.println("Exiting the system. Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}