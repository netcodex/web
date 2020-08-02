import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 描述：测试工具类
 *
 * @author x
 * @since 2020-08-01 11:57
 */
public class TestUtil {
    public static User getUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("alexander");
        user.setPassword("#an&3%f4");

        Card card1 = new Card(1001, "工商银行", "897564");
        Card card2 = new Card(1006, "建设银行", "583456");
        Card card3 = new Card(1011, "农业银行", "589642");
        Card card4 = new Card(1016, "招商银行", "258945");

        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        List<Card> cards1 = new ArrayList<>();
        cards1.add(card4);

        Date birthDay = new Date();
        user.setBirthDay(birthDay);

        User wife = new User();
        wife.setUsername("catalina");
        wife.setPassword("&t34@yu%5+");
        wife.setCards(cards1);

        user.setCards(cards);
        user.setWife(wife);
        return user;
    }
}
