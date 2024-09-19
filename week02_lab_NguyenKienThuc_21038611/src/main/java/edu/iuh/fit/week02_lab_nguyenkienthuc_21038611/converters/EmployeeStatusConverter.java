package edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.enums.EmployeeStatus;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class EmployeeStatusConverter implements AttributeConverter<EmployeeStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(EmployeeStatus attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getStatus();
    }

    @Override
    public EmployeeStatus convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(EmployeeStatus.values())
                .filter(c -> c.getStatus() == dbData)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
