package racoonsoft.languagebox.structure;

public class Lesson
{
    String name;
    Boolean trial;
    String task;
    Long[] material;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getTrial() {
        return trial;
    }

    public void setTrial(Boolean trial) {
        this.trial = trial;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Long[] getMaterial() {
        return material;
    }

    public void setMaterial(Long[] material) {
        this.material = material;
    }
}
