package com.example.myapp.config;

import com.example.myapp.entity.OptionCake;
import com.example.myapp.entity.Product;
import com.example.myapp.entity.Role;
import com.example.myapp.entity.User;
import com.example.myapp.repository.OptionCakeRepository;
import com.example.myapp.repository.ProductRepository;
import com.example.myapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OptionCakeRepository optionCakeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Tạo Admin mặc định
        if (!userRepository.existsByEmail("admin@sweetcake.vn")) {
            User admin = new User("admin@sweetcake.vn",
                    passwordEncoder.encode("admin123"),
                    Role.ROLE_ADMIN);
            userRepository.save(admin);
            System.out.println("Tạo tài khoản Admin thành công: admin@sweetcake.vn / admin123");
        }

        // Tạo Staff mặc định
        if (!userRepository.existsByEmail("staff@sweetcake.vn")) {
            User staff = new User("staff@sweetcake.vn",
                    passwordEncoder.encode("staff123"),
                    Role.ROLE_STAFF);
            userRepository.save(staff);
            System.out.println("Tạo tài khoản Staff thành công: staff@sweetcake.vn / staff123");
        }

        // Seed OptionCake categories
        if (optionCakeRepository.count() == 0) {
            OptionCake sweet = new OptionCake();
            sweet.setCategoryName("Sweet");
            optionCakeRepository.save(sweet);

            OptionCake salty = new OptionCake();
            salty.setCategoryName("Salty");
            optionCakeRepository.save(salty);

            System.out.println("Tạo danh mục: Sweet (Bánh ngọt) và Salty (Bánh mặn)");
        }

        // Seed Products
        if (productRepository.count() == 0) {
            LocalDateTime now = LocalDateTime.now();

            // --- Bánh ngọt (optionCakeId = 1) ---
            String[][] sweetCakes = {
                {"Chocolate Cake",  "Bánh chocolate đậm đà, phủ lớp ganache mịn màng", "12.99", "50"},
                {"Vanilla Cake",    "Bánh vanilla thơm ngát với kem bơ mềm mịn", "10.99", "40"},
                {"Strawberry Cake", "Bánh dâu tây tươi mát, trang trí dâu tươi", "14.99", "30"},
                {"Caramel Cake",    "Bánh caramel ngọt ngào với lớp sốt caramel vàng óng", "13.99", "35"},
                {"Almond Cake",     "Bánh hạnh nhân giòn tan, thơm bùi đặc trưng", "15.99", "25"},
                {"Blueberry Cake",  "Bánh việt quất tươi mát, vị chua nhẹ hài hòa", "14.49", "30"},
                {"Coconut Cake",    "Bánh dừa thơm béo, phủ dừa nạo mịn", "11.99", "40"},
                {"Honey Cake",      "Bánh mật ong vàng óng, ngọt tự nhiên", "12.49", "35"},
                {"Tiramisu",        "Tiramisu Ý cổ điển với cà phê espresso và mascarpone", "16.99", "20"}
            };

            // Lấy ID thực tế của OptionCake Sweet
            Integer sweetId = optionCakeRepository.findAll().stream()
                    .filter(o -> "Sweet".equals(o.getCategoryName()))
                    .findFirst().map(OptionCake::getOptionCakeId).orElse(1);

            for (int i = 0; i < sweetCakes.length; i++) {
                Product p = new Product();
                p.setOptionCakeId(sweetId);
                p.setCakeName(sweetCakes[i][0]);
                p.setDescription(sweetCakes[i][1]);
                p.setPrice(Double.parseDouble(sweetCakes[i][2]));
                p.setQuantity(Integer.parseInt(sweetCakes[i][3]));
                p.setCreateAt(now.minusDays(sweetCakes.length - i));
                productRepository.save(p);
            }

            // --- Bánh mặn (optionCakeId = 2) ---
            String[][] saltyCakes = {
                {"Cheese Bread",    "Bánh mì phô mai thơm béo, vỏ giòn ruột mềm", "5.99", "60"},
                {"Garlic Bread",    "Bánh mì bơ tỏi nóng hổi, giòn rụm", "4.99", "70"},
                {"Sausage Roll",    "Bánh cuộn xúc xích thơm lừng, vỏ puff pastry giòn", "6.99", "45"},
                {"Ham Croissant",   "Croissant giăm bông và phô mai, lớp bơ xếp hoàn hảo", "7.99", "40"},
                {"Herb Crackers",   "Bánh quy thảo mộc giòn tan, vị mặn nhẹ", "3.99", "80"},
                {"Cheddar Biscuit", "Bánh quy phô mai cheddar, béo ngậy đậm đà", "4.49", "55"},
                {"Tomato Focaccia", "Focaccia cà chua Italia, thơm dầu olive", "8.99", "30"},
                {"Pepper Bread",    "Bánh mì tiêu đen cay nhẹ, hương vị độc đáo", "5.49", "50"},
                {"Parmesan Stick",  "Que phô mai Parmesan giòn, nhẹ nhàng thơm ngon", "4.99", "65"}
            };

            // Lấy ID thực tế của OptionCake Salty
            Integer saltyId = optionCakeRepository.findAll().stream()
                    .filter(o -> "Salty".equals(o.getCategoryName()))
                    .findFirst().map(OptionCake::getOptionCakeId).orElse(2);

            for (int i = 0; i < saltyCakes.length; i++) {
                Product p = new Product();
                p.setOptionCakeId(saltyId);
                p.setCakeName(saltyCakes[i][0]);
                p.setDescription(saltyCakes[i][1]);
                p.setPrice(Double.parseDouble(saltyCakes[i][2]));
                p.setQuantity(Integer.parseInt(saltyCakes[i][3]));
                p.setCreateAt(now.minusDays(saltyCakes.length - i));
                productRepository.save(p);
            }

            System.out.println("Seed thành công 18 sản phẩm (9 bánh ngọt + 9 bánh mặn)");
        }
    }
}
