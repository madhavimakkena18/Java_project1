package JavaProject1;

public class Patient {
    private String patientId;
    private String name;
    private char gender;
    private int age;
    private Severity severity;
    private PatientStatus status;
    public Patient(String patientId, String name, char gender, int age, Severity severity) {
        this.patientId = patientId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.severity = severity;
        this.status = PatientStatus.WAITING;
    }
    public String getId() {
        return patientId;
    }
    public String getName() {
        return name;
    }
    public char getGender() {
        return gender;
    }
    public int getAge() {
        return age;
    }
    public Severity getSeverity() {
        return severity;
    }
    public PatientStatus getStatus() {
        return status;
    }
    public void activate() {
        status = PatientStatus.ACTIVE;
    }
    public void discharge() {
        status = PatientStatus.DISCHARGED;
    }
}