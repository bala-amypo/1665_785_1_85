@Entity
public class RatingResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Property property;

    private Double finalRating;
    private String ratingCategory;

    private LocalDateTime ratedAt;

    @PrePersist
    public void onCreate() {
        ratedAt = LocalDateTime.now();
    }
}
