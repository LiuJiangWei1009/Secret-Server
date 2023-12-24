package cn.LJW.Entities.Resource;

public class Resource {
    String resourceName;
    String resourceID;
    String description;
    String illustrate;
    String file_size;

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getFile_size() {
        return file_size;
    }

    public void setFile_size(String file_size) {
        this.file_size = file_size;
    }

    public String getResourceID() {
        return resourceID;
    }

    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIllustrate() {
        return illustrate;
    }

    public void setIllustrate(String illustrate) {
        this.illustrate = illustrate;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "resourceName='" + resourceName + '\'' +
                ", resourceID='" + resourceID + '\'' +
                ", description='" + description + '\'' +
                ", illustrate='" + illustrate + '\'' +
                ", file_size='" + file_size + '\'' +
                '}';
    }
}
