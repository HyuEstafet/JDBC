package pojos;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Date;

@Builder // --> Produces complex builder APIs for the annotated POJO classes; For creating objects
@Entity // --> For marking the class capable of holding database values
@Data // --> A shortcut for @ToString, @EqualsAndHashCode, @Getter, @Setter and @RequiredArgsConstructor
@NoArgsConstructor // --> Generates constructors that take no arguments
@AllArgsConstructor // --> Constructor with one argument for every field

public class CustomerPojoWithLombock {

    // @Column annotation needed for holding the information in the columns of the customers table
@Column (name="customer_id")
    private int customerId;
@Column (name="customer_name")
    private String customerName;
@Column (name="customer_email")
    private String customerEmail;
@Column (name="customer_phone")
    private String customerPhone;
@Column (name="customer_age")
    private int customerAge;
@Column (name="gdpr_status")
    private boolean gdprStatus;
@Column (name="customer_profile_status")
    private boolean customerProfileStatus;
@Column (name="date_profile_created")
    private Date dateProfileCreated;
@Column (name="date_profile_deactivated")
    private Date dateProfileDeactivated;
@Column (name="deactivation_reason")
    private String deactivationReason;
@Column (name="customer_notes")
    private String customerNotes;
}
