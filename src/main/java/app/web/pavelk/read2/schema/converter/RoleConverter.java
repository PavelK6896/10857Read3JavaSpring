package app.web.pavelk.read2.schema.converter;

import app.web.pavelk.read2.schema.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoleConverter implements AttributeConverter<List<User.Role>, String> {

    public final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(List<User.Role> attribute) {
        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(attribute);
        } catch (final JsonProcessingException e) {
            log.error("JSON writing error", e);
        }
        return customerInfoJson;
    }

    @Override
    public List<User.Role> convertToEntityAttribute(String dbData) {
        List<User.Role> customerInfo = null;
        try {
            customerInfo = objectMapper.readValue(dbData, new TypeReference<>() {
            });
        } catch (final IOException e) {
            log.error("JSON reading error", e);
        }
        return customerInfo;
    }
}
