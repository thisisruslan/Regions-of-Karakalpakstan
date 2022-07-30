package uz.gita.regionsofkarakalpakhstan.data;

public class RegionData {
    private int logoID;
    private String name;
    private int imageID;
    private String description;
    private String location;

    public RegionData(int logoID, String name, int imageID, String description, String location) {
        this.logoID = logoID;
        this.name = name;
        this.imageID = imageID;
        this.description = description;
        this.location = location;
    }

    public int getLogoID() {
        return logoID;
    }

    public String getName() {
        return name;
    }

    public int getImageID() {
        return imageID;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {   return location;   }
}
