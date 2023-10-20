package app.web.pavelk.read2.schema;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sub_read", schema = "post")
public class SubRead {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NonNull
    @NotBlank(message = "Community name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @OneToMany(fetch = LAZY, mappedBy = "subRead")
    @ToString.Exclude
    private List<Post> posts;

    private Instant createdDate;

    @ManyToOne(fetch = LAZY)
    @ToString.Exclude
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SubRead subRead = (SubRead) o;
        return id != null && Objects.equals(id, subRead.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
