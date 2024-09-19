package com.ProjectFourthYear.FlippedClassroom.subjects;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Subject_ID implements Serializable {
    private String subid;
    private String department;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject_ID that = (Subject_ID) o;
        return Objects.equals(subid, that.subid) &&
               Objects.equals(department, that.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subid, department);
    }
}
