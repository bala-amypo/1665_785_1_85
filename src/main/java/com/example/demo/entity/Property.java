@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String address;
    private String city;

    private Double price;
    private Double areaSqFt;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<RatingLog> ratingLogs;

    // getters & setters
}
