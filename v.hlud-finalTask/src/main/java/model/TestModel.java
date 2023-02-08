package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;


public class TestModel{
    private String duration;
    private String method;
    private String name;
    private String startTime;
    private String endTime=null;
    private String status;

    public TestModel() {
        this.startTime = "";
        this.endTime = "";
    }



    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestModel testModel = (TestModel) o;
        return Objects.equals(duration, testModel.duration) &&
                Objects.equals(method, testModel.method) &&
                Objects.equals(name, testModel.name) &&
                Objects.equals(startTime, testModel.startTime) &&
                Objects.equals(endTime, testModel.endTime) &&
                StringUtils.equalsIgnoreCase(status, testModel.status);
                

    }

    @Override
    public int hashCode() {
        return Objects.hash(duration, method, name, startTime, endTime, status);
    }

    @Override
    public String toString() {
        return "TestModel{" +
                "duration='" + duration + '\'' +
                ", method='" + method + '\'' +
                ", name='" + name + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}