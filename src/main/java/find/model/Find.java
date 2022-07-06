package find.model;

import java.sql.Date;

public class Find {

    private int faNumber;
    private int mNumber;
    private String species;
    private String kind;
    private String location;
    private String animalState;
    private String imgPath;
    private Date wrTime;
    private String title;
    private String content;

    public Find(int mNumber, String species, String kind, String location, String imgPath, String title, String content) {
        this.mNumber = mNumber;
        this.species = species;
        this.kind = kind;
        this.location = location;
        this.imgPath = imgPath;
        this.title = title;
        this.content = content;
    }

    public Find(int faNumber, int mNumber, String species, String kind, String location, String animalState,
                String imgPath, Date wrTime, String title, String content) {
        this.faNumber = faNumber;
        this.mNumber = mNumber;
        this.species = species;
        this.kind = kind;
        this.location = location;
        this.animalState = animalState;
        this.imgPath = imgPath;
        this.wrTime = wrTime;
        this.title = title;
        this.content = content;
    }

    public int getFaNumber() {
		return faNumber;
    }

    public void setFaNumber(int faNumber) {
		this.faNumber = faNumber;
    }

    public int getmNumber() {
		return mNumber;
    }

    public void setmNumber(int mNumber) {
        this.mNumber = mNumber;
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

    public String getAnimalState() {
        return animalState;
    }

    public void setAnimalState(String animalState) {
        this.animalState = animalState;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Date getWrTime() {
        return wrTime;
    }

    public void setWrTime(Date wrTime) {
        this.wrTime = wrTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}