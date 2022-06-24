package abandoned_animal.model;

import java.sql.Blob;
import java.sql.Date;

public class AbandonedAnimal {

    private char gender;
    private int abNumber;
    private int sNumber;
    private int age;
    private float weight;
    private String name;
    private String species;
    private String kind;
    private String location;
    private String uniqueness;
    private String description;
    private String animalState;
    private String imgPath;
    private Date admissionDate;
    private Blob video;

    public AbandonedAnimal(String name, String img, String location, Date admissionDate) {
        this.name = name;
        this.imgPath = img;
        this.location = location;
        this.admissionDate = admissionDate;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getAbNumber() {
        return abNumber;
    }

    public void setAbNumber(int abNumber) {
        this.abNumber = abNumber;
    }

    public int getsNumber() {
        return sNumber;
    }

    public void setsNumber(int sNumber) {
        this.sNumber = sNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUniqueness() {
        return uniqueness;
    }

    public void setUniqueness(String uniqueness) {
        this.uniqueness = uniqueness;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnimalState() {
        return animalState;
    }

    public void setAnimalState(String animalState) {
        this.animalState = animalState;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Blob getVideo() {
        return video;
    }

    public void setVideo(Blob video) {
        this.video = video;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
