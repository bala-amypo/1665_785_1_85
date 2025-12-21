@Entity
public class FacilityScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Property property;

    @Max(10) @Min(0)
    private Integer schoolProximity;

    @Max(10) @Min(0)
    private Integer hospitalProximity;

    @Max(10) @Min(0)
    private Integer transportAccess;

    @Max(10) @Min(0)
    private Integer safetyScore;

    // getters & setters
}



