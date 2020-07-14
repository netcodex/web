import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author x Date: 2020-05-21 16:14
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private List<Card> cards;
    private User wife;
}

@Data
@AllArgsConstructor
class Card {
    private Integer serial;
    private String bankName;
    private String password;
}
