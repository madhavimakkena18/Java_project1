package JavaProject1;

public class Doctor {
    private String doctorId;
    private String name;
    private DoctorSpecialization specialization;
    private boolean available;
    public Doctor(String doctorId, String name, DoctorSpecialization specialization) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.available = true;
    }
    public boolean assign() {
        if (available) {
            available = false;
            return true;
        }
        return false;
    }
    public void release() {
        available = true;
    }
    public String getDoctorId() {
        return doctorId;
    }
    public String getName() {
        return name;
    }
    public DoctorSpecialization getSpecialization() {
        return specialization;
    }
    public boolean isAvailable() {
        return available;
    }
}